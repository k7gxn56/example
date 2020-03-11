package juc;


import java.util.concurrent.TimeUnit;

/**
 * 多线程死锁示例
 */
public class DeadLockDemo {

    static class MyLock implements Runnable{

        private String lock1;

        private String lock2;

        public MyLock(String lock1, String lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()+"开始对"+lock1+"加锁");
            synchronized (lock1){
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"开始尝试对"+lock2+"加锁");
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName()+"对"+lock2+"加锁成功");
                }
            }
        }
    }


    public static void main(String[] args) throws Exception{
        String lock1 = "Lock_A";
        String lock2 = "Lock_B";
        new Thread(new MyLock(lock1,lock2) ,"T1").start();
        new Thread(new MyLock(lock2,lock1),"T2").start();

        //TimeUnit.HOURS.sleep(3);
    }
}
