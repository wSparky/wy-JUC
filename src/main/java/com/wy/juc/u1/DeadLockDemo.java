package com.wy.juc.u1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangye
 * @Email wangye.wy@outlook.com
 * @CreateTime: 2022-10-21  18:40
 * @Version: 1.0.0
 * @Description: TODO
 */
@Slf4j
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadlock();
    }

    private  void deadlock(){
        Thread t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try{
                        log.info("sleeping");
                        Thread.currentThread().sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        log.info("wait");
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
