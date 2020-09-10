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
@TableName("food_attr_group")
public class FoodAttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性组id
	 */
	@TableId
	private Integer id;
	/**
	 * 属性组名
	 */
	private String groupName;
	/**
	 * 0不可被检索，1可被检索
	 */
	private Integer search;

}
