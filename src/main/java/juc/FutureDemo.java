package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args){
        Long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<String>> allFutures = new ArrayList<>();
        for (int i = 0;i < 4;i++){
            Future<String> future;
            if (i < 2){
                future = service.submit(new SlowTask());
            }else{
                future = service.submit(new FaskTask());
            }
            allFutures.add(future);
        }

        for (int i = 0;i < 4; i++){
            Future<String> future = allFutures.get(i);
            try{
                System.out.println(future.get(100,TimeUnit.MILLISECONDS));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        service.shutdown();
        Long end = System.currentTimeMillis();
        System.out.println("cost time:"+(end - start));
    }

    static class SlowTask implements Callable<String>{

        @Override
        public String call() throws Exception{
            TimeUnit.SECONDS.sleep(3);
            return "速度慢的任务:"+Thread.currentThread().getName();
        }
    }

    static class FaskTask implements Callable<String>{

        @Override
        public String call() throws Exception{
            return "速度快的任务:"+Thread.currentThread().getName();
        }
    }
}
