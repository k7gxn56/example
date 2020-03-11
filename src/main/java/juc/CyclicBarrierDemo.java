package juc;

import hello.CountryEnum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final Integer num = 6;

    public static void main(String[] args){

        CyclicBarrier cyclicBarrier = new CyclicBarrier(6,()->{
            System.out.println("秦国完成统一大业");
        });

        for (int i = 1;i <= num;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"国被灭...");
                try{
                    cyclicBarrier.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (BrokenBarrierException e){
                    e.printStackTrace();
                }
            }, CountryEnum.getCountryName(i)).start();
        }
    }
}
