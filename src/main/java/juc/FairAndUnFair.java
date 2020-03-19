package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FairAndUnFair {

    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock(false);
        Thread[] threads = new Thread[10];
        for (int i =0;i <10;i++){
            final int n = i;
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " -> " + n);
                }finally {
                    lock.unlock();
                }
            },"t"+i).start();
        }
    }

}