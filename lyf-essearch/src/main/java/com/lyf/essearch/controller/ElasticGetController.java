package com.lyf.essearch.controller;

import com.lyf.common.utils.R;
import com.lyf.essearch.service.FoodSelectService;
import com.lyf.essearch.service.SpecialService;
import com.lyf.essearch.service.TopicService;
import com.lyf.essearch.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyf
 * @date 2020/7/20-1:16
 */

@RequestMapping("/search/doget")
@RestController
public class ElasticGetController {
    @Autowired
    FoodSelectService foodSelectService;

    @Autowired
    SpecialService specialService;

    @Autowired
    TopicService topicService;
    //检索es(foodinfos)
    @GetMapping("/doget/esfood")
    public R getfoods(SearchParam searchParam){
        SearchResponseVo search = foodSelectService.search(searchParam);
        return R.ok().put("data",search);
    }

    @GetMapping("doget/special")
    public R getspecial(SpecialParam specialParam){
        SpecialResponseVo specialResponseVo = specialService.search(specialParam);
        return R.ok().put("data",specialResponseVo);
    }

    @GetMapping("doget/topic")
    public R getTopic(TopicParam topicParam){
        TopicResponseVo topicResponseVo = topicService.search(topicParam);
        return R.ok().put("data",topicResponseVo);
    }

}
