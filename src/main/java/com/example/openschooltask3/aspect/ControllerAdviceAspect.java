package com.example.openschooltask3.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAdviceAspect {

    private static final Logger log = LogManager.getLogger();

    @AfterThrowing(value = "com.example.openschooltask3.aspect.LoggingAspect.isControllerLayer() && com.example.openschooltask3.aspect.LoggingAspect.isSomeMethod()", throwing = "ex")
    public void loggingThrowingGetMethods(JoinPoint joinPoint, Throwable ex) {
        log.error("Метод {} контроллера {} выбросил предупреждение {}", joinPoint.getSignature().getName(), getSubService(joinPoint.getSignature().getDeclaringTypeName()), ex.toString());
    }

    private String getSubService(String service) {
        String[] parts = service.split("\\.");
        return parts[parts.length - 1];
    }
}
