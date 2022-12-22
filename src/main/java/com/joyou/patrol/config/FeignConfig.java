package com.joyou.patrol.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liushuai
 * @date: 2021/4/21
 * @description：feign
 */
@Slf4j
@Configuration
@ConditionalOnClass(FeignClient.class)
@EnableFeignClients
public class FeignConfig {
}
