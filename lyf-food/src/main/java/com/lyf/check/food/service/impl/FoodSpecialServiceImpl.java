package com.lyf.check.food.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lyf.check.food.entity.FoodInfoEntity;
import com.lyf.check.food.entity.FoodSpecialInfomationEntity;
import com.lyf.check.food.fegin.SearchFeignService;
import com.lyf.check.food.service.FoodSpecialInfomationService;
import com.lyf.check.food.vo.FoodSpecialVo;
import com.lyf.check.food.vo.GetSpecialNamesVo;
import com.lyf.common.exception.MyEsException;
import com.lyf.common.model.FoodSpecialModel;
import com.lyf.common.utils.R;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodSpecialDao;
import com.lyf.check.food.entity.FoodSpecialEntity;
import com.lyf.check.food.service.FoodSpecialService;
import org.springframework.transaction.annotation.Transactional;


@Service("foodSpecialService")
public class FoodSpecialServiceImpl extends ServiceImpl<FoodSpecialDao, FoodSpecialEntity> implements FoodSpecialService {

    @Autowired
    FoodSpecialInfomationService foodSpecialInfomationService;

    @Autowired
    SearchFeignService searchFeignService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String specialName = (String)params.get("specialName");
        IPage<FoodSpecialEntity> page = this.page(
                new Query<FoodSpecialEntity>().getPage(params),
                new QueryWrapper<FoodSpecialEntity>().like("name",specialName)
        );

        return new PageUtils(page);
    }

    @Override
    public List<GetSpecialNamesVo> queryName() {
        List<FoodSpecialEntity> entities = this.getBaseMapper().selectList(null);
        List<GetSpecialNamesVo> collect = entities.stream().map(value -> {
            GetSpecialNamesVo vo = new GetSpecialNamesVo();
            vo.setId(value.getId());
            vo.setName(value.getName());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public void add(FoodSpecialVo vo) {
        FoodSpecialEntity foodSpecialEntity = new FoodSpecialEntity();
        BeanUtils.copyProperties(vo,foodSpecialEntity);
        LocalDate now = LocalDate.now();
        foodSpecialEntity.setCreateTime(String.valueOf(now));
        this.save(foodSpecialEntity);
        FoodSpecialInfomationEntity foodSpecialInfomationEntity = new FoodSpecialInfomationEntity();
        BeanUtils.copyProperties(vo,foodSpecialInfomationEntity);
        foodSpecialInfomationEntity.setSId(foodSpecialEntity.getId());
        foodSpecialInfomationEntity.setImgurl(vo.getImgurlBig());
        foodSpecialInfomationService.save(foodSpecialInfomationEntity);
        //TODO 将数据发送给es进行保存，远程调用lyf--search服务
        FoodSpecialModel foodSpecialModel = new FoodSpecialModel();
        foodSpecialModel.setId((long)foodSpecialEntity.getId());
        foodSpecialModel.setName(foodSpecialEntity.getName());
        foodSpecialModel.setImgurl(foodSpecialEntity.getImgurl());
        foodSpecialModel.setCreateTime(String.valueOf(now));
        R r1 = searchFeignService.foodSave(foodSpecialModel);
        if ((int) r1.get("code") != 0) {
            // ES失败，抛出异常
            throw new MyEsException();
        }
    }

    @Transactional
    @Override
    public void updateBySid(FoodSpecialVo vo) {
        FoodSpecialEntity foodSpecialEntity = new FoodSpecialEntity();
        BeanUtils.copyProperties(vo,foodSpecialEntity);
        LocalDate now = LocalDate.now();
        foodSpecialEntity.setCreateTime(String.valueOf(now));
        this.updateById(foodSpecialEntity);
        FoodSpecialInfomationEntity foodSpecialInfomationEntity = new FoodSpecialInfomationEntity();
        BeanUtils.copyProperties(vo,foodSpecialInfomationEntity);
        foodSpecialInfomationEntity.setImgurl(vo.getImgurlBig());
        foodSpecialInfomationEntity.setSId(foodSpecialEntity.getId());
        foodSpecialInfomationService.getBaseMapper().update(foodSpecialInfomationEntity,
                new QueryWrapper<FoodSpecialInfomationEntity>().eq("s_id",foodSpecialEntity.getId()));
        //TODO 将数据发送给es进行保存，远程调用lyf--search服务
        FoodSpecialModel foodSpecialModel = new FoodSpecialModel();
        foodSpecialModel.setId((long)foodSpecialEntity.getId());
        foodSpecialModel.setName(foodSpecialEntity.getName());
        foodSpecialModel.setImgurl(foodSpecialEntity.getImgurl());
        foodSpecialModel.setCreateTime(String.valueOf(now));
        R r1 = searchFeignService.foodSave(foodSpecialModel);
        if ((int) r1.get("code") != 0) {
            // ES失败，抛出异常
            throw new MyEsException();
        }
    }

    @Override
    public FoodSpecialVo getInfomatinoById(Integer id) {
        FoodSpecialEntity byId = this.getById(id);
        FoodSpecialVo vo = new FoodSpecialVo();
        BeanUtils.copyProperties(byId,vo);
        FoodSpecialInfomationEntity foodSpecialInfomationEntity = foodSpecialInfomationService.getBaseMapper().selectOne(
                new QueryWrapper<FoodSpecialInfomationEntity>().eq("s_id", byId.getId()));
        vo.setImgurlBig(foodSpecialInfomationEntity.getImgurl());
        vo.setContent(foodSpecialInfomationEntity.getContent());
        vo.setSId(foodSpecialInfomationEntity.getSId());
        return vo;
    }


}