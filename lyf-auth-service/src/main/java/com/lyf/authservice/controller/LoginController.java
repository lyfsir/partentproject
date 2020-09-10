package com.lyf.authservice.controller;

import com.lyf.authservice.feign.ThirdPartFeignService;
import com.lyf.authservice.feign.UmsMemberService;
import com.lyf.authservice.vo.UserLoginVo;
import com.lyf.authservice.vo.UserVo;
import com.lyf.common.codeMesg.MyCodemsg;
import com.lyf.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lyf
 * @date 2020/6/22-2:15
 */
@RestController

public class LoginController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ThirdPartFeignService thirdPartFeignService;

    @Autowired
    UmsMemberService umsMemberService;

    @RequestMapping("/sms/sendCode")
    public R sendCode(@RequestParam("phone") String phone) {
        //TODO 1，防止接口恶意刷
        //2，设置验证码的有效时间redis存放验证码
        String s = stringRedisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(s)) {
            Long l = Long.parseLong(s.split("_")[1]);
            if (System.currentTimeMillis() - l < 60000) {
                return R.error(MyCodemsg.CODE_EXCEPTION.getCode(), MyCodemsg.CODE_EXCEPTION.getMsg());
            }
        }
        //3，防止刷新页面重新发送验证码
        String substring = UUID.randomUUID().toString().substring(0, 5) + "_" + System.currentTimeMillis();
        stringRedisTemplate.opsForValue().set(phone, substring, 10, TimeUnit.MINUTES);
        thirdPartFeignService.sendCode(phone, "code:" + substring.split("_")[0]);
        return R.ok();
    }

    @PostMapping("/register/user")
    public R register(@RequestBody UserVo userVo) {
        //因为设置了校验功能 ， phone不为空
        String phone = userVo.getMobile();
        String code = userVo.getCode();
        //由于phone可能不存在redis中，此时分割字符串会报错

        String phoneCode = stringRedisTemplate.opsForValue().get(phone);
        if (phoneCode == null) {
            //验证码不存在
            return R.error(MyCodemsg.CODENULL_EXCEPTION.getCode(), MyCodemsg.CODENULL_EXCEPTION.getMsg());
        } else if (!code.equals(phoneCode.split("_")[0])) {
            return R.error(MyCodemsg.CODENOT_EXCEPTION.getCode(), MyCodemsg.CODENOT_EXCEPTION.getMsg());
        } else {
            R r = umsMemberService.register(userVo);
            int code1 = (int) r.get("code");
            if (code1 == 0) {
                return R.ok();
            } else {
                return R.error((int) r.get("code"), (String) r.get("msg"));
            }
        }
    }

    @PostMapping("/login/user")
    public R login(@RequestBody UserLoginVo userLoginVo) {
        //远程调用ums服务
        R login = umsMemberService.login(userLoginVo);
        if ((int) login.get("code") != 0) {
            return R.error((int) login.get("code"), (String) login.get("msg"));
        } else {
            //没有错误信息
            //TODO 登陆成功处理
            return R.ok().put("data",login.get("data"));
        }
    }

}
