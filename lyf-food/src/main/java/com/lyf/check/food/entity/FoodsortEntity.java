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
@TableName("t_foodsort")
public class FoodsortEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 食材id
	 */
	@TableId
	private Integer cid;
	/**
	 * 食材名称
	 */
	private String name;
	/**
	 * 父级i食材d
	 */
	private Integer parentId;
	/**
	 * 食材层级
	 */
	private Integer level;
	/**
	 * 显示[1显示，0不显示]
	 */
	private Integer status;
	/**
	 * 食材排序
	 */
	private Integer sort;

}
