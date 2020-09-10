package com.lyf.essearch.service;

import com.lyf.essearch.vo.TopicParam;
import com.lyf.essearch.vo.TopicResponseVo;

/**
 * @author lyf
 * @date 2020/8/25-1:52
 */
public interface TopicService {
    TopicResponseVo search(TopicParam topicParam);
}

