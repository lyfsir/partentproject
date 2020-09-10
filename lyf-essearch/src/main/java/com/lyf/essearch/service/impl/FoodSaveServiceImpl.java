package com.lyf.essearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.lyf.common.model.FoodInfosModel;
import com.lyf.common.model.FoodSpecialModel;
import com.lyf.common.model.TopicInfosModel;
import com.lyf.essearch.config.LyfEsConfig;
import com.lyf.essearch.constant.EsConstant;
import com.lyf.essearch.service.FoodSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/7/17-0:22
 */
@Slf4j
@Service
public class FoodSaveServiceImpl implements FoodSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean saveinfo(FoodInfosModel foodInfosModel) throws IOException {
        //给es建立一个索引

        //给es中保存这些数据(批量保存用BulkRequest,因为只有一个实体类不用他)
        //构造保存请求
        IndexRequest indexRequest = new IndexRequest(EsConstant.FOODINFO_INDEX);
        indexRequest.id(foodInfosModel.getId().toString());
        //把对象转换为json字符串存进去
        String jsonString = JSON.toJSONString(foodInfosModel);
        indexRequest.source(jsonString, XContentType.JSON);//要保存的数据,以及说明是json数据类型
        //执行操作
        IndexResponse index = restHighLevelClient.index(indexRequest, LyfEsConfig.COMMON_OPTIONS);
        RestStatus status = index.status();
        int status1 = status.getStatus();
        if(status1 != 200 && status1 != 201){
            log.error("es保存失败",index.getId());
            return false;
        }else {
            log.info("es保存成功",index.getId());
            return true;
        }

    }

    @Override
    public boolean savespecial(FoodSpecialModel foodSpecialModel) throws IOException {
        IndexRequest indexRequest = new IndexRequest(EsConstant.FOODSPECIAL_INDEX);
        indexRequest.id(foodSpecialModel.getId().toString());
        String jsonString = JSON.toJSONString(foodSpecialModel);
        indexRequest.source(jsonString,XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, LyfEsConfig.COMMON_OPTIONS);
        RestStatus status = index.status();
        int status1 = status.getStatus();
        if(status1 != 200 && status1 != 201){
            log.error("es保存失败",index.getId());
            return false;
        }else {
            log.info("es保存成功",index.getId());
            return true;
        }
    }

    @Override
    public boolean saveTopic(TopicInfosModel topicInfosModel) throws IOException {
        IndexRequest indexRequest = new IndexRequest(EsConstant.TOPICINFO_INDEX);
        indexRequest.id(topicInfosModel.getId().toString());
        String jsonString = JSON.toJSONString(topicInfosModel);
        indexRequest.source(jsonString,XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, LyfEsConfig.COMMON_OPTIONS);
        RestStatus status = index.status();
        int status1 = status.getStatus();
        if(status1 != 200 && status1 != 201){
            log.error("es保存失败",index.getId());
            return false;
        }else {
            log.info("es保存成功",index.getId());
            return true;
        }
    }
}
