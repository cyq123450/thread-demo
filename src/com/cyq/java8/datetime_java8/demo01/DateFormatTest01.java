package com.cyq.java8.datetime_java8.demo01;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * DateFormat演示
 */
public class DateFormatTest01 {

    /**
     * SimpleDateFormat是线程不安全的，所以以下方式容易产生线程安全问题
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        List<Future<Date>> list = new ArrayList<>();
        
        Callable<Date> task = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
                return sdf.parse("20200114");
            }
        };

        ExecutorService threadPool = newFixedThreadPool(10);
        
        for(int i = 0; i < 10; i++) {
            list.add(threadPool.submit(task));
        }

        for(int j = 0; j < 10; j++) {
            System.out.println(list.get(j).get());
        }

    }

    /**
     * 以下是为了解决上面产生线程安全问题
     *      使用ThreadLocal解决线程安全问题。
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        List<Future<Date>> list = new ArrayList<>();

        Callable<Date> task = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
                return DateFormatLocalThread.convert("20200114");
            }
        };

        ExecutorService threadPool = newFixedThreadPool(10);

        for(int i = 0; i < 10; i++) {
            list.add(threadPool.submit(task));
        }

        for(int j = 0; j < 10; j++) {
            System.out.println(list.get(j).get());
        }
    }

    /**
     * 全新时间格式API，该API不存在线程安全问题。
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Callable<LocalDateTime> callable = () -> LocalDateTime.parse("2021-01-26 13:30:00", dtf);
        ExecutorService pool = newFixedThreadPool(10);

        List<Future> results = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            results.add(pool.submit(callable));
        }


        for(int i = 0; i < 10; i++) {
            System.out.println(results.get(i).get());;
        }

    }
    
}
