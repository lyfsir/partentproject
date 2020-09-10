package com.lyf.check.food.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyf.check.food.entity.FoodAttrEntity;
import com.lyf.check.food.service.FoodAttrService;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.R;



/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-03 23:50:50
 */
@RestController
@RequestMapping("food/foodattr")
public class FoodAttrController {
    @Autowired
    private FoodAttrService foodAttrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = foodAttrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		FoodAttrEntity foodAttr = foodAttrService.getById(id);

        return R.ok().put("foodAttr", foodAttr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodAttrEntity foodAttr){
		foodAttrService.save(foodAttr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FoodAttrEntity foodAttr){
		foodAttrService.updateById(foodAttr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		foodAttrService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
