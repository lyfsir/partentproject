package com.lyf.essearch.vo;

import com.lyf.common.model.FoodSpecialModel;
import lombok.Data;

import java.util.List;

/**
 * @author lyf
 * @date 2020/8/23-1:15
 */
@Data
public class SpecialResponseVo {
    private List<FoodSpecialModel> foodSpecialModels;
    //分页信息
    private Integer pageNum; // 当前页吗
    private Long total; // 总记录数
    private Integer totalPages; // 总页码
}
