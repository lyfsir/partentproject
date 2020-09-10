package com.lyf.check.ums.entity;

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
 * @date 2020-08-04 00:22:43
 */
@Data
@TableName("ums_member_collect_food")
public class UmsMemberCollecfoodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 会员id
	 */
	private Integer memberId;
	/**
	 * 收藏食物的id
	 */
	private Integer foodId;
	/**
	 * 收藏的时间
	 */
	private Date createTime;

}
