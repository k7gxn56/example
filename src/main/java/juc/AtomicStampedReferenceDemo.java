package hello;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,5);

    public static void main(String[] args){
        System.out.println("===========ABA问题的产生====================");

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+",更新成功:"+ atomicReference.compareAndSet(100,150)+",最新结果:"+atomicReference.get());
            System.out.println(Thread.currentThread().getName()+",更新成功:"+ atomicReference.compareAndSet(150,100)+",最新结果:"+atomicReference.get());
        },"t1").start();

        new Thread(() -> {
            try{ TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e){ e.printStackTrace();}
            System.out.println(Thread.currentThread().getName()+",最新值:"+atomicReference.get());
        },"t2").start();


        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("=======ABA问题的解决================");
            String currentThreadName = Thread.currentThread().getName();
            int stamp = atomicStampedReference.getStamp();
            int val = atomicStampedReference.getReference();
            System.out.println(currentThreadName+",当前的版本:"+stamp+",当前值:"+val);
            System.out.println(currentThreadName+",更改成功:"+atomicStampedReference.compareAndSet(100,200,stamp,stamp+1));

            stamp = atomicStampedReference.getStamp();
            val = atomicStampedReference.getReference();
            System.out.println(currentThreadName+",当前的版本:"+stamp+",当前值:"+val);

            System.out.println(currentThreadName+",更改成功:"+atomicStampedReference.compareAndSet(atomicStampedReference.getReference(),100,atomicStampedReference.getStamp(),stamp+1));
            stamp = atomicStampedReference.getStamp();
            val = atomicStampedReference.getReference();
            System.out.println(currentThreadName+",当前的版本:"+stamp+",当前值:"+val);
        },"t3").start();

        new Thread(() -> {
            String currentThreadName = Thread.currentThread().getName();
            int stamp = atomicStampedReference.getStamp();
            int val = atomicStampedReference.getReference();
            System.out.println(currentThreadName+",当前版本:"+stamp+",当前值:"+val);

            try {
                TimeUnit.SECONDS.sleep(2);
                boolean updateFlag = atomicStampedReference.compareAndSet(val,2019,stamp,stamp+1);
                System.out.println(currentThreadName+",修改结果:"+updateFlag);
                stamp = atomicStampedReference.getStamp();
                val = atomicStampedReference.getReference();
                System.out.println(currentThreadName+",当前版本:"+stamp+",当前值:"+val);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t4").start();
    }

}
