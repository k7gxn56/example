package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁Demo
 */
public class SpinLockDemo {

    volatile AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println("current Thread is :"+(null == atomicReference.get() ? "Null" : atomicReference.get().getName()));
        boolean flag = atomicReference.compareAndSet(null,thread);
        while (!flag){
            flag = atomicReference.compareAndSet(null,thread);
            System.out.println(thread.getName()+" get Lock : fail.");
        }
        System.out.println(thread.getName()+" get Lock : success.");
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+" invoked myUnLock()");
    }

    public static void main(String[] args){
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            //获取锁3秒钟
            try{ TimeUnit.MILLISECONDS.sleep(500);}catch (InterruptedException e){}
            spinLockDemo.myUnLock();
        },"AAAAA").start();

        //休眠1秒钟,让AAAAA线程先运行一会儿
        try{TimeUnit.MILLISECONDS.sleep(100);}catch (InterruptedException e){}

        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"BBBBB").start();
    }
}
