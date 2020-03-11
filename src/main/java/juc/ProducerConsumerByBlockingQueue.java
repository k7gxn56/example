package juc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ProducerConsumerByBlockingQueue {

    static class MyTask{

        private volatile Boolean flag = true;

        private BlockingQueue<String> blockingQueue;

        private AtomicReference<String[]> atomicReference;

        public MyTask(BlockingQueue<String> blockingQueue,AtomicReference<String[]> atomicReference){
            this.blockingQueue = blockingQueue;
            this.atomicReference = atomicReference;
            System.out.println("传入的BlockingQueue的具体类型是:"+blockingQueue.getClass().getName());
        }
        public void Producer() throws InterruptedException{
            String[] data = atomicReference.get();
            int n = data.length;
            Boolean status;
            Random random = new Random();
            String rs;
            while (flag){
                rs = data[random.nextInt(n)];
                status = blockingQueue.offer(rs,2L, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+" 生产"+rs+(status ? "成功" : "失败"));
                TimeUnit.MILLISECONDS.sleep(100);
            }
            System.out.println(Thread.currentThread().getName()+" 生产者运行结束。");
        }

        public void Consumer() throws InterruptedException{
            String data;
            while (flag){
                data = blockingQueue.poll(2L,TimeUnit.SECONDS);
                TimeUnit.MILLISECONDS.sleep(50);
                if (null == data || data.equalsIgnoreCase("")){
                    System.out.println(Thread.currentThread().getName()+" 消费失败,格子里面是空的#####");
                    flag = false;
                    return;
                }else{
                    System.out.println(Thread.currentThread().getName()+" 消费"+data+"成功~~~~~");
                }
            }
        }

        public void setStop(){
            this.flag = false;
            System.out.println("老板说够了,钱已经赚够了.");
        }
    }

    public static void main(String[] args)throws InterruptedException{

        String[] arr = {"香蕉","苹果","李子","梨子","脐橙","花生","红枣","葡萄","桑葚","甘蔗"};
        AtomicReference<String[]> atomicReference = new AtomicReference<>();
        atomicReference.set(arr);
        MyTask myTask = new MyTask(new ArrayBlockingQueue<String>(5),atomicReference);

        new Thread(() -> {
            try{
                myTask.Producer();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"Producer").start();


        new Thread(() -> {
            try{
                myTask.Consumer();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"Consumer").start();

        try{ TimeUnit.SECONDS.sleep(1L);}catch (InterruptedException e){}
        myTask.setStop();
    }
}
