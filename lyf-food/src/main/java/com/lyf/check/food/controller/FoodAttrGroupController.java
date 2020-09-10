package com.lyf.check.food.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyf.check.food.entity.FoodAttrGroupEntity;
import com.lyf.check.food.service.FoodAttrGroupService;
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
@RequestMapping("food/foodattrgroup")
public class FoodAttrGroupController {
    @Autowired
    private FoodAttrGroupService foodAttrGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = foodAttrGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		FoodAttrGroupEntity foodAttrGroup = foodAttrGroupService.getById(id);

        return R.ok().put("foodAttrGroup", foodAttrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodAttrGroupEntity foodAttrGroup){
		foodAttrGroupService.save(foodAttrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FoodAttrGroupEntity foodAttrGroup){
		foodAttrGroupService.updateById(foodAttrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		foodAttrGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
