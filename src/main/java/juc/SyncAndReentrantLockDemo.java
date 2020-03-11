package juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private Integer n = 1;//1代表A 2代表B 3代表C
    private Lock lock = new ReentrantLock();
    private Condition a = lock.newCondition();
    private Condition b = lock.newCondition();
    private Condition c = lock.newCondition();

    public void print2(){
        lock.lock();
        try{
            while (n != 1){
                a.await();
            }
            System.out.println(Thread.currentThread().getName()+"打印1次");
            System.out.println(Thread.currentThread().getName()+"打印2次");
            n = 2;
            b.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print3(){
        lock.lock();
        try{
            while (n != 2){
                b.await();
            }
            System.out.println(Thread.currentThread().getName()+"打印1次");
            System.out.println(Thread.currentThread().getName()+"打印2次");
            System.out.println(Thread.currentThread().getName()+"打印3次");
            n = 3;
            c.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print4(){
        lock.lock();
        try{
            while (n != 3){
                c.await();
            }
            System.out.println(Thread.currentThread().getName()+"打印1次");
            System.out.println(Thread.currentThread().getName()+"打印2次");
            System.out.println(Thread.currentThread().getName()+"打印3次");
            System.out.println(Thread.currentThread().getName()+"打印4次");
            n = 1;
            a.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 3个线程A B C,交替执行A 打印2次,B打印3次,C打印4次 ,重复3遍
 * 检查  执行 通知
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args){
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0;i < 3;i++) {
                shareResource.print2();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0;i < 3;i++) {
                shareResource.print3();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0;i < 3;i++) {
                shareResource.print4();
            }
        },"C").start();
    }
}
