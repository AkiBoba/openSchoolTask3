package com.example.openschooltask3.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void isControllerLayer() {

    }

    @Pointcut("within(com.example.openschooltask3.service.UserServiceImpl)")
    public void isUserServiceLayer() {

    }

    @Pointcut("within(com.example.openschooltask3.service.OrderServiceImpl)")
    public void isOrderServiceLayer() {

    }

    @Pointcut("execution(public * *(..))")
    public void isSomeMethod() {

    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasMapping() {

    }

    @Pointcut("execution(public * get*(..))")
    public void isGetMethod() {

    }

    @Pointcut("execution(public * create(*))")
    public void isCreateMethod() {

    }

    @Pointcut("execution(public * update(*))")
    public void isUpdateMethod() {

    }

    @Pointcut("execution(public * delete(*))")
    public void isDeleteMethod() {

    }

}
