package io.renren.modules.food.controller;

import io.renren.common.utils.R;
import io.renren.fegin.FoodSortFegin;
import io.renren.modules.food.vo.SortVo;
import io.renren.modules.app.annotation.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyf
 * @date 2020/8/4-2:57
 */
@RestController
@RequestMapping("/food/sort")
public class FoodSortController {
    @Autowired
    FoodSortFegin foodFegin;

    @GetMapping("doget/sort")
    public R doGet(){
        R treeList = foodFegin.getTreeList();
        return R.ok().put("data",treeList);
    }

    @Login
    @DeleteMapping("del/ids")
    public R delete(@RequestBody Integer[] ids){
        foodFegin.delete(ids);
        return R.ok();
    }

    @Login
    @PutMapping("edit/sort")
    public R edit(@RequestBody SortVo sort){
        foodFegin.update(sort);
        return R.ok();
    }

    @Login
    @PostMapping("add/sort")
    public R add(@RequestBody SortVo sort){
        foodFegin.save(sort);
        return R.ok();
    }


}
