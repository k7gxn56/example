package juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenxiang
 * @date 2020/6/16 9:59
 * @description
 */
public class VolatileInc implements Runnable{

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileInc v = new VolatileInc();
        for (int i = 0; i < 10; i++) {
            new Thread(v, "t"+i).start();
        }
        while (Thread.activeCount() >= 2){
            // 除main 和 gc线程，还有其他线程在运行
        }
        System.out.println("count = "+count.toString());
    }
}
