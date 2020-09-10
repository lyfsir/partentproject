package com.lyf.check.food.service.impl;

import com.lyf.check.food.vo.FoodImagesVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodImagesDao;
import com.lyf.check.food.entity.FoodImagesEntity;
import com.lyf.check.food.service.FoodImagesService;


@Service("foodImagesService")
public class FoodImagesServiceImpl extends ServiceImpl<FoodImagesDao, FoodImagesEntity> implements FoodImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodImagesEntity> page = this.page(
                new Query<FoodImagesEntity>().getPage(params),
                new QueryWrapper<FoodImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveImages(Integer fid, List<FoodImagesVo> entities) {
        if(entities == null || entities.size() ==0){

        }else {
            List<FoodImagesEntity> collect = entities.stream().map(value -> {
                FoodImagesEntity entity = new FoodImagesEntity();
                entity.setImagesUrl(value.getImagesUrl());
                entity.setType(value.getType());
                entity.setFoodId(fid);
                return entity;
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }

}