package com.weimob.bs.multidb.dao.mysql.aop;

import com.weimob.bs.multidb.dao.mysql.annotation.NeedCheck;
import com.weimob.bs.multidb.dao.mysql.model.CheckException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Adam on 2016/4/27.
 */
@Component
@Order(0)
@Aspect
public class InputCheckAspect {
    private static final Logger logger = LoggerFactory.getLogger(MultiDataSourceAspect.class);

    @Before("@annotation(nc)")
    public void doInputCheckBefore(JoinPoint joinPoint, NeedCheck nc) throws Throwable {
        Object[] args = joinPoint.getArgs();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations;
        String outStr = "";
        for (int i = 0; i < args.length; i++) {
            constraintViolations = validator.validate(args[i]);
            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                    outStr += constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage() + "\n";
                }
            }
        }
        if (outStr != null && !"".equals(outStr)) {
            throw new CheckException("check error:\n" + outStr);
        }
    }
}
