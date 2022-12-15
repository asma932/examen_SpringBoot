package com.example.exmasma.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;


@Component
@org.aspectj.lang.annotation.Aspect
@Slf4j
public class Aspect {

    @After("execution(* com.example.exmasma.service.IService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Type type = joinPoint.getSignature().getDeclaringType();

        log.info("Success method " + name + " : ");


    }
}