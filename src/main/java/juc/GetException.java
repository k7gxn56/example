package juc;

import java.util.concurrent.*;

public class GetException {


    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(20);

        try{
            for (int i = 0;i < 5;i++){
                System.out.println(i);
                Thread.sleep(200);
            }
            Future<Integer> future = service.submit(new CallableTask());
            System.out.println("future.isDone="+future.isDone());
            System.out.println("future.cancel="+future.cancel(true));
            System.out.println("future.isCancelled="+future.isCancelled());
            System.out.println(future.get());
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            System.out.println("kkkkkkkllllllll");
            e.printStackTrace();
        }finally {
            service.shutdownNow();
        }
    }

    static class CallableTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception{
            throw new IllegalArgumentException("参数不正确");
        }
    }
}
