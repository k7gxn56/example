package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MyZiXunLock {

    private AtomicReference<Thread> reference = new AtomicReference<>();

    private int n = 0;

    public void lock(){
        Thread thread = Thread.currentThread();
        if (thread.equals(reference.get())){
            ++n;
            System.out.println(thread.getName()+"获取到了锁,当前n="+n);
            return;
        }
        do {
            //System.out.println(thread.getName()+"自旋中.");
        }while (!reference.compareAndSet(null,thread));
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        if (thread.equals(reference.get())){
            if (n > 0){
                n--;
            }else{
                reference.set(null);
            }
            System.out.println(thread.getName()+" 解锁了,n="+n);
        }else{
            System.out.println(thread.getName()+"不是当前持有锁的线程"+reference.get().getName());
        }
    }

    public static void main(String[] args) throws Exception{
        MyZiXunLock m1 = new MyZiXunLock();
        MyZiXunLock m2 = new MyZiXunLock();
        Map<String,Object> map = new HashMap<>(16);
        map.put("name","zzz");
       // TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            try {
                m1.lock();
                m2.lock();
                m1.lock();
                m2.lock();
                TimeUnit.MILLISECONDS.sleep(200);
                m1.unlock();
                m1.unlock();
                m1.unlock();
                m2.unlock();
                m2.unlock();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                m1.lock();
                m1.lock();
                m1.lock();
                TimeUnit.SECONDS.sleep(1);
                m1.unlock();
                m1.unlock();
                m1.unlock();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t2").start();

    }
}
