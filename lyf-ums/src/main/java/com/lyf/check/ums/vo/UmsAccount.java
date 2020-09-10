package com.lyf.check.ums.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/8/18-16:46
 */
@Data
public class UmsAccount {
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String header;

    /**
     * headers的token信息
     */
    private String token;
}
