package juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo04 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException{
        for (int i = 0;i < 1000;i++){
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    ThreadLocal<Long> threadLocal = new ThreadLocal<>();
                    threadLocal.set(System.currentTimeMillis());
                    System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
                }
            });
            try{ TimeUnit.MILLISECONDS.sleep(3); }catch (Exception e){}
        }
        threadPool.shutdown();
    }
}
