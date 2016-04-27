package com.weimob.bs.multidb.dao.utils;

import com.weimob.bs.multidb.dao.mysql.mapper.BaseMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;


/**
 * 泛型限定式依赖注入工具类
 *
 * @author Donney
 * @version 1.0
 * @date 2016/4/12
 **/
@Component
public class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 泛型限定式依赖注入方法
     *
     * @param genericBeanClass 需要依赖注入到当前对象的对象的抽象类的Class文件对象
     * @param clazz 当前对象的Class文件对象
     * @return 需要依赖注入到当前对象的对象
     */
    public static <T> T getGenericImpl(Class<T> genericBeanClass, Class clazz) {
        Class poClass = (Class) ((ParameterizedType) (clazz.getName().contains("CGLIB$$") ? clazz.getSuperclass().getGenericSuperclass() : clazz.getGenericSuperclass())).getActualTypeArguments()[0];
        Iterator<T> genericBeanIterator = applicationContext.getBeansOfType(genericBeanClass).values().iterator();
        if(genericBeanClass.isAssignableFrom(BaseMapper.class)){
            while (genericBeanIterator.hasNext()) {
                T genericBean = genericBeanIterator.next();
                if (poClass == ((ParameterizedType)genericBean.getClass().getInterfaces()[0].getGenericInterfaces()[0]).getActualTypeArguments()[0]) {
                    return genericBean;
                }
            }
            return null;
        }
        while (genericBeanIterator.hasNext()) {
            T genericBean = genericBeanIterator.next();
            if (poClass == ((ParameterizedType) (genericBean.getClass().getName().contains("CGLIB$$") ? genericBean.getClass().getSuperclass().getGenericSuperclass() : genericBean.getClass().getGenericSuperclass())).getActualTypeArguments()[0]) {
                return genericBean;
            }
        }
        return null;
    }
}
