package com.lyf.essearch.vo;

import com.lyf.common.model.FoodInfosModel;
import lombok.Data;

import java.util.List;

/**
 * @author lyf
 * @date 2020/7/18-23:45
 */

/**
 * 把检索到的数据返回给页面
 */

@Data
public class SearchResponseVo {
     private List<FoodInfosModel> food; // 查询到的所有信息

    //分页信息
    private Integer pageNum; // 当前页吗
    private Long total; // 总记录数
    private Integer totalPages; // 总页码

}
