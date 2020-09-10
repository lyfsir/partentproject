package com.lyf.essearch.service.impl;

import com.lyf.essearch.constant.EsConstant;
import com.lyf.essearch.service.FoodDelService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyf
 * @date 2020/8/6-19:31
 */
@Slf4j
@Service
public class FoodDelServiceImpl implements FoodDelService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean delFood(Integer[] ids) throws IOException {
        List<DeleteResponse> list = new ArrayList<>();
        for (int id : ids){
            DeleteRequest deleteRequest = new DeleteRequest(EsConstant.FOODINFO_INDEX,String.valueOf(id));
            DeleteResponse response = null;
            response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            RestStatus status = response.status();
            int status1 = status.getStatus();
            if (status1!=200){
                log.error("es删除失败",response.getId());
                return false;
            }
            list.add(response);
        }
        return true;
    }

    @Override
    public boolean delSpecial(Integer[] ids) throws IOException {
        List<DeleteResponse> list = new ArrayList<>();
        for (int id : ids){
            DeleteRequest deleteRequest = new DeleteRequest(EsConstant.FOODSPECIAL_INDEX,String.valueOf(id));
            DeleteResponse response = null;
            response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            RestStatus status = response.status();
            int status1 = status.getStatus();
            if (status1!=200){
                log.error("es删除失败",response.getId());
                return false;
            }
            list.add(response);
        }
        return true;
    }

    @Override
    public boolean delTopic(Integer[] ids) throws IOException {
        List<DeleteResponse> list = new ArrayList<>();
        for (int id : ids){
            DeleteRequest deleteRequest = new DeleteRequest(EsConstant.TOPICINFO_INDEX,String.valueOf(id));
            DeleteResponse response = null;
            response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            RestStatus status = response.status();
            int status1 = status.getStatus();
            if (status1!=200){
                log.error("es删除失败",response.getId());
                return false;
            }
            list.add(response);
        }
        return true;
    }
}
