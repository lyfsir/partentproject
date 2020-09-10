package com.lyf.essearch.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/8/25-1:49
 */
@Data
public class TopicParam {
    private String keyword; //页面传递过来的全文匹配关键字
    private String userName;
    private String sort; //排序条件
    private Integer pageNum = 1; //页码(默认进来就是第一页)
}
