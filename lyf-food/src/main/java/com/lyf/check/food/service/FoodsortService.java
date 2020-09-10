package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodsortEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:51
 */
public interface FoodsortService extends IService<FoodsortEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

