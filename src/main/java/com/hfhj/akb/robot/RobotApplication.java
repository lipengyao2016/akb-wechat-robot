package com.hfhj.akb.robot;

import com.hfhj.akb.starter.dao.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author lin.qing
 * @date 2020-09-22 10:47
 */
@EnableOpenApi
@EnableScheduling
@SpringBootApplication
@EnableFeignClients
@ComponentScan(value = {"com.hfhj.*"})
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class RobotApplication {
    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class);
    }

}
