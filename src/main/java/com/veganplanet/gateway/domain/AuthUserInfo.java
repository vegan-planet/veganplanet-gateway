package com.veganplanet.gateway.domain;

import lombok.Data;

/**
 * 用户信息
 *
 * @date 2023/12/21 22:59
 */
@Data
public class AuthUserInfo {
    /**
     * 用户编号
     */
    private Long userNo;
    /**
     *用户名
     */
    private String userName;
    /**
     *手机号
     */
    private String phone;
    /**
     *头像
     */
    private String avatar;
    /**
     *昵称
     */
    private String nickName;
}
