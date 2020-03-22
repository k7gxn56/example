package juc;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Vector;

/**
 * 本例主要来演示Vector不是绝对的线程安全,只是相对的线程安全,属于这种类型的还有HashTable、Collections.synchronized容器方法
 * 在jdk1.7版中,如果不在方法调用端做额外同步措施的话，代码仍然是线程不安全的
 */
public class VectorNoThreadSafe {

    private static Vector<Integer> vector = new Vector<>();
    public static void main(String[] args) throws Exception{
        int c = 0;
        while (true){
            for (int i = 0;i < 10;i++){
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    //synchronized (vector) { 使用synchronized关键字在使用端强制同步
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println("线程" + threadName + "移除下标为" + i + "的元素" + vector.remove(i));
                        }
                    //}
                }
            },"removeThread"+c);

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                   // synchronized (vector) {使用synchronized关键字在使用端强制同步
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println("线程" + threadName + "打印下标为" + i + "的元素" + vector.get(i));
                        }
                    //}
                }
            },"printThread"+c);

            removeThread.start();
            printThread.start();

            c++;
            while (Thread.activeCount() > 20);
        }
    }
}
