package com.lyf.check.food.vo;

import com.lyf.check.food.entity.FoodAttrEntity;
import com.lyf.check.food.entity.FoodImagesEntity;
import com.lyf.check.food.entity.FoodStepEntity;
import lombok.Data;

import java.util.List;

/**
 * @author lyf
 * @date 2020/8/4-17:43
 */
@Data
public class GetFoodInfomationVo {
    private Integer id;

    /**
     * 食物标题
     */
    private String title;
    /**
     * 食物描述
     */
    private String descrit;
    /**
     * 用户id
     */
    private Integer userId;


    //用户信息
    private String userName;

    //用户头像
    private String logo;

    //创建时间
    private String createTime;

    //获取图片集
    private List<FoodImagesEntity> selfoodimages;
    private List<FoodAttrEntity> foodAttrs;
    //获取步骤做法集
    private List<FoodStepEntity> selfoodSteps;
    //所属分类
    private List<String> cateName; //审核未通过不用设置分类信息

    private String specialName; // 所属专题

}
