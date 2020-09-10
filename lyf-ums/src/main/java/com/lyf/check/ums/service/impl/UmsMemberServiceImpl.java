package com.lyf.check.ums.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyf.check.ums.vo.SocialityVo;
import com.lyf.check.ums.vo.UmsAccount;
import com.lyf.check.ums.vo.UmsLoginVo;
import com.lyf.check.ums.vo.UmsMember;
import com.lyf.common.exception.MobileException;
import com.lyf.common.exception.NickNameException;
import com.lyf.common.exception.UserNameException;
import com.lyf.common.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.ums.dao.UmsMemberDao;
import com.lyf.check.ums.entity.UmsMemberEntity;
import com.lyf.check.ums.service.UmsMemberService;


@Service("umsMemberService")
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberDao, UmsMemberEntity> implements UmsMemberService {



    @Override
    public String[] getusername(Integer id) {
        UmsMemberEntity entity = this.baseMapper.selectById(id);
        String username = entity.getUsername();
        String header = entity.getHeader();
        String[] user = {username,header};
        return user;
    }

    @Override
    public UmsAccount login(SocialityVo socialityVo) throws Exception {
        String uid = socialityVo.getUid();
        UmsMemberEntity entity = this.getBaseMapper().selectOne(new QueryWrapper<UmsMemberEntity>()
                .eq("social_uid", uid));
        if (entity != null) {
            //登陆过,更新数据库里的信息
            UmsMemberEntity update = new UmsMemberEntity();
            update.setId(entity.getId());
            update.setAccesstoken(entity.getAccesstoken());
            update.setExpiresIn(entity.getExpiresIn());
            this.updateById(update);
            entity.setAccesstoken(update.getAccesstoken());
            entity.setExpiresIn(update.getExpiresIn());
            UmsAccount account = new UmsAccount();
            BeanUtils.copyProperties(entity,account);
            return account;
        } else {
            //此社交账号未登录，自动注册
            UmsMemberEntity register = new UmsMemberEntity();
            //查询当前用户的社交账号信息，通过accessToken查找
            try {
                Map<String, String> query = new HashMap<>();
                query.put("access_token", socialityVo.getAccess_token());
                query.put("uid", socialityVo.getUid());
                HttpResponse get = HttpUtils.doGet("https://api.weibo.com", "/2/users/show.json", "GET", new HashMap<String, String>(), query);
                if (get.getStatusLine().getStatusCode() == 200) {
                    //查询成功
                    String json = EntityUtils.toString(get.getEntity());
                    JSONObject jsonObject = JSON.parseObject(json);
                    String uname = jsonObject.getString("screen_name");
                    String nikename = jsonObject.getString("name");
                    String gender = jsonObject.getString("gender");
                    String city = jsonObject.getString("city");
                    String imageUrl = jsonObject.getString("profile_image_url");
                    String sign = jsonObject.getString("description");
                    register.setUsername(uname);
                    register.setNickname(nikename);
                    register.setGender("m".equals(gender) ? 1 : 0);
                    register.setCity(city);
                    register.setHeader(imageUrl);
                    register.setSign(sign);
                } else {
                    return null;
                }
            } catch (Exception e) {
            }
            register.setSocialUid(socialityVo.getUid());
            register.setAccesstoken(socialityVo.getAccess_token());
            register.setExpiresIn((String.valueOf(socialityVo.getExpires_in())));
            this.save(register);
            UmsAccount umsAccount = new UmsAccount();
            BeanUtils.copyProperties(register,umsAccount);
            return umsAccount;
        }

    }

    @Override
    public void register(UmsMember umsMember) {
        UmsMemberEntity entity = new UmsMemberEntity();
        BeanUtils.copyProperties(umsMember, entity);

        UmsMemberEntity username = this.getBaseMapper().selectOne(new QueryWrapper<UmsMemberEntity>()
                .eq("username", entity.getUsername()));
        UmsMemberEntity nickname = this.getBaseMapper().selectOne(new QueryWrapper<UmsMemberEntity>()
                .eq("nickname", entity.getNickname()));
        UmsMemberEntity mobile = this.getBaseMapper().selectOne(new QueryWrapper<UmsMemberEntity>()
                .eq("mobile", entity.getMobile()));

        if (username != null) {
            //出现用户名重复 抛出异常
            throw new UserNameException();
        } else if (nickname != null) {
            //出现昵称名重复 抛出异常
            throw new NickNameException();
        } else if (mobile != null) {
            //出现手机号码名重复 抛出异常
            throw new MobileException();
        }
        String password = entity.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        entity.setPassword(encode);
        this.save(entity);
    }

    @Override
    public UmsAccount login(UmsLoginVo umsLoginVo) {
        UmsMemberEntity entity = this.getBaseMapper().selectOne(
                new QueryWrapper<UmsMemberEntity>()
                        .eq("username", umsLoginVo.getUser())
                        .or()
                        .eq("mobile", umsLoginVo.getUser())
        );
        if (entity == null) {
            return null;
        } else {
            //存在此用户，校验密码
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean b = encoder.matches(umsLoginVo.getPassword(), entity.getPassword());
            if (b) {
                //密码正确
                UmsAccount umsAccount = new UmsAccount();
                BeanUtils.copyProperties(entity,umsAccount);
                return umsAccount;
            } else {
                return null;
            }
        }

    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String)params.get("username");
        IPage<UmsMemberEntity> page = this.page(
                new Query<UmsMemberEntity>().getPage(params),
                new QueryWrapper<UmsMemberEntity>().like("username",username)
        );

        return new PageUtils(page);
    }

}