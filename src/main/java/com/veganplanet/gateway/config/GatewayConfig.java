package com.veganplanet.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
* <p>读取网关配置文件</p>
* @date 2024/1/15 15:29
* @param
* @return
*/
@Configuration
@ConfigurationProperties(prefix = "cn.veganplanet.gateway")
@Data
public class GatewayConfig {

    private Map<String, List<String>> noLoginUrls;

    /**
    * <p>判断是否需要网关验证接口判断</p>
    * @date 2024/1/15 15:30
    * @param
    * @return
    */
    public boolean containsNoLogin(Route route, String path){
        String serviceName=route.getUri().getHost();
        path=path.replace("/"+serviceName,"");
        if(getNoLoginUrls().containsKey(serviceName)){
            return  getNoLoginUrls().get(serviceName).contains(path);
        }
        return  false;
    }
}
