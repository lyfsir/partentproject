package com.lyf.check.food.entity;

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
 * @date 2020-08-05 18:03:22
 */
@Data
@TableName("food_special_infomation")
public class FoodSpecialInfomationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 专题详情id
	 */
	@TableId
	private Integer id;
	/**
	 * 详情图片
	 */
	private String imgurl;
	/**
	 * 详情内容
	 */
	private String content;
	/**
	 * 相关专题id
	 */
	private Integer sId;

}
