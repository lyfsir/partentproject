package com.lyf.check.topic.fegin;

import com.lyf.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lyf
 * @date 2020/8/8-9:14
 */
@FeignClient("lyf-ums")
public interface UmsMemberFegin {
    //获取用户名
    @RequestMapping("/ums/umsmember/username/{id}")
    R username(@PathVariable("id") Integer id);
}
