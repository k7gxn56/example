package juc;

/**
 * 死锁示例
 */
public class ThreadDeadDemo {

    private Object o1 = new Object();
    private Object o2 = new Object();

    public void t1()throws InterruptedException{
        synchronized (o1){
            Thread.sleep(100);
            synchronized (o2){
                System.out.println("t1获取锁成功");
            }
        }
    }

    public void t2() throws InterruptedException{
        synchronized (o2){
            Thread.sleep(100);
            synchronized (o1){
                System.out.println("t2获取锁成功");
            }
        }
    }


    public static void main(String[] args){
        ThreadDeadDemo threadDeadDemo = new ThreadDeadDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    threadDeadDemo.t1();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threadDeadDemo.t2();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
