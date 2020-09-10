package io.renren.modules.ums.controller;

import io.renren.common.utils.R;
import io.renren.fegin.FoodSortFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lyf
 * @date 2020/8/4-5:31
 */
@RestController
@RequestMapping("/ums/auditi")
public class UmsAuditingController {
    @Autowired
    FoodSortFegin foodSortFegin;
    @GetMapping("/ums/{num}")
    public R doGet(@RequestParam Map<String, Object> params,
                   @PathVariable Integer num){
        R r = foodSortFegin.list(params,num);
        return R.ok().put("page",r.get("page"));
    }
}
