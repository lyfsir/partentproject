package com.lyf.essearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.lyf.common.model.FoodSpecialModel;
import com.lyf.common.model.TopicInfosModel;
import com.lyf.essearch.constant.EsConstant;
import com.lyf.essearch.service.TopicService;
import com.lyf.essearch.vo.SpecialResponseVo;
import com.lyf.essearch.vo.TopicParam;
import com.lyf.essearch.vo.TopicResponseVo;
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
 * @date 2020/8/25-1:53
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public TopicResponseVo search(TopicParam topicParam) {
        TopicResponseVo responseVo = null;
        //1，准备检索请求
        SearchRequest searchRequest = buildSearchRequest(topicParam);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            responseVo = buildSearchResponse(search,topicParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseVo;
    }

    private TopicResponseVo buildSearchResponse(SearchResponse search, TopicParam topicParam) {
        TopicResponseVo topicResponseVo = new TopicResponseVo();
        SearchHits hits = search.getHits();
        List<TopicInfosModel> list = new ArrayList<>();
        if(hits.getHits()!=null&&hits.getHits().length>0){
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                TopicInfosModel topicInfosModel = JSON.parseObject(sourceAsString, TopicInfosModel.class);
                if(!StringUtils.isEmpty(topicParam.getKeyword())){
                    HighlightField title = hit.getHighlightFields().get("content");
                    String string = title.getFragments()[0].string();
                    topicInfosModel.setContent(string);
                }
                list.add(topicInfosModel);
            }
        }
        topicResponseVo.setTopicInfosModels(list);
        topicResponseVo.setPageNum(topicParam.getPageNum());
        //封装总记录数
        long totla = hits.getTotalHits().value;
        topicResponseVo.setTotal(totla);
        int totalPages = (int) totla%EsConstant.TOPICINFO_SIZE == 0?(int)totla/EsConstant.TOPICINFO_SIZE:((int) totla/EsConstant.TOPICINFO_SIZE+1);
        topicResponseVo.setTotalPages(totalPages);
        return topicResponseVo;
    }

    private SearchRequest buildSearchRequest(TopicParam topicParam) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(topicParam.getKeyword())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("content",topicParam.getKeyword()));
        }
        if (!StringUtils.isEmpty(topicParam.getUserName())){
            boolQueryBuilder.filter(QueryBuilders.matchQuery("username", topicParam.getUserName()));
        }
        builder.query(boolQueryBuilder);
        if (!StringUtils.isEmpty(topicParam.getSort())){
            builder.sort(topicParam.getSort(), SortOrder.DESC);
        }

        if (!StringUtils.isEmpty(topicParam.getKeyword())){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("content");
            highlightBuilder.preTags("<b style='color:#ff6767'>");
            highlightBuilder.postTags("</b>");
            builder.highlighter(highlightBuilder);
        }
        builder.from((topicParam.getPageNum()-1)* EsConstant.TOPICINFO_SIZE);
        builder.size(EsConstant.TOPICINFO_SIZE);
        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.TOPICINFO_INDEX}, builder);
        return searchRequest;
    }
}
