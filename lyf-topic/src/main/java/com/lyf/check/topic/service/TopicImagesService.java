package com.lyf.check.topic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.topic.vo.ImagesVo;
import com.lyf.check.topic.vo.TopicVo;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.topic.entity.TopicImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-08 08:55:31
 */
public interface TopicImagesService extends IService<TopicImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
    public void saveImages(Integer tid, List<ImagesVo> vo);
}

