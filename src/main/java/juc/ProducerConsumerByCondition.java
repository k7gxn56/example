package juc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerByCondition {

    private Queue queue;

    private int max = 16;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    public ProducerConsumerByCondition(int size){
        this.max = size;
        queue = new LinkedList();
    }

    public void put(Object o) throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == max){
                notFull.await();
            }
            System.out.println(o+"被添加到了队列");
            queue.add(o);
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException{
        lock.lock();
        Object item = new Object();
        try{
            while (queue.size() == 0){
                notEmpty.await();
            }
            item = queue.remove();
            System.out.println(item+"被取出了队列");
            notFull.signalAll();
        }catch (InterruptedException e){
            lock.unlock();
        }
        return item;
    }

    public static void main(String[] args){
        ProducerConsumerByCondition producerConsumerByCondition = new ProducerConsumerByCondition(20);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 5;i++){
            Thread p = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Long currentTime = System.currentTimeMillis();
                            System.out.println("线程" + Thread.currentThread().getName() + "添加" + currentTime + "到队列中了");
                            producerConsumerByCondition.put(currentTime);
                        }
                    }catch (InterruptedException e){
                        System.out.println("线程"+Thread.currentThread().getName()+"被中断");
                        e.printStackTrace();
                    }
                }
            },"p"+i);
            service.submit(p);
        }

        for (int i = 0;i < 30;i++){
            Thread p = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(producerConsumerByCondition.take());
                            Thread.sleep(500);
                        }
                    }catch (InterruptedException e){
                        System.out.println("线程"+Thread.currentThread().getName()+"被中断");
                        e.printStackTrace();
                    }
                }
            },"c"+i);
            service.submit(p);
        }
    }
}
