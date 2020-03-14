package juc;

public class WrongResult {

    private static Integer i = 0;

    public static void main(String[] args) throws InterruptedException{

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0;j < 10000;j++){
                    i++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j =0;j < 10000;j++){
                    i++;
                }
            }
        });

        System.out.println(Thread.MIN_PRIORITY);
        System.out.println(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
//        while (Thread.activeCount() >2){
//
//        }
        System.out.println(i);
    }
}
