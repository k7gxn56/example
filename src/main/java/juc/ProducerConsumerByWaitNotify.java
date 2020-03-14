package juc;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerByWaitNotify {

    private Integer size = 10;

    private Queue queue;

    public ProducerConsumerByWaitNotify(Integer size){
        this.size = size;
        queue = new LinkedList();
    }

    public void add(Object o){
        try {
            synchronized (this){
                while (queue.size() == size){
                    wait();
                }
                queue.add(o);
                notifyAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public Object remove(){
        Object o = new Object();
        try{
            synchronized (this){
                while (queue.size() == 0){
                    wait();
                }
                o = queue.remove();
                notifyAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args){
        ProducerConsumerByWaitNotify producerConsumerByWaitNotify = new ProducerConsumerByWaitNotify(12);
        for (int i = 0;i < 10;i++){
            if (i < 5){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int n = 0;
                        while (true) {
                            if (n > 30){
                                Thread.currentThread().interrupt();
                                break;
                            }
                            Long currentTime = System.currentTimeMillis();
                            System.out.println(Thread.currentThread().getName() + "生产了" + currentTime);
                            producerConsumerByWaitNotify.add(currentTime);
                            n++;
                        }
                    }
                },"p"+i).start();
            }else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int n = 0;
                        while (true) {
                            if (n > 30){
                                Thread.currentThread().interrupt();
                                break;
                            }
                            Object data = producerConsumerByWaitNotify.remove();
                            System.out.println(Thread.currentThread().getName() + "消费了" + data);
                            n++;
                        }
                    }
                },"c"+i).start();
            }
        }
    }
}
