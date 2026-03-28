package com.zx.thread;

public class ThreadImpl extends Thread{

    @Override
    public void run() {
        System.out.println("任务完成");
    }

    public static void main(String[] args) {
        ThreadImpl thread = new ThreadImpl();
        thread.start();
    }
}
