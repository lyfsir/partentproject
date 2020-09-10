package com.lyf.check.food.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyf.check.food.entity.SortEntity;
import com.lyf.check.food.service.SortService;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.R;
/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:51
 */
@RestController
@RequestMapping("food/sort")
public class SortController {
    @Autowired
    private SortService sortService;

    @RequestMapping("/list/tree")
    public R getTreeList(){
        List<SortEntity> sortEntities = sortService.listTree();
        return R.ok().put("data",sortEntities);
    }

    @RequestMapping("/list/treefromredis")
    public R getTreeListFromRedis(){
        List<SortEntity> sortEntities = sortService.getredisSort();
        return R.ok().put("data",sortEntities);
    }

    @RequestMapping("/list/tree/{id}")
    public R getTreeListById(@PathVariable("id") Integer id){
        List<SortEntity> sortEntities = sortService.listTreeByid(id);
        return R.ok().put("data",sortEntities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sortService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		SortEntity sort = sortService.getById(id);

        return R.ok().put("sort", sort);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SortEntity sort){
		sortService.save(sort);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SortEntity sort){
		sortService.updateById(sort);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		sortService.ifremoveByIds(Arrays.asList(ids));

        return R.ok();
    }

}
