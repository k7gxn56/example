package concurrent;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenxiang
 * @date 2021/2/5 14:31
 * @description
 */
public class ProducerThread extends Thread {


    private final MyQueue myQueue;

    private final Random random = new Random();

    private AtomicInteger index;

    public ProducerThread(MyQueue myQueue,AtomicInteger index){
        this.myQueue = myQueue;
        this.index = index;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            String item = "element-"+index;
            this.myQueue.put(item);
            System.out.println("添加了元素:"+item);
            index.getAndIncrement();
            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
