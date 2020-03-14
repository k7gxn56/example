package juc;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage){
        this.storage = storage;
        System.out.println("生产者当前线程状态是:"+Thread.currentThread().getState().name());
    }
    @Override
    public void run(){
        int num = 0;
        try{
            Thread.sleep(100);
            System.out.println("生产者当前线程状态是:"+Thread.currentThread().getState().name());
            //while (num <= 100000 && !canceled){
            while (!Thread.currentThread().isInterrupted() && num <= 99999){
                if (num % 100 == 0){
                    storage.put(num);//如果是因为队列阻塞而卡在这里,改变voliatle状态是无法退出线程执行的
                    System.out.println(num+"是100的正数倍,倍放置到仓库中了");
                }
                num++;
            }
        }catch (InterruptedException e){
            System.out.println("生产者当前线程状态是:"+Thread.currentThread().getState().name());
            Thread.currentThread().interrupt();
            System.out.println("生产者收到了中断信号");
            e.printStackTrace();
        }finally {
            //if (canceled){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("我是被中断而结束的.");
            }
            System.out.println("生产者结束运行");
            System.out.println(Thread.currentThread().isInterrupted());
        }
    }
}
