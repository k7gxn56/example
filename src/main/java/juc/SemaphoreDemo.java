package hello;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 6个人去只有3个坑位的卫生间上厕所
 */
public class SemaphoreDemo {

    private final static Integer num = 6;

    public static void main(String[] args){

        Semaphore semaphore = new Semaphore(3);
        Random random = new Random();
        for (int i = 1; i <= num;i++){
            new Thread(() -> {
                try{
                    semaphore.acquire();
                    System.out.println("现在还有"+semaphore.availablePermits()+"坑位");
                    System.out.println("第"+Thread.currentThread().getName()+"人 已经占领了1个坑位。");
                    Integer cost = random.nextInt(1500);
                    TimeUnit.MILLISECONDS.sleep(cost);
                    System.out.println("第"+Thread.currentThread().getName()+"人花了"+cost+"毫秒方便好了,已经离开了");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
