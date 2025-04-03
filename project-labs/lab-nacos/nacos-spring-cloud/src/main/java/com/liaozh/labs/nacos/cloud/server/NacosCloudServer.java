package com.liaozh.labs.nacos.cloud.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liaozh.labs.nacos.cloud.config.NacosCloudServerConfig;
import com.liaozh.labs.nacos.cloud.listener.NacosCloudListener;

/**
 * nacos cloud 服务器
 *
 * @author: liaozh
 * @date: 2025/4/3
 */
@Component
public class NacosCloudServer {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** 服务器配置 */
    private NacosCloudServerConfig serverConfig;
    /** 监听器 */
    private NacosCloudListener listener;

    public void startup() {
        LOGGER.info("开始启动服务器...");
        this.listener.initListener();
        // 上次刷帧时间
        long lastUpdateTime = System.currentTimeMillis();
        while (true) {
            long now = System.currentTimeMillis();
            long interval = now - lastUpdateTime;
            if (interval > this.serverConfig.getUpdateTime()) {
                if (this.serverConfig.isDebug()) {
                    LOGGER.debug("刷新服务器...间隔:{}ms", interval);
                }
                lastUpdateTime = now;
            }
        }
    }

    @Autowired
    public void setServerConfig(NacosCloudServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Autowired
    public void setListener(NacosCloudListener listener) {
        this.listener = listener;
    }
}
