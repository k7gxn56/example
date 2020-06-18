package juc;

/**
 * @author shenxiang
 * @date 2020/5/21 17:44
 * @description
 */
public class DaemonThread extends Thread{

    public void run(){
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setName("daemonThread");
        daemonThread.setPriority(10);
        daemonThread.setDaemon(true);
        daemonThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+","+i);
        }
    }
}
