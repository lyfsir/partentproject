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
@TableName("lyf_tousermsg")
public class LyfTousermsgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 對應的用戶id
	 */
	private Integer userid;
	/**
	 * 此用戶的信息
	 */
	private String msg;
	/**
	 * 此消息的状态,0代表已读，1代表未读
	 */
	private Integer status;

}
