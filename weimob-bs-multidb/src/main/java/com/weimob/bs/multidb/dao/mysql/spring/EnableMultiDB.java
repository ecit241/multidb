package com.weimob.bs.multidb.dao.mysql.spring;

import com.weimob.bs.multidb.dao.mysql.aop.InputCheckAspect;
import com.weimob.bs.multidb.dao.mysql.aop.MultiDataSourceAspect;
import com.weimob.bs.multidb.dao.utils.SpringBeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Adam on 2016/4/26.
 */
@Component
@Configuration
public class EnableMultiDB {
    @Bean
    public MultiDataSourceAspect multiDataSourceAspect() {
        return new MultiDataSourceAspect();
    }

    @Bean
    public InputCheckAspect inputCheckAspect() {
        return new InputCheckAspect();
    }

    @Bean
    public SpringBeanUtils springBeanUtils() {
        return new SpringBeanUtils();
    }
}
