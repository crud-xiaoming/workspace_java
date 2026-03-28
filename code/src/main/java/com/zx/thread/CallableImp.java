package com.zx.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableImp implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "任务已完成";
    }

    public static void main(String[] args) {
        try {
            // 创建线程池
            ExecutorService executor = Executors.newSingleThreadExecutor();
            
            // 创建 Callable 任务
            CallableImp callable = new CallableImp();
            
            // 提交任务并获取 Future
            Future<String> future = executor.submit(callable);
            
            // 获取任务执行结果（会阻塞直到任务完成）
            String result = future.get();
            System.out.println(result);
            
            // 关闭线程池
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
