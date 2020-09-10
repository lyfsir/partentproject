package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.food.vo.FoodSpecialVo;
import com.lyf.check.food.vo.GetSpecialNamesVo;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodSpecialEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-05 18:03:22
 */
public interface FoodSpecialService extends IService<FoodSpecialEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<GetSpecialNamesVo> queryName();

    void add(FoodSpecialVo vo);

    void updateBySid(FoodSpecialVo vo);

    FoodSpecialVo getInfomatinoById(Integer id);

}

