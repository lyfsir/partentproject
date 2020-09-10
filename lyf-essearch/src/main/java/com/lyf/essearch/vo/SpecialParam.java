package com.lyf.essearch.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/8/23-1:14
 */
@Data
public class SpecialParam {
    private String keyword;
    private String sort;
    private Integer pageNum = 1; //页码(默认进来就是第一页)
}
