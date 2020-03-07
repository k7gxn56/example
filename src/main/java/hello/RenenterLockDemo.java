package hello;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{

    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run(){
        get();
    }

    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 可重入锁（也叫做递归锁）
 * 同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 * 在同一个线程外层方法获取锁的时候，在进入内层方法会自动获取该锁
 *
 * synchronized是可重入锁
 * t1	 invoked sendSMS()
 * t1	 invoked sendEmail()
 * t2	 invoked sendSMS()
 * t2	 invoked sendEmail()
 * ReentrantLock也是可重入锁
 * t3	 invoked get()
 * t3	 invoked set()
 * t4	 invoked get()
 * t4	 invoked set()
 */
public class RenenterLockDemo {

    public static void main(String[] args){
        Phone phone = new Phone();

        new Thread(() -> {
            try{
                phone.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            try{
                phone.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("==========================");

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();
    }
}
