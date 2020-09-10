package com.lyf.essearch.service;

import com.lyf.common.model.FoodInfosModel;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/8/6-19:27
 */
public interface FoodDelService {

    boolean delFood(Integer[] ids) throws IOException;

    boolean delSpecial(Integer[] ids) throws IOException;

    boolean delTopic(Integer[] ids) throws IOException;
}
