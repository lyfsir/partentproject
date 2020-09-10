package com.lyf.check.food.service.impl;

import com.lyf.check.food.vo.FoodCateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodCategoryRelationDao;
import com.lyf.check.food.entity.FoodCategoryRelationEntity;
import com.lyf.check.food.service.FoodCategoryRelationService;


@Service("foodCategoryRelationService")
public class FoodCategoryRelationServiceImpl extends ServiceImpl<FoodCategoryRelationDao, FoodCategoryRelationEntity> implements FoodCategoryRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodCategoryRelationEntity> page = this.page(
                new Query<FoodCategoryRelationEntity>().getPage(params),
                new QueryWrapper<FoodCategoryRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveCategory(Integer fid,String fname, List<FoodCateVo> cname ) {
        if(cname == null || cname.size() ==0){

        }else {
            List<FoodCategoryRelationEntity> collect = cname.stream().map(value -> {
                FoodCategoryRelationEntity entity = new FoodCategoryRelationEntity();
                BeanUtils.copyProperties(value,entity);
                entity.setCategoryName(value.getCateName());
                entity.setFoodId(fid);
                entity.setFoodName(fname);
                return entity;
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }

}