package juc;


import hello.CountryEnum;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private final static Integer num = 6;

    private final static String[] things =  {"温度正常","风力等级正常","外围环境监测正常","发射塔检测","核心部件正常","宇航员准备正常"};

    public static void main(String[] args) throws Exception{
        //latch();
        QinSkillSixCountry();
    }

    /**
     * 秦灭六国统一中国
     */
    private static void QinSkillSixCountry() throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 1;i <= num;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"国被灭...");
                countDownLatch.countDown();
            }, CountryEnum.getCountryName(i)).start();
        }
        countDownLatch.await();
        System.out.println("秦灭六国完成,中国重新统一...");
    }

    /**
     * 火箭发射前的检查
     * @throws Exception
     */
    private static void latch() throws Exception{
        System.out.println("火箭发射前的检测工作......");
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 5;i >= 0;i--){
            final String k = String.valueOf(i);
            final int n = i;
            new Thread(() -> {
                System.out.println("线程 "+Thread.currentThread().getName()+","+things[n]);
                countDownLatch.countDown();
            },k).start();
        }
        countDownLatch.await();
        System.out.println("报告,准备完成,准备倒计时...");
        for (int i = 5;i >= 0;i--) {
            System.out.println(Thread.currentThread().getName()+"倒计时:"+i);
        }
        System.out.println("倒计时完成,火箭发射...");
    }
}
