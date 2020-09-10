package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.food.vo.FoodInfomationVo;
import com.lyf.check.food.vo.GetFoodInfomationVo;
import com.lyf.check.food.vo.UpdatefoodVo;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.FoodInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:50
 */
public interface FoodInfoService extends IService<FoodInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Integer num);

    GetFoodInfomationVo selectInfomation(Integer foodId);

    void saveall(UpdatefoodVo updatefoodVo);

    void savealltoaut(FoodInfomationVo infomationVo);

    PageUtils queryPageTo(Map<String, Object> params);
}

