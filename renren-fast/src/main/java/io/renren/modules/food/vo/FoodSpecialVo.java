package io.renren.modules.food.vo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author lyf
 * @date 2020/8/5-19:46
 */
@Data
public class FoodSpecialVo {
    private Integer id;
    /**
     * 专题名字
     */
    private String name;
    /**
     * 专题图片
     */
    private String imgurl;

    /**
     * 详情图片
     */
    private String imgurlBig;
    /**
     * 详情内容
     */
    private String content;
    /**
     * 相关专题id
     */
    private Integer sId;
}
