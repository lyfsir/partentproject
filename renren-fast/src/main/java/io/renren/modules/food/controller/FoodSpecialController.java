package io.renren.modules.food.controller;

import io.renren.common.utils.R;
import io.renren.fegin.FoodSortFegin;
import io.renren.modules.food.vo.FoodSpecialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lyf
 * @date 2020/8/5-19:14
 */
@RestController
@RequestMapping("/food/special")
public class FoodSpecialController {
    @Autowired
    FoodSortFegin foodSortFegin;
    @GetMapping("/doget")
    public R doGet(){
        R r = foodSortFegin.doGet();
        return R.ok().put("data",r.get("data"));
    }

    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        R r = foodSortFegin.listto(params);
        return r.ok().put("page",r.get("page"));
    }

    @GetMapping("/info")
    public R getInfo(){
        R r = foodSortFegin.doGet();
        return R.ok().put("data",r.get("data"));
    }

    @PostMapping("/add")
    public R add(@RequestBody FoodSpecialVo vo){
        foodSortFegin.save(vo);
        return R.ok();
    }

    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        R info = foodSortFegin.info(id);
        return R.ok().put("foodSpecial",info.get("foodSpecial"));
    }

    @PostMapping("/update")
    public R update(@RequestBody FoodSpecialVo vo){
        foodSortFegin.update(vo);
        return R.ok();
    }

    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        foodSortFegin.deletesp(ids);
        return R.ok();
    }
}
