package concurrent;

import java.util.concurrent.Callable;

/**
 * @author shenxiang
 * @date 2021/2/5 10:40
 * @description
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return System.currentTimeMillis() +" --> "+Thread.currentThread().getName()+" call() invoked!";
    }
}
