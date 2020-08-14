package juc;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;

/**
 * @author shenxiang
 * @date 2020/8/14 18:07
 * @description
 */
public class ThreadPoolShutDown {
    public static void main(String[] args) {
        t2();
    }

    static class DivTask implements Runnable{

        private int a,b;

        public DivTask(int a,int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            double r = a / b;
            System.out.println(r);
        }
    }

    public static void t2(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            try {
                Future<?> future = executor.submit(new DivTask(100, i));
                System.out.println(future.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    public static void t1(){
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        String jvmName = runtimeBean.getName();
        System.out.println("JVM Name = "+jvmName);
        Long pid = Long.valueOf(jvmName.split("@")[0]);
        System.out.println("JVM PID = "+pid);
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        int n = 30000;
        for (int i = 0; i < n; i++) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
            for (int j = 0;j < 10;j++){
                executor.execute(() -> {
                    System.out.println("当前线程总数为:"+bean.getThreadCount());
                });
            }
            executor.shutdown();
        }
        System.out.println("线程总数为:"+bean.getThreadCount());
    }
}
