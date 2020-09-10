package com.lyf.check.ums.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyf.check.ums.vo.*;
import com.lyf.common.codeMesg.MyCodemsg;
import com.lyf.common.exception.MobileException;
import com.lyf.common.exception.NickNameException;
import com.lyf.common.exception.UserNameException;
import com.lyf.jwt.config.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lyf.check.ums.entity.UmsMemberEntity;
import com.lyf.check.ums.service.UmsMemberService;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-04 00:22:43
 */
@RestController
@RequestMapping("ums/umsmember")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("/getuserinfo")
    public R getuserInfo(@RequestParam String username){
        UmsMemberEntity entity = umsMemberService.getBaseMapper().selectOne(
                new QueryWrapper<UmsMemberEntity>().eq("username", username));
        if(entity!=null){
            UmsInfoVo umsInfoVo = new UmsInfoVo();
            BeanUtils.copyProperties(entity,umsInfoVo);
            return R.ok().put("data",umsInfoVo);
        }
        return R.ok();
    }

    //获取用户名
    @RequestMapping("/username/{id}")
    public R username(@PathVariable("id") Integer id){
        String[] user = umsMemberService.getusername(id);

        return R.ok().put("username", user[0]).put("logo",user[1]);
    }

    @PostMapping("/register/ums")
    public R register(@RequestBody UmsMember umsMember){
        try {
            umsMemberService.register(umsMember);
        }catch (UserNameException e){
            return R.error(MyCodemsg.USERNAME_EXCEPTION.getCode(),MyCodemsg.USERNAME_EXCEPTION.getMsg());
        }catch (NickNameException e){
            return R.error(MyCodemsg.NIKENAME_EXCEPTION.getCode(),MyCodemsg.NIKENAME_EXCEPTION.getMsg());
        }catch (MobileException e){
            return R.error(MyCodemsg.MOBILE_EXCEPTION.getCode(),MyCodemsg.MOBILE_EXCEPTION.getMsg());
        }

        return R.ok();
    }

    @PostMapping("/login/ums")
    public R login(@RequestBody UmsLoginVo umsLoginVo){
        UmsAccount login = umsMemberService.login(umsLoginVo);
        if(login==null){
            //提交信息不正确
            return R.error(MyCodemsg.LOGIN_INFO_EXCEPTION.getCode(),MyCodemsg.LOGIN_INFO_EXCEPTION.getMsg());
        }else{
            //提交的信息正确
            String jwt = JwtUtil.createJWT(String.valueOf(login.getId()), String.valueOf(login.getUsername()), null);
            login.setToken(jwt);
            return R.ok().put("data",login);
        }
    }

    @PostMapping("/social/login")
    public R oauth2login(@RequestBody SocialityVo socialityVo) throws Exception {
        UmsAccount login = umsMemberService.login(socialityVo);
        if(login==null){
            //提交信息不正确
            return R.error(MyCodemsg.SOCOAL_LOGIN_INFO_EXCEPTION.getCode(),MyCodemsg.SOCOAL_LOGIN_INFO_EXCEPTION.getMsg());
        }else{
            //提交的信息正确
            String jwt = JwtUtil.createJWT(String.valueOf(login.getId()), String.valueOf(login.getUsername()), null);
            login.setToken(jwt);
            return R.ok().put("data",login);
        }
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = umsMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息 ums/umsmember
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UmsMemberEntity umsMember = umsMemberService.getById(id);

        return R.ok().put("umsMember", umsMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UmsMemberEntity umsMember){
		umsMemberService.save(umsMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UmsMemberEntity umsMember){
		umsMemberService.updateById(umsMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		umsMemberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
