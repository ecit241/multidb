package com.weimob.bs.multidb.dao.mysql.annotation;

import java.lang.annotation.*;

/**
 * Created by Adam on 2016/4/27.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedCheck {
}
