package com.lyf.check.topic.service.impl;

import com.lyf.check.topic.fegin.SearchFeginService;
import com.lyf.check.topic.fegin.UmsMemberFegin;
import com.lyf.check.topic.service.TopicImagesService;
import com.lyf.check.topic.vo.ImagesVo;
import com.lyf.check.topic.vo.TopicVo;
import com.lyf.common.exception.MyEsException;
import com.lyf.common.model.TopicInfosModel;
import com.lyf.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyf.common.utils.PageUtils;
import com.lyf.common.utils.Query;

import com.lyf.check.topic.dao.TopicInfoDao;
import com.lyf.check.topic.entity.TopicInfoEntity;
import com.lyf.check.topic.service.TopicInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("topicInfoService")
public class TopicInfoServiceImpl extends ServiceImpl<TopicInfoDao, TopicInfoEntity> implements TopicInfoService {
    @Autowired
    UmsMemberFegin umsMemberFegin;
    @Autowired
    TopicImagesService topicImagesService;
    @Autowired
    SearchFeginService searchFeginService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TopicInfoEntity> page = this.page(
                new Query<TopicInfoEntity>().getPage(params),
                new QueryWrapper<TopicInfoEntity>()
        );
        List<TopicInfoEntity> list = page.getRecords().stream().map(value -> {
            R r = umsMemberFegin.username(value.getUId());
            String username = (String) r.get("username");
            value.setUserName(username);
            return value;
        }).collect(Collectors.toList());
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void savetopic(TopicVo topicVo) {
        TopicInfoEntity infoEntity = new TopicInfoEntity();
        infoEntity.setContent(topicVo.getContent());
        LocalDate now = LocalDate.now();
        infoEntity.setCreateTime(String.valueOf(now));
        infoEntity.setUId(topicVo.getUid());
        infoEntity.setUserName(topicVo.getUserName());
        this.save(infoEntity);
        List<ImagesVo> images = topicVo.getImages();
        topicImagesService.saveImages(infoEntity.getId(),images);
        TopicInfosModel infosModel = new TopicInfosModel();
        infosModel.setId((long)infoEntity.getId());
        infosModel.setContent(topicVo.getContent());
        // 获取头像信息
        // return R.ok().put("username", user[0]).put("logo",user[1]);
        R r = umsMemberFegin.username(topicVo.getUid());
        String logo = (String) r.get("logo");
        if (StringUtils.isEmpty(logo)){
            infosModel.setLogo("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
        }else {
            infosModel.setLogo(logo);
        }
        infosModel.setCreateTime(String.valueOf(now));
        infosModel.setUsername(topicVo.getUserName());
        List<TopicInfosModel.Image> collect = images.stream().map(value -> {
            TopicInfosModel.Image image = new TopicInfosModel.Image();
            String image1 = value.getImage();
            image.setImageurl(image1);
            return image;
        }).collect(Collectors.toList());
        infosModel.setImage(collect);
        R r1 = searchFeginService.topicsave(infosModel);
        if ((int) r1.get("code") != 0) {
            // ES失败，抛出异常
            throw new MyEsException();
        }
    }

}