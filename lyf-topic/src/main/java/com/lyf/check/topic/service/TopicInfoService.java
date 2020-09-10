package com.lyf.check.topic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.topic.vo.TopicVo;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.topic.entity.TopicInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-08 08:55:30
 */
public interface TopicInfoService extends IService<TopicInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void savetopic(TopicVo topicVo);
}

