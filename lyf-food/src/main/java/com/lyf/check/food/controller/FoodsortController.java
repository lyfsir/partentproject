package com.lyf.check.food.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyf.check.food.entity.FoodsortEntity;
import com.lyf.check.food.service.FoodsortService;
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
@RequestMapping("food/foodsort")
public class FoodsortController {
    @Autowired
    private FoodsortService foodsortService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = foodsortService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cid}")
    public R info(@PathVariable("cid") Integer cid){
		FoodsortEntity foodsort = foodsortService.getById(cid);

        return R.ok().put("foodsort", foodsort);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodsortEntity foodsort){
		foodsortService.save(foodsort);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FoodsortEntity foodsort){
		foodsortService.updateById(foodsort);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] cids){
		foodsortService.removeByIds(Arrays.asList(cids));

        return R.ok();
    }

}
