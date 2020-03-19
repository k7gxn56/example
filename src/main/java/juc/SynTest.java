package juc;

import sun.misc.Contended;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SynTest {

    private volatile static SynTest synTest = null;

    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(1,1);

    private SynTest(){
        System.out.println("me executed.");
    }

    public static SynTest newInstance(){
        if (synTest == null)
        synchronized(SynTest.class){
            if (synTest == null){
                synTest = new SynTest();
            }
        }
        return synTest;
    }
    static class Task implements Runnable{

        Object lock;
        Random random;
        public Task(Object lock){
            this.lock = lock;
        }
        @Override
        public void run(){
            test();
        }

        private void test(){
            final String tName = Thread.currentThread().getName();
            try {
                synchronized (lock) {
                    System.out.println(tName + " 获取锁成功.");
                    ThreadLocal<Integer> sleepTime = new ThreadLocal<>();
                    sleepTime.set(random.nextInt(100));
                    System.out.println(tName+" start sleep "+sleepTime.get()+" ms");
                    //TimeUnit.MILLISECONDS.sleep(sleepTime.get());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    static int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(n);
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : n + 1;
    }
    public static void main(String[] args) throws Exception{

        ExecutorService service = Executors.newFixedThreadPool(10);

        System.out.println(20>>>2);
        System.out.println(-20>>>2);

        Map<String,String> map = new ConcurrentHashMap<>(10);
        map.put("x","y");
//        SynTest s1 = SynTest.newInstance();
//        SynTest s2 = SynTest.newInstance();
//
//        System.out.println(s1 == s2);
//
//        stampedReference.compareAndSet(1,10,1,2);
//        stampedReference.compareAndSet(1,20,1,3);
//
//        System.out.println(stampedReference.getReference());
//        System.out.println(stampedReference.getStamp());
//
//        System.out.println( Runtime.getRuntime().availableProcessors());
//
//        ReentrantLock lock = new ReentrantLock();
//        Condition lock1 = lock.newCondition();
//        String threadName = "main";
//        try{
//            threadName = Thread.currentThread().getName();
//            lock.lock();
//            lock.lock();
//            lock.lock();
//            lock.lock();
//            lock.lock();
//            lock1.await();
//            System.out.println(threadName+" in lock.");
//            TimeUnit.SECONDS.sleep(3);
//        }catch (Exception e){
//            System.out.println("throw Exception");
//            e.printStackTrace();
//        }finally {
//            lock1.signal();
//            System.out.println(threadName+" signal.");
//            lock.unlock();
//            System.out.println(threadName+" unlock.");
//            lock.unlock();
//            lock.unlock();
//            lock.unlock();
//            lock.unlock();
//        }
    }
}
