package com.zx.thread;

public class RunableImpl implements Runnable{

    public void run() {
        System.out.println("RunableImpl任务");
    }

    public static void main(String[] args) {
        Runnable runnable = new RunableImpl();
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
