package juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadLockJumpQueue {

    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static void read(){
        readWriteLock.readLock().lock();
        String threadName = Thread.currentThread().getName();
        try{
            System.out.println(threadName+"获取到了读锁");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
            System.out.println(threadName+"释放了读锁");
        }
    }

    private static void write(){
        readWriteLock.writeLock().lock();
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(threadName+"获取到了写锁");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
            System.out.println(threadName+"释放了写锁");
        }
    }

    private static void upgrade(){
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "获取到了读锁");
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "获取到了写锁,升级成功");
        }finally {
            readWriteLock.readLock().unlock();
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args){
        new Thread(() -> upgrade(),"t1").start();
//        new Thread(() -> read(),"t2").start();
//        new Thread(() -> read(),"t3").start();
//        new Thread(() -> read(),"t4").start();
//        new Thread(() -> write(),"t5").start();
//        new Thread(() -> read(),"t6").start();
//        new Thread(() -> write(),"t7").start();
//        new Thread(() -> read(),"t8").start();
    }
}
