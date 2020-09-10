package com.lyf.check.topic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author lyf
 * @email 2653155409@qq.com
 * @date 2020-08-08 08:55:30
 */
@Data
@TableName("topic_info")
public class TopicInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 话题id
	 */
	@TableId
	private Integer id;
	/**
	 * 话题内容
	 */
	private String content;
	/**
	 * 话题状态(0显示，1不显示)
	 */
	private Integer status;
	/**
	 * 发表时间
	 */
	private String createTime;
	/**
	 * 用户id
	 */
	private Integer uId;

	//用户名称
	private String userName;

	@TableField(exist = false)
	private List<String> images;

}
