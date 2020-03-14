package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static void main(String[] args){
        CallableTask callableTask = new CallableTask();
        RunnableTask runnableTask = new RunnableTask();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(callableTask);
        new Thread(runnableTask,"RunnableTask").start();
        new Thread(integerFutureTask,"CallableTask").start();
        try{
            System.out.println("task运行结果:"+integerFutureTask.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}

class RunnableTask implements Runnable{
    @Override
    public void run(){
        System.out.println("线程"+Thread.currentThread().getName()+"正在工作");
        int sum = 0;
        for (int i = 1;i <= 100;i++){
            sum += i;
        }
        System.out.println(sum);
    }
}

class CallableTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception{
        System.out.println("线程"+Thread.currentThread().getName()+"正在工作");
        int sum = 0;
        for (int i = 1;i <= 100;i++){
            sum += i;
        }
        return sum;
    }
}