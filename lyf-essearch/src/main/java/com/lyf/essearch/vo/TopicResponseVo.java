package com.lyf.essearch.vo;

import com.lyf.common.model.TopicInfosModel;
import lombok.Data;

import java.util.List;

/**
 * @author lyf
 * @date 2020/8/25-1:50
 */
@Data
public class TopicResponseVo {
    private List<TopicInfosModel> topicInfosModels;
    //分页信息
    private Integer pageNum; // 当前页吗
    private Long total; // 总记录数
    private Integer totalPages; // 总页码
}
