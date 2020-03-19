package juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CachedThreadPoolDemo {

    private static AtomicInteger ai = new AtomicInteger(0);

    static class RunnableTask implements Runnable{

        @Override
        public void run(){
            System.out.println("当前时间戳(s)->"+(System.currentTimeMillis() / 1000));
            System.out.println("RunnableTask线程"+Thread.currentThread().getName()+"被执行了,ai="+ai.getAndIncrement());
            try{
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class CallableTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception{
            Integer tmp = ai.getAndIncrement();
            System.out.println("CallableTask线程"+Thread.currentThread().getName()+"被执行了,ai="+tmp);
            TimeUnit.MILLISECONDS.sleep(1);
            return tmp;
        }
    }

    static class Fibonacci extends RecursiveTask<Integer>{
        int n;
        public Fibonacci(int n){
            this.n = n;
        }
        @Override
        public Integer compute(){
            System.out.println("线程"+Thread.currentThread().getName()+"正在执行任务");
            if (n <= 1){
                return n;
            }
            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n -2);
            f2.fork();
            return f1.join() + f2.join();
        }
    }

    public static void main(String[] args) throws Exception{
        //fixedThreadPool();
        //cachedThreadPool();
        //scheduledThreadPool();
        //singleThreadExecutor();
        //singleThreadScheduledExecutor();
        forkJoinPool();
    }

    private static void forkJoinPool() throws Exception{
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i= 0;i < 20;i++){
            ForkJoinTask task = forkJoinPool.submit(new Fibonacci(i));
            System.out.println(task.get());
        }
    }

    private static void singleThreadScheduledExecutor(){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0;i < 100;i++) {
            service.scheduleWithFixedDelay(new RunnableTask(), 1, 2, TimeUnit.SECONDS);
        }
    }

    private static void singleThreadExecutor() throws Exception{
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0;i < 1000;i++){
            service.submit(new RunnableTask());
        }
    }

    private static void scheduledThreadPool() throws Exception{
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        for (int i = 0;i < 100;i++) {
            //service.schedule(new RunnableTask(),3,TimeUnit.SECONDS); 延时3秒后执行
            //service.scheduleAtFixedRate(new RunnableTask(),1,2,TimeUnit.SECONDS);延时1秒后周期性执行2秒,不算任务自身执行时间
            service.scheduleWithFixedDelay(new RunnableTask(), 1, 2, TimeUnit.SECONDS);//延时1秒后周期执行2秒,周期性2秒需在任务自身执行完后开始计算}
            //service.shutdown();
        }
    }

    private static void fixedThreadPool() throws Exception{
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 1000;i++){
            service.submit(new CallableTask());
        }
        service.shutdown();
    }

    private static void cachedThreadPool() throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        Integer rs = 0;
        for (int i = 0;i < 9999;i++){
            //service.submit(new RunnableTask());
            //service.execute(new RunnableTask());
            System.out.println(service.submit(new CallableTask()).get());
        }

        service.shutdown();
    }
}
