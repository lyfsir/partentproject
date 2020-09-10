package com.lyf.check.food.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/7/2-4:09
 */
@Data
public class FoodImagesVo {
    private Integer id;
    /**
     * 食物id
     */
    private Integer foodId;
    /**
     * 图片地址
     */
    private String imagesUrl;
    /**
     * 0为默认图片，1为非默认图片
     */

    private Integer type;
}
