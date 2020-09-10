package com.lyf.essearch.controller;

import com.lyf.common.codeMesg.MyCodemsg;
import com.lyf.common.utils.R;
import com.lyf.essearch.service.FoodDelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/8/6-19:26
 */
@Slf4j
@RestController
@RequestMapping("search/del")
public class ElasticDelController {
    @Autowired
    FoodDelService foodDelService;

    @DeleteMapping("/doDel")
    public R delfoods(@RequestBody Integer[] ids){
        boolean b =false;
        try {
            b = foodDelService.delFood(ids);
        } catch (IOException e) {
            //出现这种错误表示es客户端连接不上
            log.error("ES删除信息失败",e);
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }
        if(b){
            return R.ok();
        }else {
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }
    }

    @DeleteMapping("/delspecial")
    public R delspecial(@RequestBody Integer[] ids){
        boolean b = false;
        try {
            b = foodDelService.delSpecial(ids);
        } catch (IOException e) {
            //出现这种错误表示es客户端连接不上
            log.error("ES删除信息失败",e);
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }
        if(b){
            return R.ok();
        }else {
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }
    }

    @DeleteMapping("/deltopic")
    public R deltopic(@RequestBody Integer[] ids){
        boolean b = false;
        try {
            b = foodDelService.delTopic(ids);
        } catch (IOException e) {
            //出现这种错误表示es客户端连接不上
            log.error("ES删除信息失败",e);
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }
        if(b){
            return R.ok();
        }else {
            return R.error(MyCodemsg.ES_EXCEPTION.getCode(), MyCodemsg.ES_EXCEPTION.getMsg());
        }
    }
}
