package io.renren.modules.topic.controller;

import io.renren.common.utils.R;
import io.renren.fegin.TopicFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lyf
 * @date 2020/8/8-9:25
 */
@RestController
@RequestMapping("doget/topic")
public class TopicInfoController {
    @Autowired
    TopicFegin topicFegin;
    @GetMapping("info/list")
    public R doGet(@RequestParam Map<String, Object> params){
        R list = topicFegin.list(params);
        return R.ok().put("page",list.get("page"));
    }

    @DeleteMapping("del/info")
    public R del(@RequestBody Integer[] ids){
        topicFegin.delete(ids);
        return R.ok();
    }
}
