package com.lyf.check.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.ums.entity.UmsMemberCollecfoodEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-04 00:22:43
 */
public interface UmsMemberCollecfoodService extends IService<UmsMemberCollecfoodEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

