package com.liaozh.labs.nacos.cloud.contorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.liaozh.labs.nacos.cloud.config.NacosCloudServerConfig;

import reactor.core.publisher.Mono;

/**
 * nacos cloud 测试接口控制器
 *
 * @author: liaozh
 * @date: 2025/4/7
 */
@RestController
@RequestMapping("/test")
public class NacosCloudTestRestController {

    private NacosCloudServerConfig config;

    private NacosConfigManager nacosConfigManager;

    @GetMapping("ping")
    public Mono<String> ping() {
        return Mono.just(Boolean.toString(this.config.isDebug()));
    }

    @GetMapping("priority")
    public Mono<String> priority() throws NacosException {
        doPrintPriority();
        return Mono.just("priority");
    }

    /**
     * 打印配置优先级
     *
     * @see com.alibaba.cloud.nacos.client.NacosPropertySourceLocator#locate(Environment env)
     * @throws NacosException nacos异常
     */
    private void doPrintPriority() throws NacosException {

        NacosConfigProperties nacosConfigProperties = this.nacosConfigManager.getNacosConfigProperties();
        // shared
        List<NacosConfigProperties.Config> configs = nacosConfigProperties.getSharedConfigs();;
        if (null != configs && !configs.isEmpty()) {
            for (NacosConfigProperties.Config config : configs) {
                doPrint(config.getDataId(), config.getGroup());
            }
        }

        // extension
        configs = nacosConfigProperties.getExtensionConfigs();;
        if (null != configs && !configs.isEmpty()) {
            for (NacosConfigProperties.Config config : configs) {
                doPrint(config.getDataId(), config.getGroup());
            }
        }

        // main
        doPrint(nacosConfigProperties.getName(), nacosConfigProperties.getGroup());
        System.out.println(".......................................................");
        // 实际生效
        System.out.println("valid:" + this.config.getName());
    }

    @SuppressWarnings("unchecked")
    private void doPrint(String dataId, String group) throws NacosException {
        Map<String, Object> tempMap =
            new Yaml().load(this.nacosConfigManager.getConfigService().getConfig(dataId, group, 3000));
        Map<String, Object> server = (Map<String, Object>)tempMap.get("server");
        Map<String, Object> config = (Map<String, Object>)server.get("config");
        System.out.println(dataId + ":" + config.get("name"));
    }

    @Autowired
    public void setConfig(NacosCloudServerConfig config) {
        this.config = config;
    }

    @Autowired
    public void setNacosConfigManager(NacosConfigManager nacosConfigManager) {
        this.nacosConfigManager = nacosConfigManager;
    }
}
