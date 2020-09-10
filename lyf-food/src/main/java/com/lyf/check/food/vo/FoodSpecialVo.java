package com.lyf.check.food.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/8/5-19:38
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


    private String createTime;
}
