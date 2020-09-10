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
@TableName("food_category_relation")
public class FoodCategoryRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联id
	 */
	@TableId
	private Integer id;
	/**
	 * 食物id
	 */
	private Integer foodId;
	/**
	 * 分类id
	 */
	private Integer categoryId;
	/**
	 * 食物名称
	 */
	private String foodName;
	/**
	 * 分类名称
	 */
	private String categoryName;

}
