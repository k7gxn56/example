package hello;

public class SingletonDemo {

    private volatile static SingletonDemo singletonDemo = null;


    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+" 我被初始化了.");
    }

    //DCL(Double Check Lock 双端检锁机制)
    public static SingletonDemo geInstance(){
        if (singletonDemo == null){
            synchronized (SingletonDemo.class){
                if (singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args){
        for (int i = 0; i < 50;i++){
            new Thread(() -> {
                SingletonDemo.geInstance();
            }).start();
        }
    }
}
