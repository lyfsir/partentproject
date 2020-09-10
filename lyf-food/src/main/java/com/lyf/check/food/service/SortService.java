package com.lyf.check.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.food.entity.SortEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:51
 */
public interface SortService extends IService<SortEntity> {

    List<SortEntity> listTree();
    void ifremoveByIds(List<Integer> asList);


    PageUtils queryPage(Map<String, Object> params);

    List<SortEntity> getredisSort();

    List<SortEntity> listTreeByid(Integer id);
}

