package com.hfhj.akb.robot.config;

import cn.hutool.core.thread.GlobalThreadPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;

/**
 * @author tanggen
 * @date 2020/11/11 16:01
 */
@Configuration
public class ExecutorServiceConfig {

    @Bean
    public ExecutorService executorService() {
        GlobalThreadPool.init();
        return GlobalThreadPool.getExecutor();
    }

}
