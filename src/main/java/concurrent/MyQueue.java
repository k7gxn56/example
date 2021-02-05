package concurrent;

/**
 * @author shenxiang
 * @date 2021/2/5 11:51
 * @description
 */
public class MyQueue {

    private String[] data = new String[10];

    private int getIndex = 0;

    private int putIndex = 0;

    private int size = 0;

    public synchronized void put(String element) throws InterruptedException {
        if (size == data.length){
            wait();
        }
        data[putIndex] = element;
        ++putIndex;
        // 数组循环使用
        if (putIndex == data.length) putIndex = 0;
        ++size;
        notify();
    }

    public synchronized String get() throws InterruptedException {
        if (size == 0){
            wait();
        }
        String result = data[getIndex];
        ++getIndex;
        if (getIndex == data.length) getIndex = 0;
        --size;
        notify();
        return result;
    }
}
