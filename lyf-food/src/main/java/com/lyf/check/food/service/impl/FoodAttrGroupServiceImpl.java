package com.lyf.check.food.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodAttrGroupDao;
import com.lyf.check.food.entity.FoodAttrGroupEntity;
import com.lyf.check.food.service.FoodAttrGroupService;


@Service("foodAttrGroupService")
public class FoodAttrGroupServiceImpl extends ServiceImpl<FoodAttrGroupDao, FoodAttrGroupEntity> implements FoodAttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodAttrGroupEntity> page = this.page(
                new Query<FoodAttrGroupEntity>().getPage(params),
                new QueryWrapper<FoodAttrGroupEntity>()
        );

        return new PageUtils(page);
    }

}