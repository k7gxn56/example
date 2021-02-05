package concurrent;

/**
 * @author shenxiang
 * @date 2021/2/5 14:49
 * @description
 */
public class MyQueue2 extends MyQueue {

    private String[] data = new String[10];

    private int getIndex = 0;

    private int putIndex = 0;

    private int size = 0;

    @Override
    public synchronized void put(String element) {
        while (size == data.length){
            try {
                    wait();
            }catch (Exception e){
                e.printStackTrace();
            }
            put(element);
        }
        put0(element);
        notify();
    }

    public void put0(String element){
        data[putIndex] = element;
        ++putIndex;
        if (putIndex == data.length) putIndex = 0;
        ++size;
    }

    @Override
    public synchronized String get() {
        while (size == 0){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return get();
        }
        String result = get0();
        notify();
        return result;
    }

    public String get0(){
        String result = data[getIndex];
        ++getIndex;
        if (getIndex == data.length) getIndex = 0;
        --size;
        return result;
    }
}
