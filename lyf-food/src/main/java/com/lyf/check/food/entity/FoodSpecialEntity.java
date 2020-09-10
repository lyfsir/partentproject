package com.lyf.check.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("food_special")
public class FoodSpecialEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 专题id
	 */
	@TableId
	private Integer id;
	/**
	 * 专题名字
	 */
	private String name;



	/**
	 * 专题图片
	 */
	private String imgurl;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 是否显示[1-不显示，0显示]
	 */
	@TableLogic(value = "0" , delval = "1")
	private Integer status;

}
