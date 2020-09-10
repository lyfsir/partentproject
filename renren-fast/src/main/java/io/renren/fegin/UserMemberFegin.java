package io.renren.fegin;

import io.renren.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author lyf
 * @date 2020/8/4-4:58
 */

@FeignClient("lyf-ums")
public interface UserMemberFegin {
    /**
     * 列表
     */
    @RequestMapping("ums/umsmember/list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 删除
     */
    @RequestMapping("ums/umsmember/delete")
    public R delete(@RequestBody Integer[] ids);
}
