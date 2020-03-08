package hello;

import java.util.concurrent.*;

public class MyThreadDemo {

    public static void main(String[] args){

        System.out.println(Runtime.getRuntime().availableProcessors());
        System.exit(0);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        try{
            for (int i = 1;i <= 12;i++){
                final int tmp = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"正在处理"+tmp+".");
                });
//                try{
//                    TimeUnit.MILLISECONDS.sleep(150);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    public static void simple(){
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        try{
            for (int i = 1;i <= 20;i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"正在处理.");
                });
                try{
                    TimeUnit.MILLISECONDS.sleep(150);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
