package com.liaozh.labs.nacos.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.liaozh.labs.nacos.cloud.server.NacosCloudServer;

/**
 * lab nacos cloud 启动器
 *
 * @author: liaozh
 * @date: 2025/4/3
 */
@SpringBootApplication
public class NacosCloudApplication implements CommandLineRunner {

    private NacosCloudServer nacosCloudServer;

    @Autowired
    public void setNacosCloudServer(NacosCloudServer nacosCloudServer) {
        this.nacosCloudServer = nacosCloudServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosCloudApplication.class, args);
    }

    @Override
    public void run(String... args) {
        this.nacosCloudServer.startup();
    }

}
