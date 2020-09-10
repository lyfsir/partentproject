package com.lyf.check.ums.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.ums.dao.UmsMemberCollecfoodDao;
import com.lyf.check.ums.entity.UmsMemberCollecfoodEntity;
import com.lyf.check.ums.service.UmsMemberCollecfoodService;


@Service("umsMemberCollecfoodService")
public class UmsMemberCollecfoodServiceImpl extends ServiceImpl<UmsMemberCollecfoodDao, UmsMemberCollecfoodEntity> implements UmsMemberCollecfoodService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UmsMemberCollecfoodEntity> page = this.page(
                new Query<UmsMemberCollecfoodEntity>().getPage(params),
                new QueryWrapper<UmsMemberCollecfoodEntity>()
        );

        return new PageUtils(page);
    }

}