package com.lyf.check.food.service.impl;

import com.lyf.check.food.entity.*;
import com.lyf.check.food.fegin.SearchFeignService;
import com.lyf.check.food.fegin.UmsMemberService;
import com.lyf.check.food.service.*;
import com.lyf.check.food.vo.*;
import com.lyf.common.model.FoodInfosModel;
import com.lyf.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.food.dao.FoodInfoDao;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("foodInfoService")
public class FoodInfoServiceImpl extends ServiceImpl<FoodInfoDao, FoodInfoEntity> implements FoodInfoService {
    @Autowired
    UmsMemberService umsMemberService;

    @Autowired
    FoodAttrGroupService foodAttrGroupService ;

    @Autowired
    FoodStepService foodStepService;

    @Autowired
    FoodImagesService foodImagesService;



    @Autowired
    FoodAttrService foodAttrService;

    @Autowired
    FoodCategoryRelationService foodCategoryRelationService;

    @Autowired
    SearchFeignService searchFeignService;

    @Autowired
    FoodSpecialService foodSpecialService;

    /**
     * 保存待审核的信息
     *
     * @param infomationVo
     * @return
     */
    @Override
    @Transactional
    public void savealltoaut(FoodInfomationVo infomationVo) {
        infomationVo.setAuditing(1);
        //保存基础信息
        FoodInfoEntity info = new FoodInfoEntity();
        BeanUtils.copyProperties(infomationVo, info);
        LocalDate now = LocalDate.now();
        info.setCreateTime(String.valueOf(now));
        this.save(info);
        //保存图片集
        List<FoodImagesVo> foodimages = infomationVo.getFoodimages();
        foodImagesService.saveImages(info.getId(),foodimages);

        //保存主料属性集
        List<FoodAttrEntity> foodAttrs = infomationVo.getDomains();
        List<FoodAttrEntity> collect = foodAttrs.stream().map(value -> {
            FoodAttrEntity attrEntity = new FoodAttrEntity();
            attrEntity.setAttrName(value.getAttrName());
            attrEntity.setAttrValue(value.getAttrValue());
            attrEntity.setFoodId(info.getId());
            attrEntity.setAttgroupId(1);
            return attrEntity;
        }).collect(Collectors.toList());
        foodAttrService.saveBatch(collect);

        //保存辅料料属性集
        List<FoodAttrEntity> foodAttrs2 = infomationVo.getDoless();
        if (foodAttrs2!=null&&foodAttrs2.size()>0){
            List<FoodAttrEntity> collect2 = foodAttrs2.stream().map(value -> {
                FoodAttrEntity attrEntity = new FoodAttrEntity();
                attrEntity.setAttrName(value.getAttrName());
                attrEntity.setAttrValue(value.getAttrValue());
                attrEntity.setFoodId(info.getId());
                attrEntity.setAttgroupId(2);
                return attrEntity;
            }).collect(Collectors.toList());
            foodAttrService.saveBatch(collect2);
        }

        //保存其他属性集
        List<FoodAttrEntity> foodAttrs3 = infomationVo.getDomore();
        if (foodAttrs3!=null&&foodAttrs3.size()>0){
            List<FoodAttrEntity> collect3 = foodAttrs3.stream().map(value -> {
                FoodAttrEntity attrEntity = new FoodAttrEntity();
                attrEntity.setAttrName(value.getAttrName());
                attrEntity.setAttrValue(value.getAttrValue());
                attrEntity.setFoodId(info.getId());
                attrEntity.setAttgroupId(3);
                return attrEntity;
            }).collect(Collectors.toList());
            foodAttrService.saveBatch(collect3);
        }

        //保存步骤集
        List<FoodStepVo> foodSteps = infomationVo.getFoodSteps();
        List<FoodStepEntity> collect1 = foodSteps.stream().map(value -> {
            FoodStepEntity entity = new FoodStepEntity();
            entity.setContent(value.getContent());
            entity.setFoodId(info.getId());
            return entity;
        }).collect(Collectors.toList());
        foodStepService.saveBatch(collect1);
    }

    @Override
    public PageUtils queryPageTo(Map<String, Object> params) {

        return null;
    }

    /**
     * 保存全部信息（保存后台评审的信息）
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public void saveall(UpdatefoodVo vo) {
        System.out.println("---test-----"+vo.toString());
        //得到分类名list
        List<FoodCateVo> category1 = vo.getCategory();
        log.info("catefory1",category1);
        //修改数据库成功得到完整数据再进行es的保存工作

        //组装需要的es数据
        FoodInfosModel infosModel = new FoodInfosModel();
        //根据vo的id查询数据库的foodinfo表信息，通过id查到分类信息、可以被用来检索的属性信息、默认图片信息以及用户名信息，按热度来排序，因为还没弄，默认0
        FoodInfoEntity entity2 = this.baseMapper.selectById(vo.getId());
        infosModel.setId((long) entity2.getId());
        infosModel.setTitle(entity2.getTitle());
        List<FoodInfosModel.Category> collect = category1.stream().map(value -> {
            FoodInfosModel.Category category = new FoodInfosModel.Category();
            category.setCId((long) value.getCategoryId());
            category.setCName(value.getCateName());
            return category;
        }).collect(Collectors.toList());
        log.error("collect",collect);
        infosModel.setCategory(collect);
        //可以被用来检索的属性信息
        FoodAttrGroupEntity search = foodAttrGroupService.getBaseMapper().selectOne(new QueryWrapper<FoodAttrGroupEntity>().eq("search", "1"));
        if (search != null) {
            List<FoodAttrEntity> attrEntities = foodAttrService.selectAttrByFId(entity2.getId(), search.getId());
            List<FoodInfosModel.Attr> collect1 = attrEntities.stream().map(value -> {
                FoodInfosModel.Attr attrs = new FoodInfosModel.Attr();
                attrs.setAttrIid((long) value.getId());
                attrs.setAttrName(value.getAttrName());
                attrs.setAttrValue(value.getAttrValue());
                return attrs;
            }).collect(Collectors.toList());
            infosModel.setAttr(collect1);
        }

        //默认图片信息
        FoodImagesEntity imagesEntity = foodImagesService.getBaseMapper().selectOne(new QueryWrapper<FoodImagesEntity>().eq("food_id", entity2.getId())
                .eq("type", 0));
        String url = imagesEntity.getImagesUrl();
        infosModel.setImgurl(url);


        //用户名信息
        R r = umsMemberService.username(entity2.getUserId());
        String username = (String) r.get("username");
        infosModel.setUsername(username);

        //所属专题
        infosModel.setSId((long)vo.getSid());
        //当前时间
        LocalDate now = LocalDate.now();
        infosModel.setCreateTime(String.valueOf(now));
        System.out.println("model-----"+infosModel.toString());

        //TODO 将数据发送给es进行保存，远程调用lyf--search服务
        R r1 = searchFeignService.foodSave(infosModel);
        if ((int) r1.get("code") == 0) {
            // TODO 成功之后，修改数据库审核状态和保存分类信息 , 如果数据库操作失败，是不是应该把存进去的es中的food_id里的数据删去
            /**
             * 调用成功再执行以下修改数据库的操作
             */
            //因为数据库已经保存auditing为1的信息但是没有分类
            //修改auditing为0
            FoodInfoEntity entity = new FoodInfoEntity();
            //保存前台传来的分类信息
            foodCategoryRelationService.saveCategory(vo.getId(),vo.getTitle(),vo.getCategory());

            // 保存专题信息

            entity.setAuditing(0);
            entity.setSId(vo.getSid());
            entity.setCreateTime(String.valueOf(now));
            this.getBaseMapper().update(entity, new QueryWrapper<FoodInfoEntity>().eq("id", vo.getId()));

        } else {
            //TODO 远程调用失败，接口幂等性，重试机制
        }
    }

    @Override
    public GetFoodInfomationVo selectInfomation(Integer foodId) {
        GetFoodInfomationVo infomationVo = new GetFoodInfomationVo();
        //获取info信息
        FoodInfoEntity entity = this.baseMapper.selectOne(new QueryWrapper<FoodInfoEntity>().eq("id", foodId));
        if (entity!=null){
            BeanUtils.copyProperties(entity, infomationVo);
            //获取专题信息
            if (entity.getSId()!=null){
                FoodSpecialEntity specialEntity = foodSpecialService.getById(entity.getSId());
                if (specialEntity!=null){
                    infomationVo.setSpecialName(specialEntity.getName());
                }
            }

            //获取用户信息
            R user = umsMemberService.username(entity.getUserId());
            String username = (String) user.get("username");
            String logo = (String) user.get("logo");
            infomationVo.setUserName(username);
            infomationVo.setLogo(logo);
            //获取所属分类信息
            List<String> list = new ArrayList<>();
            List<FoodCategoryRelationEntity> foodCategoryRelationEntities = foodCategoryRelationService.getBaseMapper().selectList(
                    new QueryWrapper<FoodCategoryRelationEntity>().eq("food_id", entity.getId()));

            for (FoodCategoryRelationEntity entity1 : foodCategoryRelationEntities) {
                String categoryName = entity1.getCategoryName();
                list.add(categoryName);
            }
            infomationVo.setCateName(list);
            //获取图片集
            List<FoodImagesEntity> images = foodImagesService.getBaseMapper().selectList(
                    new QueryWrapper<FoodImagesEntity>().eq("food_id", entity.getId())
            );
            infomationVo.setSelfoodimages(images);

            //获取属性分组以及属性值
            List<FoodAttrEntity> attList = new ArrayList<>();
            List<FoodAttrGroupEntity> groupEntities =
                    foodAttrGroupService.getBaseMapper().selectList(new QueryWrapper<FoodAttrGroupEntity>());
            for (FoodAttrGroupEntity entity1 : groupEntities) {
                List<FoodAttrEntity> atts = foodAttrService.getBaseMapper().selectList(
                        new QueryWrapper<FoodAttrEntity>().eq("attgroup_id", entity1.getId())
                                .eq("food_id", entity.getId())
                );
                for (FoodAttrEntity entity2 : atts) {
                    attList.add(entity2);
                }
            }
            infomationVo.setFoodAttrs(attList);
            //获取步骤
            List<FoodStepEntity> stepList = foodStepService.getBaseMapper().selectList(new QueryWrapper<FoodStepEntity>()
                    .eq("food_id", entity.getId()));
            infomationVo.setSelfoodSteps(stepList);
            return infomationVo;
        }else{
            return null;
        }

    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer num) {
        String title = (String) params.get("title");
        IPage<FoodInfoEntity> page = this.page(
                new Query<FoodInfoEntity>().getPage(params),
                new QueryWrapper<FoodInfoEntity>().like("title",title).eq("auditing", num)
        );
        List<FoodInfoEntity> list = page.getRecords().stream().map(value -> {
            //获取用户信息
            Integer userId = value.getUserId();
            R info = umsMemberService.username(userId);
            String username = (String) info.get("username");
            value.setUserName(username);
            FoodImagesEntity imagesEntity = foodImagesService.getBaseMapper().selectOne(new QueryWrapper<FoodImagesEntity>().eq("food_id", value.getId())
                    .eq("type", 0));
            String imagesUrl = imagesEntity.getImagesUrl();
            value.setImages(imagesUrl);
            return value;
        }).collect(Collectors.toList());
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FoodInfoEntity> page = this.page(
                new Query<FoodInfoEntity>().getPage(params),
                new QueryWrapper<FoodInfoEntity>()
        );

        return new PageUtils(page);
    }

}