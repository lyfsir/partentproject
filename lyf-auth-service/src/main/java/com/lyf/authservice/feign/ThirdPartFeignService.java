package com.lyf.authservice.feign;

import com.lyf.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lyf
 * @date 2020/6/22-2:18
 */
@FeignClient("lyf-third-party-service")
public interface ThirdPartFeignService {

    @RequestMapping("/sms/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("param") String param);
}
