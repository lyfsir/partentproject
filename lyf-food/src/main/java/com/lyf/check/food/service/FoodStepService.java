package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodStepEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:50
 */
public interface FoodStepService extends IService<FoodStepEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

