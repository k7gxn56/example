package juc;

/**
 * 使用volatile方式来中断线程的运行
 */
public class VolatileStopThread implements Runnable {

    private volatile boolean canceled = false;

    private ThreadLocal<Integer> total = new ThreadLocal<>();

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"开始run");
        total.set(99999);
        int num = 0;
        try{
            while (!canceled && num <= total.get()){
                if (num % 100 == 0){
                    System.out.println(num+"是100的整数倍");
                }
                num++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        VolatileStopThread r = new VolatileStopThread();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(10);
        System.out.println(thread.getState());
        r.canceled = true;

    }
}
