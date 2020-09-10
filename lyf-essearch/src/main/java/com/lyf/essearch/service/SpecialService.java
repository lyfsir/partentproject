package com.lyf.essearch.service;

import com.lyf.essearch.vo.SpecialParam;
import com.lyf.essearch.vo.SpecialResponseVo;

import java.io.IOException;

/**
 * @author lyf
 * @date 2020/8/23-23:10
 */
public interface SpecialService {
    SpecialResponseVo search(SpecialParam specialParam) ;
}
