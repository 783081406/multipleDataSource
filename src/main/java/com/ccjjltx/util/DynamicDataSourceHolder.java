package com.ccjjltx.util;

/**
 * @author ccj Date:2018/05/09 21:47
 * @version 1.0
 * @since 1.8
 */
public class DynamicDataSourceHolder {

    /**
     * 数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
     */
    private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<>();

    static String getDataSource() {
        return THREAD_DATA_SOURCE.get();
    }

    static void setDataSource(String dataSource) {
        THREAD_DATA_SOURCE.set(dataSource);
    }

    public static void clearDataSource() {
        THREAD_DATA_SOURCE.remove();
    }
}
