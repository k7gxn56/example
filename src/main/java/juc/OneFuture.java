package juc;

import java.util.Random;
import java.util.concurrent.*;

public class OneFuture {

    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask());

        try{
            System.out.println(future.get());
        } catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception{
            Thread.sleep(1000);
            return new Random().nextInt(9999);
        }
    }
}
