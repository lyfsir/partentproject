package com.lyf.check.topic.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/8/19-2:07
 */
@Data
public class ImagesVo {
    private Integer id;
    /**
     * 话题图片
     */
    private String image;
    /**
     * 话题id
     */
    private Integer tId;
}
