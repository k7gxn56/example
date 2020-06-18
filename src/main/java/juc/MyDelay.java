package juc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author shenxiang
 * @date 2020/3/27 11:29
 * @description
 */
public class MyDelay {
    public static void main(String[] args) throws InterruptedException {
        Item item1 = new Item(5L,"item1", TimeUnit.SECONDS);
        Item item2 = new Item(10L,"item2",TimeUnit.SECONDS);
        Item item3 = new Item(15L,"item3",TimeUnit.SECONDS);

        BlockingQueue<Item> queue = new DelayQueue<>();

        queue.put(item1);
        queue.put(item2);
        queue.put(item3);

        System.out.println("begin time:"+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        for (int i = 0; i < 3; i++) {
            Item item = queue.take();
            System.out.format("name:{%s},time:{%s}\n",item.name,LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }
}
