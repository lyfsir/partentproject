package com.lyf.authservice.feign;

import com.lyf.authservice.vo.SocialityVo;
import com.lyf.authservice.vo.UserLoginVo;
import com.lyf.authservice.vo.UserVo;
import com.lyf.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lyf
 * @date 2020/6/23-1:41
 */
@FeignClient("lyf-ums")
public interface UmsMemberService {
    @PostMapping("/ums/umsmember/register/ums")
    R register(@RequestBody UserVo userVo);

    @PostMapping("/ums/umsmember/login/ums")
    R login(@RequestBody UserLoginVo userLoginVo);

    @PostMapping("/ums/umsmember/social/login")
    R oauth2login(@RequestBody SocialityVo socialityVo) throws Exception;
}
