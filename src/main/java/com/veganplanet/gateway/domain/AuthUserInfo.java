package com.veganplanet.gateway.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer userNo;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
}
