package com.liaozh.labs.nacos.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * nacos cloud 服务器配置
 *
 * @author: liaozh
 * @date: 2025/4/3
 */
@RefreshScope
@ConfigurationProperties(prefix = "server.config")
public class NacosCloudServerConfig {

    private boolean debug;

    private boolean block;

    private int updateTime;

    private String listenId;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isBlock() {
        return block;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public String getListenId() {
        return listenId;
    }

    public void setListenId(String listenId) {
        this.listenId = listenId;
    }
}
