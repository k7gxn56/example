package hello;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类型不安全的问题
 * ArrayList
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args){

        Long start = System.currentTimeMillis();
        int length = 30000;
        Set<String> s = new HashSet<>();
        AtomicInteger integer = new AtomicInteger();
        integer.getAndIncrement();
        List<String> list = Collections.synchronizedList(new ArrayList<>(length));//Vector<length>//ArrayList<>(length);
        //List<String> list = new Vector<>(length);

        for (int i = 0;i < length;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                //System.out.println(list);
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2){

        }
        Long end = System.currentTimeMillis();

        System.out.println("cost time:"+(end - start)+" ms");
//        for (String s : list){
//            System.out.println(s);
//        }

        /**
         * 1、故障现象 java.util.ConcurrentModificationException
         *
         * 2、导致原因
         *
         * 3、解决方案
         *  3.1 Vector<>
         *  3.2 Collections.synchronizedList(new ArrayList<>(length))
         * 4、优化建议
         */
    }
}
