package juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareNum{

    private Integer num = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try{
            while (num != 0){
                condition.await();
            }
            num++;
            condition.signal();
            System.out.println(Thread.currentThread().getName()+",+1,当前值:"+num);
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try{
            while (num == 0){
                condition.await();
            }
            num--;
            condition.signal();
            System.out.println(Thread.currentThread().getName()+",-1,当前值:"+num);
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 初始值为0 两个用户，一个把他加1  一个把他减1   这个过程个重复5次
 *
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) throws InterruptedException{

        ShareNum n = new ShareNum();
        new Thread(() -> {
            for (int i = 0;i < 5;i++){
                n.increment();
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0;i < 5;i++){
                n.decrement();
            }
        },"t2").start();
    }
}
