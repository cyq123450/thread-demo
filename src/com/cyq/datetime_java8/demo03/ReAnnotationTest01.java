package com.cyq.datetime_java8.demo03;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解：java8新添了重复注解
 */
public class ReAnnotationTest01 {

    /**
     * @NotNull 是非空检查。
     *      checker framework 框架提供了编译的检查，后期可以关注。
     */
    private @NotNull String info;

    // 利用反射获取重复注解值
    @Test
    public void test1() throws Exception {
        Class<ReAnnotationTest01> clazz = ReAnnotationTest01.class;
        Method method = clazz.getMethod("reAnnotation", String.class);
        MyAnnotation[] annotations = method.getAnnotationsByType(MyAnnotation.class);
        for(MyAnnotation annotation : annotations) {
            System.out.println(annotation.value());
        }
    }

    @MyAnnotation("info1")
    @MyAnnotation("info2")
    public void reAnnotation(@MyAnnotation("msg") String msg) {

    }

}
