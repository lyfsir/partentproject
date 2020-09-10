package com.lyf.check.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.check.ums.vo.SocialityVo;
import com.lyf.check.ums.vo.UmsAccount;
import com.lyf.check.ums.vo.UmsLoginVo;
import com.lyf.check.ums.vo.UmsMember;
import com.lyf.common.utils.PageUtils;
import com.lyf.check.ums.entity.UmsMemberEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-04 00:22:43
 */
public interface UmsMemberService extends IService<UmsMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(UmsMember umsMember);

    String[] getusername(Integer id);

    UmsAccount login(UmsLoginVo umsLoginVo);

    UmsAccount login(SocialityVo socialityVo) throws Exception;
}

