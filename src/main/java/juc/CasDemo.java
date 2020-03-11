package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {


    public static void main(String[] args){
        AtomicInteger i = new AtomicInteger(10);

        System.out.println(i.compareAndSet(10,20));
        System.out.println(i.get());
        System.out.println(i.compareAndSet(10,30));
        System.out.println(i.get());
    }
}
