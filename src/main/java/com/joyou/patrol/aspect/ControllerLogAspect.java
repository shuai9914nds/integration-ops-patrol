package com.joyou.patrol.aspect;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joyou.patrol.common.ServerResponse;
import com.joyou.patrol.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author little
 * description：controller层日志拦截组件
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    /**
     * 切入点
     */
    @Pointcut("within(com.joyou.patrol.controller..*))")
    public void pointcut() {
    }

    /**
     * 环绕通知，在方法执行前后
     *
     * @param point 切入点
     * @return 结果
     * @throws BusinessRuntimeException
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws BusinessRuntimeException {
        // 签名信息
        Signature signature = point.getSignature();
        // 强转为方法信息
        MethodSignature methodSignature = (MethodSignature) signature;
        // 参数名称
        String[] parameterNames = methodSignature.getParameterNames();
        //执行的对象
        Object target = point.getTarget();

        log.info("请求处理方法:{}", target.getClass().getName() + "." + methodSignature.getMethod().getName());
        Object[] parameterValues = point.getArgs();
        //查看入参
        boolean flag = true;
        for (Object parameterValue : parameterValues) {
            if ((parameterValue instanceof MultipartFile) || (parameterValue instanceof HttpServletResponse) || (parameterValue instanceof HttpServletRequest) || (parameterValue instanceof MultipartFile[])) {
                flag = false;
            }
        }
        if (flag) {
            log.info("请求参数名:{}，请求参数值:{}", JSONObject.toJSONString(parameterNames), JSONObject.toJSONString(parameterValues));
        }
        try {
            // 开始时间
            long startTime = System.currentTimeMillis();

            // 执行方法
            Object response = point.proceed();
            // 结束时间
            long endTime = System.currentTimeMillis();
            log.info("请求处理时间差:{}ms, 响应结果:{}", endTime - startTime, JSON.toJSONString(response));
            return response;
        } catch (Throwable throwable) {
            log.error("执行方法:{}失败，异常信息:{}", methodSignature.getMethod().getName(), throwable);
            if (throwable instanceof BusinessRuntimeException) {
                // 业务异常
                return ServerResponse.createByErrorCodeMessage(1001,"参数异常");
            }
            // 非业务异常，封装一层异常
            throw new BusinessRuntimeException(1001,"方法 " + methodSignature.getMethod().getName() + " 执行失败");
        }
    }
}



