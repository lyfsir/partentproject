package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodAttrGroupEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:50
 */
public interface FoodAttrGroupService extends IService<FoodAttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

