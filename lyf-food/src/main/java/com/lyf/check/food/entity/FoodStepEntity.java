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
@TableName("food_step")
public class FoodStepEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 步骤id
	 */
	@TableId
	private Integer id;
	/**
	 * 食物id
	 */
	private Integer foodId;
	/**
	 * 步骤讲解
	 */
	private String content;

}
