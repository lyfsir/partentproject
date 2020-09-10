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
 * @date 2020-08-03 23:50:51
 */
@Data
@TableName("food_images")
public class FoodImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 食物图片id
	 */
	@TableId
	private Integer id;
	/**
	 * 食物id
	 */
	private Integer foodId;
	/**
	 * 图片地址
	 */
	private String imagesUrl;
	/**
	 * 0为默认图片，1为非默认图片
	 */
	private Integer type;

}
