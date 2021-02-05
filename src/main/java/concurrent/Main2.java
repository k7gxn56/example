package concurrent;

import java.util.concurrent.*;

/**
 * @author shenxiang
 * @date 2021/2/5 11:02
 * @description
 */
public class Main2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10)){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }
        };
        final Future<String> future = executor.submit(new MyCallable());
        final String s = future.get();
        System.out.println(s);
        executor.shutdown();
    }
}
