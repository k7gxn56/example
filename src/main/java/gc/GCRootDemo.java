package hello;


/**
 * 在java中，可以作为GC Roots的对象
 * 1、虚拟机栈中的对象
 * 2、方法区中类静态属性引用的对象
 * 3、方法区中常量引用的对象
 * 4、本地方法栈中JNI(即Native方法)中引用的对象
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[12 * 1024 *1024];

    private static void m1(){

        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) throws Exception{
        m1();
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"我在主线程退出时运行一下");
        },"T1");
        Runtime.getRuntime().addShutdownHook(t);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
