package com.joyou.patrol.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liushuai
 * @date: 2021/4/21
 * @description：mapper依赖
 */
@Slf4j
@Configuration
@EnableFeignClients(basePackages = {"com.joyou.patrol.mapper"})
public class MapperConfig {
}
