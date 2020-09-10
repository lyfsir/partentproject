package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.food.vo.SwiperVo;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodSpecialInfomationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-05 18:03:22
 */
public interface FoodSpecialInfomationService extends IService<FoodSpecialInfomationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SwiperVo> getSwiper();
}

