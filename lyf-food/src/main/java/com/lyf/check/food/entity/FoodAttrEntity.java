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
 * @date 2020-08-03 23:50:50
 */
@Data
@TableName("food_attr")
public class FoodAttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 食物属性id
	 */
	@TableId
	private Integer id;
	/**
	 * 食物id
	 */
	private Integer foodId;
	/**
	 * 属性组id
	 */
	private Integer attgroupId;
	/**
	 * 属性名
	 */
	private String attrName;
	/**
	 * 属性值
	 */
	private String attrValue;

}
