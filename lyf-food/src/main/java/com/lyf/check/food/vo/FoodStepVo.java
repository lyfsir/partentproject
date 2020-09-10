package com.lyf.check.food.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/7/2-4:09
 */
@Data
public class FoodStepVo {
    /**
     * 步骤id
     */
    private Integer id;
    /**
     * 食物id
     */
    private Integer foodId;

    /**
     * 步骤讲解
     */
    private String content;
}
