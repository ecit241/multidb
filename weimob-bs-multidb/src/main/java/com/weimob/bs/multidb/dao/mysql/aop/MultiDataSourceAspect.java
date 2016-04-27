package com.weimob.bs.multidb.dao.mysql.aop;

import com.weimob.bs.multidb.dao.mysql.datasource.MultiDataSource;
import com.weimob.bs.multidb.dao.mysql.annotation.TargetDataSource;
import com.weimob.bs.multidb.dao.mysql.annotation.UseClassDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Adam on 2016/4/26.
 */
@Component
@Order(1)
@Aspect
public class MultiDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(MultiDataSourceAspect.class);

    @Before("@annotation(ucds)")
    public void doUserClassDataSourceBefore(JoinPoint joinPoint, UseClassDataSource ucds) throws Throwable {
        if (joinPoint.getTarget().getClass().getAnnotation(TargetDataSource.class) != null) {
            MultiDataSource.setDataSourceKey(joinPoint.getTarget().getClass().getAnnotation(TargetDataSource.class).name());
        } else {
            logger.info("没有指定数据库，使用默认数据库------>class:\n" + joinPoint.getTarget().getClass().getName()
                    + "------>method:\n" + joinPoint.getSignature().getName());
        }
    }

    @After("@annotation(ucds)")
    public void doUserClassDataSourceAfter(UseClassDataSource ucds) throws Throwable {
        MultiDataSource.clearDataSourceKey();
    }

    @Before("@annotation(tds)")
    public void doMessageBefore(JoinPoint joinPoint, TargetDataSource tds) throws Throwable {
        if (tds != null && tds.name() != null) {
            MultiDataSource.setDataSourceKey(tds.name());
        } else {
            logger.info("没有指定数据库，使用默认数据库------>class:\n" + joinPoint.getTarget().getClass().getName()
                    + "------>method:\n" + joinPoint.getSignature().getName());
        }
    }

    @After("@annotation(tds)")
    public void doMessageAfter(TargetDataSource tds) throws Throwable {
        MultiDataSource.clearDataSourceKey();
    }
}
