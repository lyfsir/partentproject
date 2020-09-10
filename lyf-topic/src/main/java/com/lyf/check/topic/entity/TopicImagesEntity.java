package com.lyf.check.topic.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-08 08:55:31
 */
@Data
@TableName("topic_images")
public class TopicImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 话题图片id
	 */
	@TableId
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
