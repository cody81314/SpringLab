package com.example.demo.aop.aspect;

import com.example.demo.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Order(1)
public class LoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    @Before(value = "com.example.demo.aop.poincut.PointcutDefinition.serviceLayer()")
    public void logBeforeService(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
        String signatureName = joinPoint.getSignature().getName();
        String methodName = String.format("%s：%s ", targetClassName, signatureName);
        String args = Arrays.stream(joinPoint.getArgs())
                .filter(e -> !(e instanceof byte[]))
                .map(JsonUtil::convertObjectToJsonString)
                .collect(Collectors.joining(", "));

        LOGGER.info("{}({}) Start...", methodName, args);
    }

    @After(value = "com.example.demo.aop.poincut.PointcutDefinition.serviceLayer()")
    public void logAfterService(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
        String signatureName = joinPoint.getSignature().getName();
        String methodName = String.format("%s：%s ", targetClassName, signatureName);

        LOGGER.info("{}() End...", methodName);
    }

    @Around(value = "com.example.demo.aop.poincut.PointcutDefinition.serviceLayer()")
    public Object logAroundService(ProceedingJoinPoint jp) throws Throwable {
        String targetClassName = jp.getTarget().getClass().getSimpleName();
        String signatureName = jp.getSignature().getName();
        String methodName = String.format("%s：%s ", targetClassName, signatureName);

        long beginTime = System.currentTimeMillis();
        Object result = jp.proceed();
        long serviceCostTime = System.currentTimeMillis() - beginTime;

        LOGGER.info("{}() cost time: {}", methodName, serviceCostTime);

        return result;
    }
}
