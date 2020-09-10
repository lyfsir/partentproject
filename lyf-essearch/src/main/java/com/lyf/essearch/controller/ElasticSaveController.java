package com.lyf.essearch.controller;

import com.lyf.common.codeMesg.MyCodemsg;
import com.lyf.common.model.FoodInfosModel;
import com.lyf.common.model.FoodSpecialModel;
import com.lyf.common.model.TopicInfosModel;
import com.lyf.common.utils.R;
import com.lyf.essearch.service.FoodSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyf
 * @date 2020/7/17-0:18
 */
@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {

    @Autowired
    FoodSaveService foodSaveService;


    //向es中保存通过审核的食物描述信息
    @PostMapping("/foodinfo")
    public R foodSave(@RequestBody FoodInfosModel foodInfosModel){
        boolean b = false;
        try {
            b = foodSaveService.saveinfo(foodInfosModel);
            //这种错误表示es的某个数据有问题

        }catch (Exception e){
            //出现这种错误表示es客户端连接不上
            log.error("es保存审核通过信息错误",e);
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }

        if(b){
            return R.ok();
        }else {
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }

    }

    @PostMapping("/foodspecial")
    public R foodSave(@RequestBody FoodSpecialModel foodSpecialModel){
        boolean b = false;
        try {
            b = foodSaveService.savespecial(foodSpecialModel);
            //这种错误表示es的某个数据有问题

        }catch (Exception e){
            //出现这种错误表示es客户端连接不上
            log.error("es保存专题信息错误",e);
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }

        if(b){
            return R.ok();
        }else {
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }

    }

    @PostMapping("/topic")
    public R topicsave(@RequestBody TopicInfosModel topicInfosModel){
        boolean b = false;
        try {
            b = foodSaveService.saveTopic(topicInfosModel);
            //这种错误表示es的某个数据有问题

        }catch (Exception e){
            //出现这种错误表示es客户端连接不上
            log.error("es保存专题信息错误",e);
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }

        if(b){
            return R.ok();
        }else {
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }
    }

}
