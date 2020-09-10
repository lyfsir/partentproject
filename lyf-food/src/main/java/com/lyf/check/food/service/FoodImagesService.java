package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.food.vo.FoodImagesVo;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:51
 */
public interface FoodImagesService extends IService<FoodImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveImages(Integer id, List<FoodImagesVo> foodimages);
}

