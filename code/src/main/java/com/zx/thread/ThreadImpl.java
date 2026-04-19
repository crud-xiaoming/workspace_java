package com.zx.thread;

public class ThreadImpl extends Thread{

    @Override
    public void run() {
        int num = 0;
        while(num < 2000){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("[检测到中断标识][任务中断]");
                break;
            }
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "[正在执行][任务号："+num+"]");
            num++;
        }
    }

    public static void main(String[] args) {
        ThreadImpl thread = new ThreadImpl();
        thread.start();
    }
}
