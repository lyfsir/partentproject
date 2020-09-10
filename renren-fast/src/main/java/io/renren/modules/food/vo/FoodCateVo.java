package io.renren.modules.food.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/7/17-4:57
 */
@Data
public class FoodCateVo {
    /**
     * 关联id
     */

    private Integer id;

    /**
     * 分类id
     */
    private Integer categoryId;

    private String cateName;
}
