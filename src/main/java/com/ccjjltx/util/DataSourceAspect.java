package com.ccjjltx.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 切片注入
 */
public class DataSourceAspect {

    private final static Logger logger = Logger.getLogger(DataSourceAspect.class);

    /**
     * 拦截目标方法，由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     *
     * @param point JoinPoint
     */
    public void intercept(JoinPoint point) {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     *
     * @param clazz  Class
     * @param method Method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
        } catch (Exception e) {
            //打印错误日志
            logger.error(clazz + ":" + e.getMessage());
        }
    }
}
