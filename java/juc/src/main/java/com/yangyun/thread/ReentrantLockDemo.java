package com.yangyun.thread;

/**
 * @description 可重入锁: 公平锁, 非公平锁
 * 可重入锁(递归锁): 同一线程外层函数获得锁之后, 内层递归函数仍然能获得该锁的代码,
 * 在同一线程在外层方法获得锁的时候, 再进入内层方法会自动获得锁;
 * 也就是说线程可以进入任何一个它已经获得锁所同步的代码块
 * synchronized 是可重入锁
 * t1	 invoked sendSMS 同一线程外层函数获得锁之后
 * t1	 #######invoked sendEmail  内层递归函数仍然能获得该锁的代码 线程可以进入任何一个它已经获得锁所同步的代码块
 * t22	 invoked sendSMS
 * t22	 #######invoked sendEmail
 * @author yangyun
 * @date 2019/6/19
 * @return
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread (() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread (() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t22").start();
    }
}

class Phone {

    public synchronized void sendSMS () throws Exception{

        String name = Thread.currentThread().getName();
        System.out.println(name + "\t invoked sendSMS");

        sendEmail();
    }

    public synchronized void sendEmail () throws Exception{
        String name = Thread.currentThread().getName();
        System.out.println(name + "\t #######invoked sendEmail");
    }
}