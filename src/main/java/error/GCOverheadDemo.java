package error;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * java.lang.OutofMemoryError:GC overhead limit exceeded
 *
 * JVM参数配置
 *
 * -XX:+PrintGCDetails -Xms2m -Xmx3m -XX:MaxDirectMemorySize=1m
 *
 * GC回收时间过长时会抛出OutOfMemoryError。
 * 过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存，
 * 连续多次GC都只回收了不到2%的极端情况下才会抛出。
 * 假设不抛出GC overhead limit错误会导致GC清理的内存会很快再次填满，
 * 迫使GC再次执行，这样就形成恶性循环。
 */
public class GCOverheadDemo
{
    public static void main(String[] args){
        Set<Integer> s = new HashSet<>();
        int i = 0;
        try{
           while (true){
               s.add(++i);
           }
        }catch (Throwable e) {
            System.out.println("=========================");
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
