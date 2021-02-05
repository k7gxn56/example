package concurrent;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * @author shenxiang
 * @date 2021/2/5 14:36
 * @description
 */
public class ConsumerThread extends Thread {

    private final MyQueue myQueue;

    private final Random random = new Random();

    public ConsumerThread(MyQueue myQueue){
        this.myQueue = myQueue;
    }

    @SneakyThrows
    @Override
    public void run() {
        final String s = this.myQueue.get();
        System.out.println("消费了元素:" + s);
        Thread.sleep(random.nextInt(1000));
    }
}
