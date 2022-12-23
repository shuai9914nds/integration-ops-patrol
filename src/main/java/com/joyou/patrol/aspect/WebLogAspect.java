package com.joyou.patrol.aspect;


import com.joyou.patrol.config.TokenUtils;
import com.joyou.patrol.entity.BisUser;
import com.joyou.patrol.entity.ImmpSystemLog;
import com.joyou.patrol.mapper.ImmpSystemLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Candy
 * @create 2020-10-30 10:21
 * 日志获取AOP
 */
@Aspect
@Component
public class WebLogAspect {

    @Autowired
    private ImmpSystemLogMapper immpSystemLogMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Pointcut("within(com.joyou.patrol.controller..*)))")//切入点描述，这个是uiController包的切入点
    public void controllerLog() {
    }

    @Bean
    public TransactionInterceptor txAdvice() {

        //只读方法不加事务
        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();


        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("select*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);


        return new TransactionInterceptor(transactionManager, source);
    }

    @AfterReturning(returning = "returnOb", pointcut = "controllerLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnOb) {
        /**
         * 这个RequestContextHolder是Springmvc提供来获得请求的东西
         */
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        /**
         * 记录下请求内容
         */
        String ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())) {
            ip = "127.0.0.1";
        }
        String methodName = joinPoint.getSignature().getName();//方法名
        List<Object> args = Arrays.asList(joinPoint.getArgs());//参数
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();//类名
        BisUser loginer = TokenUtils.getLoginer();
        String token = TokenUtils.getToken();
        ImmpSystemLog immpSystemLog = new ImmpSystemLog();
        immpSystemLog.setLogStatic("成功");
        immpSystemLog.setLogName(loginer.getAccountName());
        immpSystemLog.setLogService(declaringTypeName);
        immpSystemLog.setLogBehavior(methodName);
        immpSystemLog.setLogIp(ip);
        immpSystemLog.setLogParameter(args.toString());
        immpSystemLog.setUserToken(token);
        immpSystemLogMapper.insert(immpSystemLog);
    }

    @AfterThrowing(pointcut = "controllerLog()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //状态：失败
        //姓名：当前登录者name
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
//        System.out.println("连接点方法为：" + methodName + ",参数为：" + args + ",异常为：" + ex);
        String ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())) {
            ip = "127.0.0.1";
        }
        BisUser loginer = TokenUtils.getLoginer();
        String token = TokenUtils.getToken();
        ImmpSystemLog immpSystemLog = new ImmpSystemLog();
        immpSystemLog.setLogStatic("失败");
        immpSystemLog.setLogName(loginer.getAccountName());
        immpSystemLog.setLogService(declaringTypeName);
        immpSystemLog.setLogBehavior(methodName);
        immpSystemLog.setLogIp(ip);
        immpSystemLog.setLogParameter(args.toString());
        immpSystemLog.setUserToken(token);
        immpSystemLogMapper.insert(immpSystemLog);
    }


}
