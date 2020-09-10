package com.lyf.check.food.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lyf.check.food.entity.FoodSpecialEntity;
import com.lyf.check.food.vo.SwiperVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodSpecialInfomationDao;
import com.lyf.check.food.entity.FoodSpecialInfomationEntity;
import com.lyf.check.food.service.FoodSpecialInfomationService;


@Service("foodSpecialInfomationService")
public class FoodSpecialInfomationServiceImpl extends ServiceImpl<FoodSpecialInfomationDao, FoodSpecialInfomationEntity> implements FoodSpecialInfomationService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodSpecialInfomationEntity> page = this.page(
                new Query<FoodSpecialInfomationEntity>().getPage(params),
                new QueryWrapper<FoodSpecialInfomationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SwiperVo> getSwiper() {
        String swiperJSON = stringRedisTemplate.opsForValue().get("swipersJson");
        if (StringUtils.isEmpty(swiperJSON)) {
            List<SwiperVo> list = listSwiperDB();
            String s = JSON.toJSONString(list);
            stringRedisTemplate.opsForValue().set("swipersJson",s,1, TimeUnit.DAYS);
            return list;
        }
        List<SwiperVo> list = JSON.parseObject(swiperJSON, new TypeReference<List<SwiperVo>>() {
        });
        return list;

    }

    public List<SwiperVo> listSwiperDB(){
        synchronized (this){
            String swiperJSON = stringRedisTemplate.opsForValue().get("swipersJson");
            if (!StringUtils.isEmpty(swiperJSON)){
                List<SwiperVo> list = JSON.parseObject(swiperJSON, new TypeReference<List<SwiperVo>>() {
                });
                return list;
            }

            List<FoodSpecialInfomationEntity> entities = baseMapper.selectList(new QueryWrapper<FoodSpecialInfomationEntity>().orderByDesc("id").last("limit 0,6"));
            List<SwiperVo> collect = entities.stream().map(value -> {
                SwiperVo swiperVo = new SwiperVo();
                swiperVo.setImageUrl(value.getImgurl());
                swiperVo.setSid(value.getSId());
                return swiperVo;
            }).collect(Collectors.toList());
            return collect;
        }
    }

}