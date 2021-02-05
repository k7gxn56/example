package concurrent;

import juc.ThreadStop;
import lombok.SneakyThrows;

/**
 * @author shenxiang
 * @date 2021/2/5 10:27
 * @description
 */
public class MyThread extends Thread {

    private boolean running = true;
    @SneakyThrows
    @Override
    public void run(){
        t5();
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(3000);
        myThread.stopRunning();
        myThread.join();
    }

    private void t5() throws InterruptedException {
        while (running) {
            System.out.println(Thread.currentThread().getName() + " is running ...");
            Thread.sleep(1000);
        }
    }

    private void stopRunning(){
        this.running = false;
    }

    public void t4() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(System.currentTimeMillis()+" -> mythread "+Thread.currentThread().getName()+" : "+i);
            Thread.sleep(300);
        }
    }

    public void t3(){
        int i = 0;
        while (true){
            boolean interrupted = isInterrupted();
            System.out.println("中断标记:"+interrupted);

            ++i;
            if (i > 200){
                final boolean interrupted1 = Thread.interrupted();
                System.out.println("重置中断标记:"+interrupted1);
                final boolean interrupted2 = Thread.interrupted();
                System.out.println("重置中断标记:"+interrupted2);
                final boolean interrupted3 = Thread.interrupted();
                System.out.println("重置中断标记:"+interrupted3);
                break;
            }
        }
    }

    public void t2(){
        while (true){
            final boolean interrupted = isInterrupted();
            System.out.println(System.currentTimeMillis()+" -> "+Thread.currentThread().getName()+" 中断标记:"+interrupted);
        }
    }

    public void t1(){
        for (int i = 0; i < 10; i++) {
            System.out.println(System.currentTimeMillis()+" -> MyThread 线程:"+i);
        }
    }
}
