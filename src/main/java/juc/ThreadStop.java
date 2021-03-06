package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 正确终止线程的方法是给目标线程发出中断信号,目标线程接收到中断信号后做相应处理再停止线程,如果无法处理则应该将中断向上层抛出
 */
public class ThreadStop {

    static class Task implements Runnable{
        private Integer num = 0;
        private Integer total = 9000;
        @Override
        public void run(){
            while (!Thread.currentThread().isInterrupted() && num < total){
                num++;
                System.out.println(Thread.currentThread().getName()+"正在执行,num="+num);
            }
            if (Thread.currentThread().isInterrupted()){
                System.out.println("接收到中断信号,赶紧去做收尾工作");
                System.out.println("收尾工作进行中...");
                System.out.println("我是因为接收到中断信号才停止的");
            }else{
                System.out.println("我是程序正常运行完成退出而终止的");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //Thread thread = new Thread(new Task(),"t1");
        MyThreadFactory myThreadFactory = new MyThreadFactory();
        Thread thread = myThreadFactory.newThread(new Task());
        thread.start();
        Thread.sleep(50);
        thread.interrupt();
        Thread thread2 = myThreadFactory.newThread(new Task());
        thread2.start();
        thread2.sleep(50);
        thread2.interrupt();
    }

    private static class MyThreadFactory implements ThreadFactory{
        private AtomicInteger n = new AtomicInteger(0);
        public Thread newThread(Runnable r){
            n.getAndIncrement();
            return new Thread(r,"自定义线程名"+n);
        }
    }
}
