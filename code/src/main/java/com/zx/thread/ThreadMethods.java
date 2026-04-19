package com.zx.thread;

public class ThreadMethods {
    public static void main(String[] args) {
        ThreadImpl  impl = new ThreadImpl();
        impl.start();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        impl.interrupt();
        System.out.println("=====进程停止=======");
    }
}
