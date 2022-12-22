package com.joyou.patrol.annotation;


import com.joyou.patrol.config.FeignConfig;
import com.joyou.patrol.config.MapperConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liushuai
 * @date: 2021/4/21
 * @description：启动整合注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MapperConfig.class, FeignConfig.class})
public @interface PatrolApplication {
}
