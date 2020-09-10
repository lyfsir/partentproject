package com.lyf.check.food.fegin;

import com.lyf.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lyf
 * @date 2020/7/2-2:03
 */
@FeignClient("lyf-ums")
public interface UmsMemberService {
    //获取用户名
    @RequestMapping("/ums/umsmember/username/{id}")
    R username(@PathVariable("id") Integer id);
}
