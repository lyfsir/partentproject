package com.lyf.check.food.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyf.check.food.vo.SwiperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lyf.check.food.entity.FoodSpecialInfomationEntity;
import com.lyf.check.food.service.FoodSpecialInfomationService;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.R;



/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-05 18:03:22
 */
@RestController
@RequestMapping("food/foodspecialinfomation")
public class FoodSpecialInfomationController {
    @Autowired
    private FoodSpecialInfomationService foodSpecialInfomationService;

    /**
     * 获取轮播图
     */
    @GetMapping("/swiper")
    public R Swiper(){
        List<SwiperVo> list = foodSpecialInfomationService.getSwiper();
        return R.ok().put("data",list);
    }

    /**
     * 根据Sid获取对应数据
     */
    @GetMapping("/doGetBySid")
    public R doGetBySid(@RequestParam Integer sid){
        FoodSpecialInfomationEntity entity = foodSpecialInfomationService.getOne(new QueryWrapper<FoodSpecialInfomationEntity>().eq("s_id", sid));
        return R.ok().put("data",entity);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = foodSpecialInfomationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		FoodSpecialInfomationEntity foodSpecialInfomation = foodSpecialInfomationService.getById(id);

        return R.ok().put("foodSpecialInfomation", foodSpecialInfomation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodSpecialInfomationEntity foodSpecialInfomation){
		foodSpecialInfomationService.save(foodSpecialInfomation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FoodSpecialInfomationEntity foodSpecialInfomation){
		foodSpecialInfomationService.updateById(foodSpecialInfomation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		foodSpecialInfomationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
