package com.lyf.check.food.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodStepDao;
import com.lyf.check.food.entity.FoodStepEntity;
import com.lyf.check.food.service.FoodStepService;


@Service("foodStepService")
public class FoodStepServiceImpl extends ServiceImpl<FoodStepDao, FoodStepEntity> implements FoodStepService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodStepEntity> page = this.page(
                new Query<FoodStepEntity>().getPage(params),
                new QueryWrapper<FoodStepEntity>()
        );

        return new PageUtils(page);
    }

}