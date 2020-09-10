package com.lyf.check.ums.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.ums.dao.LyfTousermsgDao;
import com.lyf.check.ums.entity.LyfTousermsgEntity;
import com.lyf.check.ums.service.LyfTousermsgService;


@Service("lyfTousermsgService")
public class LyfTousermsgServiceImpl extends ServiceImpl<LyfTousermsgDao, LyfTousermsgEntity> implements LyfTousermsgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LyfTousermsgEntity> page = this.page(
                new Query<LyfTousermsgEntity>().getPage(params),
                new QueryWrapper<LyfTousermsgEntity>()
        );

        return new PageUtils(page);
    }

}