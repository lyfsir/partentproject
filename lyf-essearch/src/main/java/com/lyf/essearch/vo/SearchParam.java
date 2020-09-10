package com.lyf.essearch.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/7/18-23:39
 */

/**
 * 所有可能传过来的参数
 */
@Data
public class SearchParam {

    private String keyword; //页面传递过来的全文匹配关键字
    private String userName;
    private Long cateId; //二级分类id
    private Long sid; //专题id
    private String sort; //排序条件
    private String attrs; //属性
    private Integer pageNum = 1; //页码(默认进来就是第一页)

}
