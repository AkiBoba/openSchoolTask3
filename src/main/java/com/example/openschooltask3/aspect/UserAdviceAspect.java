package com.example.openschooltask3.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAdviceAspect {

    private static final Logger log = LogManager.getLogger();

    @Before("com.example.openschooltask3.aspect.LoggingAspect.isUserServiceLayer() && com.example.openschooltask3.aspect.LoggingAspect.isSomeMethod()")
    public void loggingBeforeGetMethods(JoinPoint joinPoint) {
        log.info("Поступил запрос в метод {}, сервиса {} c аргументами {}", joinPoint.getSignature().getName(), getSubService(joinPoint.getSignature().getDeclaringTypeName()), joinPoint.getArgs());
    }

    @AfterReturning(value = "com.example.openschooltask3.aspect.LoggingAspect.isUserServiceLayer() && com.example.openschooltask3.aspect.LoggingAspect.isSomeMethod()", returning = "result")
    public void loggingReturningGetMethods(JoinPoint joinPoint, Object result) {
       log.info("Метод {} сервиса {} вернул {}", joinPoint.getSignature().getName(), getSubService(joinPoint.getSignature().getDeclaringTypeName()), result);
    }

    private String getSubService(String service) {
            String[] parts = service.split("\\.");
        return parts[parts.length - 1];
    }

}
