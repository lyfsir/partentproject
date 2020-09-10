package com.lyf.check.ums.vo;

import lombok.Data;

/**
 * @author lyf
 * @date 2020/6/25-4:30
 */
@Data
public class SocialityVo {
    private String access_token;

    private String remind_in;

    private int expires_in;

    private String uid;

    private String isRealName;
}
