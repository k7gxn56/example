package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerByBlockingQueueV2 {
    public static void main(String[] args){
        BlockingQueue<Long> queue = new ArrayBlockingQueue<>(10);

        for (int i =0;i < 5;i++){
            Thread p = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(Thread.currentThread().getName()+"正在往队列中放入数据");
                            queue.put(System.currentTimeMillis());
                        }
                    }catch (InterruptedException e){
                        System.out.println(Thread.currentThread().getName()+"被中断");
                        e.printStackTrace();
                    }
                }
            },"p"+i);
            try{
                p.start();
                TimeUnit.SECONDS.sleep(1);
                p.interrupt();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        for (int i = 0;i < 3;i++){
            Thread c = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while (true){
                            System.out.println(Thread.currentThread().getName()+"正在从队列中拿出数据");
                            System.out.println(queue.take());
                            Thread.sleep(500);
                        }
                    }catch (InterruptedException e){
                        System.out.println(Thread.currentThread().getName()+"被中断");
                        e.printStackTrace();
                    }
                }
            },"c"+i);
            try{
                c.start();
                TimeUnit.SECONDS.sleep(1);
                c.interrupt();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
