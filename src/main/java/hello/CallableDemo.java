package hello;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableDemo {

    static class MyTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName()+",我进来了...");
            TimeUnit.SECONDS.sleep(3);
            return 2048;
        }
    }


    public static void main(String[] args) throws Exception{
        Long start = System.currentTimeMillis();
        FutureTask<MyTask> future = new FutureTask<>(MyTask::new);
        AtomicInteger atomicInteger = new AtomicInteger(100);
        for (int i = 0;i < 3;i++){
            new Thread(() -> {
                try{
                    System.out.println(Thread.currentThread().getName()+" 开始计算");
                    TimeUnit.SECONDS.sleep(1);
                    atomicInteger.getAndIncrement();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){

        }
        new Thread(future,"XXX").start();

        Integer sum = atomicInteger.get() + future.get().call();

        Long end = System.currentTimeMillis();

        System.out.println("运行结束,结果是:"+sum+",耗时:"+(end - start)+" ms");
    }
}
