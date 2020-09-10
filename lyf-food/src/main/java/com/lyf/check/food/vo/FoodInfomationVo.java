package com.lyf.check.food.vo;

import com.lyf.check.food.entity.FoodAttrEntity;
import com.lyf.check.food.entity.FoodImagesEntity;
import com.lyf.check.food.entity.FoodStepEntity;
import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author lyf
 * @date 2020/7/2-2:59
 */
@Data
public class FoodInfomationVo {
    private Integer id;

    /**
     * 食物标题
     */
    @NotNull
    private String title;
    /**
     * 食物描述
     */
    @NotNull
    private String descrit;
    /**
     * 用户id
     */
    private Integer userId;

    //用户信息
    private String userName;

    //用户头像
    private String logo;


    @NotNull
    private List<FoodImagesVo> foodimages;

    //获取主料属性
    @NotNull
    private List<FoodAttrEntity> domains;

    //获取辅料属性
    private List<FoodAttrEntity> doless;

    //获取其他属性
    private List<FoodAttrEntity> domore;


    @NotNull
    private List<FoodStepVo> foodSteps;


    /**
     * 审核状态
     */
    private Integer auditing;

}
