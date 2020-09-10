package com.lyf.essearch.service;

import com.lyf.essearch.vo.SearchParam;
import com.lyf.essearch.vo.SearchResponseVo;
import com.lyf.essearch.vo.SpecialParam;
import com.lyf.essearch.vo.SpecialResponseVo;

/**
 * @author lyf
 * @date 2020/7/19-2:17
 */
public interface FoodSelectService {
    public SearchResponseVo search(SearchParam searchParam);

}
