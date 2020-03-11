package hello;

public class HelloGC {
    public static void main(String[] args) throws Exception{

        Long totalMemory = Runtime.getRuntime().totalMemory();
        Long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+"(字节 ) "+(totalMemory / (1024*1024))+"M");
        System.out.println("MAX_MEMORY(-Xmx) = "+maxMemory+"(字节)"+(maxMemory / (1024*1024))+"M");
        Thread t = new Thread(()->{
            System.out.println("我是ShutdownHook,在程序退出前,我运行了。。。");
        },"ShutdownHook");
         Runtime.getRuntime().addShutdownHook(t);
         Runtime.getRuntime().exit(0);
    }
}
