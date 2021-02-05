package concurrent;

import lombok.SneakyThrows;

/**
 * @author shenxiang
 * @date 2021/2/5 16:17
 * @description
 */
public class MyDaemonThread extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            System.out.println(System.currentTimeMillis()+" -> daemon "+Thread.currentThread().getName());
            Thread.sleep(2000);
        }
    }
}
