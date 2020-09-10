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
@TableName("ums_member_statistics_info")
public class UmsMemberStatisticsInfoEntity implements Serializable {
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
	 * 关注数量
	 */
	private Integer attendCount;
	/**
	 * 粉丝数量
	 */
	private Integer fansCount;
	/**
	 * 收藏食物的数量
	 */
	private Integer collectFoodCount;

}
