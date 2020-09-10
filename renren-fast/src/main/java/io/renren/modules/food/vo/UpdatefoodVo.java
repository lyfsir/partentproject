package io.renren.modules.food.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lyf
 * @date 2020/7/15-18:01
 */
@Data
public class UpdatefoodVo {
    /**
     * food_id
     */
    private Integer id;

    /**
     * food_name
     */
    private String title;

    /**
     * 分类信息
     */
    private List<FoodCateVo> category;

    /**
     * 专题信息
     */
    private Integer sid;
}
