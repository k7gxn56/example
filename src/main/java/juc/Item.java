package juc;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author shenxiang
 * @date 2020/3/27 11:45
 * @description
 */
public class Item implements Delayed {

    private Long time;

    String name;

    public Item(Long time, String name,TimeUnit unit) {
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
        this.name = name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Item item = (Item) o;
        Long diff = this.time - item.time;
        if (diff <= 0){
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Item{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}
