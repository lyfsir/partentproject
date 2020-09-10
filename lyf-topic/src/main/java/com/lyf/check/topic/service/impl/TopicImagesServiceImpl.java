package com.lyf.check.topic.service.impl;

import com.lyf.check.topic.vo.ImagesVo;
import com.lyf.check.topic.vo.TopicVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.topic.dao.TopicImagesDao;
import com.lyf.check.topic.entity.TopicImagesEntity;
import com.lyf.check.topic.service.TopicImagesService;


@Service("topicImagesService")
public class TopicImagesServiceImpl extends ServiceImpl<TopicImagesDao, TopicImagesEntity> implements TopicImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TopicImagesEntity> page = this.page(
                new Query<TopicImagesEntity>().getPage(params),
                new QueryWrapper<TopicImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveImages(Integer tid, List<ImagesVo> vo) {
        if(vo == null || vo.size() ==0){

        }else {
            List<TopicImagesEntity> collect = vo.stream().map(value -> {
                TopicImagesEntity entity = new TopicImagesEntity();
                entity.setImage(value.getImage());
                entity.setTId(tid);
                return entity;
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }

}