package com.liaozh.labs.nacos.cloud.listener;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

/**
 * nacos cloud 监听器
 *
 * @author: liaozh
 * @date: 2025/4/3
 */
@Service
public class NacosCloudListener {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private NacosConfigManager nacosConfigManager;

    @Value("${server.config.listen-id}")
    private String listenDataId;
    @Value("${spring.cloud.nacos.config.group}")
    private String group;

    public void initListener() {
        LOGGER.info("开始初始化监听器");
        addListener();
    }

    public void addListener() {
        try {
            this.nacosConfigManager.getConfigService().addListener(this.listenDataId, this.group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    LOGGER.info("收到nacos更新配置:{}", configInfo);
                }

                @Override
                public Executor getExecutor() {
                    LOGGER.info("getExecutor");
                    return null;
                }

            });
        } catch (NacosException e) {
            LOGGER.error("从nacos接收动态路由配置出错", e);
        }
    }

    @Autowired
    public void setNacosConfigManager(NacosConfigManager nacosConfigManager) {
        this.nacosConfigManager = nacosConfigManager;
    }
}
