package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private volatile Map<String,String> data = new HashMap<>();

    public void set(String key,String val){
        lock.writeLock().lock();
        try{
            Thread thread = Thread.currentThread();
            System.out.println("线程 "+thread.getName()+" 正在写入 key:"+key+",val:"+val);
            data.put(key,val);
            System.out.println("线程 "+thread.getName()+" 写入完成 key:"+key);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key){
        lock.readLock().lock();
        try{
            Thread thread = Thread.currentThread();
            System.out.println("线程 "+thread.getName()+" 正在读取...");
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            String val = data.get(key);
            System.out.println("线程 "+thread.getName()+" 读取["+val+"]成功...");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}

/**
 * 读写锁Demo
 */
public class ReadWriteLockDemo {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);

    private static void read(){
        readWriteLock.readLock().lock();
        String threadName = Thread.currentThread().getName();
        try{
            System.out.println(threadName+"获取到了读锁,开始执行读操作");
            TimeUnit.MILLISECONDS.sleep(100);
        }catch (Exception e){
            System.out.println(threadName+" 发生了异常");
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
            System.out.println(threadName+"读锁被释放了");
        }
    }

    private static void write(){
        readWriteLock.writeLock().lock();
        String threadName = Thread.currentThread().getName();
        try{
            System.out.println(threadName+"获取到了写锁,开始执行写操作");
           // TimeUnit.MILLISECONDS.sleep(300);
        }catch (Exception e){
            System.out.println(threadName+" 发生了异常");
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
            System.out.println(threadName+"写锁被释放了");
        }
    }

    public static void readWriteTest() throws InterruptedException{
        for (int i = 0;i < 10;i++){
            TimeUnit.MILLISECONDS.sleep(10);
            if (i < 5) {
                new Thread(() -> write(), "t" + i).start();
            }else{
                new Thread(() ->read(),"t"+i).start();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException{
        readWriteTest();
//        MyCache cache = new MyCache();
//
//        for (int i = 0;i < 5;i++){
//            final String k = String.valueOf(i);
//            new Thread(() -> { cache.set(k,k); },k).start();
//        }
//
//        for (int i = 0;i < 5;i++){
//            final String k = String.valueOf(i);
//            new Thread(() -> { cache.get(k);},k).start();
//        }
    }
}
