package io.renren.modules.ums.controller;

import io.renren.common.utils.R;
import io.renren.fegin.UserMemberFegin;
import io.renren.modules.app.annotation.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lyf
 * @date 2020/8/4-5:00
 */
@RestController
@RequestMapping("/ums")
public class UmsController {
    @Autowired
    UserMemberFegin userMemberFegin;

    @Login
    @GetMapping("ums/list")
    public R doget(@RequestParam Map<String, Object> params){
        R list = userMemberFegin.list(params);
        return R.ok().put("page",list.get("page"));
    }

    @Login
    @DeleteMapping("ums/del")
    public R doget(@RequestBody Integer[] ids){
        userMemberFegin.delete(ids);
        return R.ok();
    }
}
