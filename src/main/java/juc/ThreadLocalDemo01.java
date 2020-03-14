package juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo01 {

    public static void main(String[] args) throws InterruptedException{
        new Thread(() -> {
            ThreadLocal<String> date = new ThreadLocal<>();
            date.set(new ThreadLocalDemo01().date(System.currentTimeMillis()));
            System.out.println(Thread.currentThread().getName()+":"+date.get());
        },"t1").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            ThreadLocal<String> date = new ThreadLocal<>();
            date.set(new ThreadLocalDemo01().date(System.currentTimeMillis()));
            System.out.println(Thread.currentThread().getName()+":"+date.get());
        },"t2").start();
    }

    public String date(Long ms){
        Date date = new Date(ms);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
