package hello;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException{
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

            new Thread(() -> {
                try{
                    System.out.println("线程"+Thread.currentThread().getName()+"添加a");
                    synchronousQueue.put("a");
                    System.out.println("线程"+Thread.currentThread().getName()+"添加b");
                    synchronousQueue.put("b");
                    System.out.println("线程"+Thread.currentThread().getName()+"添加c");
                    synchronousQueue.put("c");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            },"t1").start();

        for (int i = 1;i < 4;i++){
            final int temp = i;
            new Thread(() -> {
                try{
                    System.out.println("线程"+Thread.currentThread().getName()+"删除"+synchronousQueue.take());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
