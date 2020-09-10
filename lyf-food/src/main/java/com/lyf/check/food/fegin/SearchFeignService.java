package com.lyf.check.food.fegin;

import com.lyf.common.model.FoodInfosModel;
import com.lyf.common.model.FoodSpecialModel;
import com.lyf.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lyf
 * @date 2020/7/17-0:57
 */
@FeignClient("lyf-essearch")
public interface SearchFeignService {
    @PostMapping("/search/save/foodinfo")
    R foodSave(@RequestBody FoodInfosModel foodInfosModel);

    @DeleteMapping("/search/del/doDel")
    R delfoods(@RequestBody Integer[] ids);

    @PostMapping("/search/save/foodspecial")
    public R foodSave(@RequestBody FoodSpecialModel foodSpecialModel);

    @DeleteMapping("/search/del/delspecial")
    public R delspecial(@RequestBody Integer[] ids);
}
