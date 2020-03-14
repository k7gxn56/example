package juc;

import java.util.concurrent.ArrayBlockingQueue;

public class VolatileCantStop {

    public static void main(String[] args) throws InterruptedException{

        ArrayBlockingQueue storage = new ArrayBlockingQueue(8);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer,"producer");

        producerThread.start();
        Thread.sleep(500);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了.");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        //producer.canceled = true;
        //发出中断信号
        producerThread.interrupt();
    }
}
