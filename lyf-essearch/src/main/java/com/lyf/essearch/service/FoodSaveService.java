package com.lyf.essearch.service;

import com.lyf.common.model.FoodInfosModel;
import com.lyf.common.model.FoodSpecialModel;
import com.lyf.common.model.TopicInfosModel;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/7/17-0:21
 */
public interface FoodSaveService {
    boolean saveinfo(FoodInfosModel foodInfosModel) throws IOException;

    boolean savespecial(FoodSpecialModel foodSpecialModel) throws IOException;

    boolean saveTopic(TopicInfosModel topicInfosModel) throws IOException;
}
