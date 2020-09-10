package com.lyf.check.food.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodsortDao;
import com.lyf.check.food.entity.FoodsortEntity;
import com.lyf.check.food.service.FoodsortService;


@Service("foodsortService")
public class FoodsortServiceImpl extends ServiceImpl<FoodsortDao, FoodsortEntity> implements FoodsortService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodsortEntity> page = this.page(
                new Query<FoodsortEntity>().getPage(params),
                new QueryWrapper<FoodsortEntity>()
        );

        return new PageUtils(page);
    }

}