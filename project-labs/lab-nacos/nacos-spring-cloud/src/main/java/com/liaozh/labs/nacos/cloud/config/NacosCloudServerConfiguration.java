package com.liaozh.labs.nacos.cloud.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * nacos cloud 配置
 *
 * @author: liaozh
 * @date: 2025/4/3
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(NacosCloudServerConfig.class)
public class NacosCloudServerConfiguration {}
