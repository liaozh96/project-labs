package com.liaozh.labs.nacos.cloud.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liaozh.labs.nacos.cloud.listener.NacosCloudConfigListener;

/**
 * nacos cloud 服务器
 *
 * @author: liaozh
 * @date: 2025/4/3
 */
@Component
public class NacosCloudServer {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** 监听器 */
    private NacosCloudConfigListener listener;

    public void startup() {
        LOGGER.info("开始启动服务器...");
        this.listener.initListener();
    }

    @Autowired
    public void setListener(NacosCloudConfigListener listener) {
        this.listener = listener;
    }
}
