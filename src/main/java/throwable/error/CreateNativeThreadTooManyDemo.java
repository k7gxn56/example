package throwable.error;

import java.util.concurrent.TimeUnit;

/**
 * 请在linux下面使用非root用户进行测试,查看linux默认支持最大数 ulimit -u
 */
public class CreateNativeThreadTooManyDemo {

    public static void main(String[] args){
        for (int i = 0;i < 999999;i++){
            final int tmp = i;
            new Thread(() -> {
                System.out.println( "我是线程"+tmp);
                try{
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            },"t"+i).start();
        }
    }
}
