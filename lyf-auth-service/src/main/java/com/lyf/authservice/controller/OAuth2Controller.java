package com.lyf.authservice.controller;

import com.alibaba.fastjson.JSON;
import com.lyf.authservice.feign.UmsMemberService;
import com.lyf.authservice.vo.SocialityVo;
import com.lyf.common.codeMesg.MyCodemsg;
import com.lyf.common.utils.HttpUtils;
import com.lyf.common.utils.R;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/6/25-3:39
 */
@RestController
public class OAuth2Controller {

    @Autowired
    UmsMemberService umsMemberService;

    //引导用户到
    // https://api.weibo.com/oauth2/authorize?client_id=你自己的client_id&response_type=code&redirect_uri=成功授权后的回调地址(和微博平台的配置一样)
    // 会带一个code码，与成功的地址进行拼接也就是下面的地址，从而得到accessToken码
    @GetMapping("/oauth/success")
    public R weiboLogin(@RequestParam String code) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("client_id","2927018396");
        map.put("client_secret", "722b600ab0617f097e4628e9e971f039");
        map.put("grant_type","authorization_code");
        map.put("redirect_uri","http://127.0.0.1:8080/success");
        map.put("code",code);

        //https://api.weibo.com/oauth2/access_token
        HttpResponse post = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "POST",new HashMap<String, String>(),new HashMap<String, String>(),map);

        if(post.getStatusLine().getStatusCode()==200){
            //获取到了授权，accessToken
            //把得到的实体类对象转为json在封装为自己的vo对象
            String json = EntityUtils.toString(post.getEntity());
            SocialityVo socialityVo = JSON.parseObject(json, SocialityVo.class);
            R r = umsMemberService.oauth2login(socialityVo);
            if((int)r.get("code")==0){
                return R.ok().put("data",r.get("data"));
            }else{
                return R.error((int)r.get("code"),(String) r.get("msg"));
            }
        }else {
            return R.error(MyCodemsg.SOCOAL_LOGIN_INFO_EXCEPTION.getCode(), MyCodemsg.SOCOAL_LOGIN_INFO_EXCEPTION.getMsg());
        }

    }
}
