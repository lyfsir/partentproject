package com.lyf.check.food.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodAttrDao;
import com.lyf.check.food.entity.FoodAttrEntity;
import com.lyf.check.food.service.FoodAttrService;


@Service("foodAttrService")
public class FoodAttrServiceImpl extends ServiceImpl<FoodAttrDao, FoodAttrEntity> implements FoodAttrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodAttrEntity> page = this.page(
                new Query<FoodAttrEntity>().getPage(params),
                new QueryWrapper<FoodAttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FoodAttrEntity> selectAttrByFId(Integer fid, Integer agid) {
        List<FoodAttrEntity> attrs = this.baseMapper.selectList(new QueryWrapper<FoodAttrEntity>()
                .eq("attgroup_id",agid)
                .eq("food_id", fid));

        return attrs;
    }

}