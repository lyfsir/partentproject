package com.lyf.check.food.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2020-08-03 23:50:50
 */
@Data
@TableName("food_info")
public class FoodInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * food_id
	 */
	@TableId
	private Integer id;
	/**
	 * 食物标题
	 */
	private String title;
	/**
	 * 食物描述
	 */
	private String descrit;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 是否显示(0显示，1不显示)
	 */
	@TableLogic(value = "0" , delval = "1")
	private Integer status;
	/**
	 * 审核状态(0审核通过，1没通过，2表示不通过)
	 */
	private Integer auditing;

	/**
	 * 所属专题id
	 */
	private Integer sId;

//	/**
//	 * 创建时间
//	 */
	private String createTime;

	//展示默认图片
	@TableField(exist = false)
	private String images;

	//用户信息
	@TableField(exist = false)
	private String userName;

}
