package io.renren.modules.food.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/8/4-3:10
 */
@Data
public class SortVo {
    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private Integer parentId;
    /**
     *
     */
    private Integer level;
    /**
     *
     */
    private Integer showStatus;
    /**
     *
     */
    private Integer sort;
    /**
     *
     */
    private String icon;
}
