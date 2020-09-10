package com.lyf.essearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.lyf.common.model.FoodSpecialModel;
import com.lyf.essearch.config.LyfEsConfig;
import com.lyf.essearch.constant.EsConstant;
import com.lyf.essearch.service.SpecialService;
import com.lyf.essearch.vo.SpecialParam;
import com.lyf.essearch.vo.SpecialResponseVo;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyf
 * @date 2020/8/23-23:11
 */
@Service
public class SpecialServiceImpl implements SpecialService {
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Override
    public SpecialResponseVo search(SpecialParam specialParam)  {
        SpecialResponseVo responseVo = null;
        //1，准备检索请求
        SearchRequest searchRequest = buildSearchRequest(specialParam);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            responseVo = buildSearchResponse(search,specialParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseVo;
    }

    private SpecialResponseVo buildSearchResponse(SearchResponse search, SpecialParam specialParam) {
        SpecialResponseVo specialResponseVo = new SpecialResponseVo();
        SearchHits hits = search.getHits();
        List<FoodSpecialModel> list = new ArrayList<>();
        if(hits.getHits()!=null&&hits.getHits().length>0){
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                FoodSpecialModel foodSpecialModel = JSON.parseObject(sourceAsString, FoodSpecialModel.class);
                if(!StringUtils.isEmpty(specialParam.getKeyword())){
                    HighlightField title = hit.getHighlightFields().get("name");
                    String string = title.getFragments()[0].string();
                    foodSpecialModel.setName(string);
                }
                list.add(foodSpecialModel);
            }
        }
        specialResponseVo.setFoodSpecialModels(list);
        specialResponseVo.setPageNum(specialParam.getPageNum());
        //封装总记录数
        long totla = hits.getTotalHits().value;
        specialResponseVo.setTotal(totla);
        int totalPages = (int) totla%EsConstant.FOODSPECIAL_SIZE == 0?(int)totla/EsConstant.FOODSPECIAL_SIZE:((int) totla/EsConstant.FOODSPECIAL_SIZE+1);
        specialResponseVo.setTotalPages(totalPages);
        return specialResponseVo;
    }

    private SearchRequest buildSearchRequest(SpecialParam specialParam) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(specialParam.getKeyword())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("name",specialParam.getKeyword()));
        }
        builder.query(boolQueryBuilder);
        if (!StringUtils.isEmpty(specialParam.getSort())){
            builder.sort(specialParam.getSort(), SortOrder.DESC);
        }
        if (!StringUtils.isEmpty(specialParam.getKeyword())){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("name");
            highlightBuilder.preTags("<b style='color:#ff6767'>");
            highlightBuilder.postTags("</b>");
            builder.highlighter(highlightBuilder);
        }
        builder.from((specialParam.getPageNum()-1)* EsConstant.FOODSPECIAL_SIZE);
        builder.size(EsConstant.FOODSPECIAL_SIZE);
        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.FOODSPECIAL_INDEX}, builder);
        return searchRequest;
    }


}
