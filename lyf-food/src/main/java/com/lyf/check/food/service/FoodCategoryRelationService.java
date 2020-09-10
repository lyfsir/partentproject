package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.food.vo.FoodCateVo;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodCategoryRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:50
 */
public interface FoodCategoryRelationService extends IService<FoodCategoryRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveCategory(Integer id, String title, List<FoodCateVo> category);

}

