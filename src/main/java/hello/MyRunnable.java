package hello;

public class MyRunnable implements Runnable{

    public volatile boolean active;
    public void run(){
        active = true;

        while(active){
            System.out.println("XDDSDS");
        }
    }

    public void stop(){
        active = false;
    }
}
