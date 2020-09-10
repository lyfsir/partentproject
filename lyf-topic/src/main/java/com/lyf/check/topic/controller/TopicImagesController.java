package com.lyf.check.topic.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyf.check.topic.entity.TopicImagesEntity;
import com.lyf.check.topic.service.TopicImagesService;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.R;



/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-08 08:55:31
 */
@RestController
@RequestMapping("topic/topicimages")
public class TopicImagesController {
    @Autowired
    private TopicImagesService topicImagesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = topicImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		TopicImagesEntity topicImages = topicImagesService.getById(id);

        return R.ok().put("topicImages", topicImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TopicImagesEntity topicImages){
		topicImagesService.save(topicImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TopicImagesEntity topicImages){
		topicImagesService.updateById(topicImages);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		topicImagesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
