package com.lyf.check.ums.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyf.check.ums.entity.UmsMemberCollecfoodEntity;
import com.lyf.check.ums.service.UmsMemberCollecfoodService;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.R;



/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-04 00:22:43
 */
@RestController
@RequestMapping("ums/umsmembercollecfood")
public class UmsMemberCollecfoodController {
    @Autowired
    private UmsMemberCollecfoodService umsMemberCollecfoodService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = umsMemberCollecfoodService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UmsMemberCollecfoodEntity umsMemberCollecfood = umsMemberCollecfoodService.getById(id);

        return R.ok().put("umsMemberCollecfood", umsMemberCollecfood);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UmsMemberCollecfoodEntity umsMemberCollecfood){
		umsMemberCollecfoodService.save(umsMemberCollecfood);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UmsMemberCollecfoodEntity umsMemberCollecfood){
		umsMemberCollecfoodService.updateById(umsMemberCollecfood);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		umsMemberCollecfoodService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
