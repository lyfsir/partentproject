package io.renren.modules.food.controller;

import io.renren.common.utils.R;
import io.renren.fegin.FoodSortFegin;
import io.renren.modules.food.vo.UpdatefoodVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyf
 * @date 2020/8/4-18:39
 */
@RestController
@RequestMapping("/food/info")
public class FoodInfoController {
    @Autowired
    FoodSortFegin foodSortFegin;
    @GetMapping("doget/info/{foodId}")
    public R doget(@PathVariable Integer foodId){
        R r = foodSortFegin.selects(foodId);
        return R.ok().put("data",r.get("data"));
    }

    @PostMapping("save/food")
    public R save(@RequestBody UpdatefoodVo updatefoodVo){

        System.out.println("----testf---"+updatefoodVo.toString());
        foodSortFegin.save(updatefoodVo);
        return R.ok();
    }

    @DeleteMapping("del/food")
    public R del(@RequestBody Integer[] ids){
        foodSortFegin.deletes(ids);
        return R.ok();
    }
}
