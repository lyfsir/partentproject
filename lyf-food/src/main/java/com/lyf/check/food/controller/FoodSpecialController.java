package com.lyf.check.food.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lyf.check.food.fegin.SearchFeignService;
import com.lyf.check.food.vo.FoodSpecialVo;
import com.lyf.check.food.vo.GetSpecialNamesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lyf.check.food.entity.FoodSpecialEntity;
import com.lyf.check.food.service.FoodSpecialService;
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
@RequestMapping("food/foodspecial")
public class FoodSpecialController {
    @Autowired
    private FoodSpecialService foodSpecialService;

    @Autowired
    private SearchFeignService searchFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R listto(@RequestParam Map<String, Object> params){
        PageUtils page = foodSpecialService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 获取使用专题名字信息
     */
    @GetMapping("/info/name")
    public R doGet(){
        List<GetSpecialNamesVo> names = foodSpecialService.queryName();
        return R.ok().put("data",names);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		FoodSpecialVo vo = foodSpecialService.getInfomatinoById(id);

        return R.ok().put("foodSpecial", vo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodSpecialVo vo){
		foodSpecialService.add(vo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FoodSpecialVo vo){
		foodSpecialService.updateBySid(vo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R deletesp(@RequestBody Integer[] ids){
        R r = searchFeignService.delspecial(ids);
        if ((int) r.get("code") == 0) {
            foodSpecialService.removeByIds(Arrays.asList(ids));
            return R.ok();
        }
        else {
            return R.error((Integer) r.get("code"),(String) r.get("msg"));
        }
    }

}
