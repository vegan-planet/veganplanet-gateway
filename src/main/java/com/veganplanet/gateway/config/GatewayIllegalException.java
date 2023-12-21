package com.veganplanet.gateway.config;

import com.alibaba.nacos.shaded.javax.annotation.Nonnull;
import com.veganplanet.common.core.constant.enums.ExceptionStatusEnum;
import com.veganplanet.common.core.exception.AbstractCustomizedRunTimeException;
import com.veganplanet.common.core.response.ServiceStatus;


/**
 * <p>系统异常信息</p>
 *
 * @author wxh_work@163.com
 * @date 2023/2/2 18:13
 */
public final class GatewayIllegalException extends AbstractCustomizedRunTimeException {
    private static final long serialVersionUID = 1L;

    public GatewayIllegalException() {
        super(ExceptionStatusEnum.SYSTEM_SERVICE_ERROR);
    }

    public GatewayIllegalException(@Nonnull ServiceStatus serviceStatus) {
        super(serviceStatus);
    }

    public GatewayIllegalException(@Nonnull ServiceStatus serviceStatus, @Nonnull final String message) {
        super(serviceStatus, message);
    }
}
