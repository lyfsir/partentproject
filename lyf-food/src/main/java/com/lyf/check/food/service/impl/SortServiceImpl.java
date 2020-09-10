package com.lyf.check.food.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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

import com.lyf.check.food.dao.SortDao;
import com.lyf.check.food.entity.SortEntity;
import com.lyf.check.food.service.SortService;


@Service("sortService")
public class SortServiceImpl extends ServiceImpl<SortDao, SortEntity> implements SortService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void ifremoveByIds(List<Integer> asList) {
        //TODO 检查当前删除的菜单是否被引用
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 加入缓存的分类数据(实时性不高使用缓存)
     */
    @Override
    public List<SortEntity> getredisSort(){
        String catelogJSON = stringRedisTemplate.opsForValue().get("catelogJSON");
        if(StringUtils.isEmpty(catelogJSON)){
            List<SortEntity> sortEntities = listTreeFromDB();
            String s = JSON.toJSONString(sortEntities);
            stringRedisTemplate.opsForValue().set("catelogJSON",s,1, TimeUnit.DAYS);
            return sortEntities;
        }
        List<SortEntity> result = JSON.parseObject(catelogJSON,new TypeReference<List<SortEntity>>(){});
        return result;
    }


    @Override
    public List<SortEntity> listTreeByid(Integer id) {
        String catelogJSON = stringRedisTemplate.opsForValue().get("catelogJSON");
        if(StringUtils.isEmpty(catelogJSON)){
            List<SortEntity> list = listTreeFromDB();
            SortEntity entity = list.get(id);
            List<SortEntity> children = entity.getChildren();
            String s = JSON.toJSONString(list);
            stringRedisTemplate.opsForValue().set("catelogJSON",s,1, TimeUnit.DAYS);
            return children;
        }
        List<SortEntity> result = JSON.parseObject(catelogJSON,new TypeReference<List<SortEntity>>(){});
        SortEntity sortEntity = result.get(id);
        List<SortEntity> children = sortEntity.getChildren();
        return children;
    }

    /**
     * 从数据库查到的分类信息
     */
    public List<SortEntity> listTreeFromDB(){
        //因为springboot里面的所有组件都是单例的，所有用this锁住
        //TODO 使用的是本地锁，因为当前开发到只有此单台服务，当部署到多台服务器时，得用分布式锁
        synchronized (this){
            String catelogJSON = stringRedisTemplate.opsForValue().get("catelogJSON");
            if(!StringUtils.isEmpty(catelogJSON)){
                List<SortEntity> result = JSON.parseObject(catelogJSON,new TypeReference<List<SortEntity>>(){});
                return result;
            }
            List<SortEntity> sortEntities = baseMapper.selectList(null);
            List<SortEntity> level1Menus = sortEntities.stream().filter((sortEntity) -> {
                return sortEntity.getParentId() == 0;
            }).map((menu)->{
                menu.setChildren(getChildrens(menu,sortEntities));
                return menu;
            }).sorted((menu1,menu2)->{
                return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
            }).collect(Collectors.toList());

            return level1Menus;
        }

    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SortEntity> page = this.page(
                new Query<SortEntity>().getPage(params),
                new QueryWrapper<SortEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 后台管理系统的数据(要求实时性高，不用缓存)
     * @return
     */
    @Override
    public List<SortEntity> listTree() {
        List<SortEntity> sortEntities = baseMapper.selectList(null);
        List<SortEntity> level1Menus = sortEntities.stream().filter((sortEntity) -> {
            return sortEntity.getParentId() == 0;
        }).map((menu)->{
            menu.setChildren(getChildrens(menu,sortEntities));
            return menu;
        }).sorted((menu1,menu2)->{
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());

        return level1Menus;
    }

    //递归查找所有菜单的子菜单
    private List<SortEntity> getChildrens(SortEntity root,List<SortEntity> all){

        List<SortEntity> children = all.stream().filter((sortEntity) -> {
            return sortEntity.getParentId() == root.getId();
        }).map((sortEntity) -> {
            sortEntity.setChildren(getChildrens(sortEntity, all));
            return sortEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());

        return children;
    }

}