package juc;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author shenxiang
 * @date 2020/6/16 9:30
 * @description
 */
public class Volatile implements Runnable{

    private static volatile boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag){
            i++;
            System.out.println(System.currentTimeMillis()+","+i);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" 执行完毕.");
    }

    public static void main(String[] args) {
        Volatile v = new Volatile();
        new Thread(v,"Thread A").start();
        System.out.println("main 线程正在运行.");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String value = sc.next();
            if (value.equals("1")){
                new Thread(() -> {
                    flag = false;
                }).start();
                break;
            }
        }
        System.out.println("main 线程已经退出了!");
    }
}
