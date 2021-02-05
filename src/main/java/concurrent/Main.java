package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenxiang
 * @date 2021/2/5 10:28
 * @description
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        t5();
    }

    private static void t5() throws InterruptedException {
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        myDaemonThread.setDaemon(true);
        myDaemonThread.start();

        final MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();

        System.out.println(Thread.currentThread().getName()+" exec done.");
    }

    private static void t4() throws InterruptedException {
        final MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(3);
        myThread.interrupt();
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+"中断检查1:"+myThread.isInterrupted());
        System.out.println(Thread.currentThread().getName()+"中断检查2:"+myThread.isInterrupted());
    }

    private static void t3() throws InterruptedException {
        final MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(10);
        myThread.interrupt();
        Thread.sleep(10);
        System.exit(0);
    }

    private static void t2(){
        MyQueue2 myQueue = new MyQueue2();
        AtomicInteger index = new AtomicInteger(0);
        for (int i = 0; i < 5; i++) {
            new ConsumerThread(myQueue).start();
        }

        for (int i = 0; i < 10; i++) {
            new ProducerThread(myQueue,index).start();
        }
    }

    private static void t1() throws InterruptedException, ExecutionException {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();

        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
        final String result = futureTask.get();
        System.out.println("future exec result:"+result);
        System.out.println("main 线程 - 执行完成");
    }
}
