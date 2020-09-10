package com.lyf.thirdpartyservice.controller;

import com.lyf.common.utils.R;
import com.lyf.thirdpartyservice.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyf
 * @date 2020/6/22-2:04
 */
@RestController
@RequestMapping("/sms")
public class SmsSendController {

    @Autowired
    SmsComponent smsComponent;

    @RequestMapping("/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("param") String param){
        smsComponent.SendSmsCode(phone,param);
        return R.ok();
    }

}
