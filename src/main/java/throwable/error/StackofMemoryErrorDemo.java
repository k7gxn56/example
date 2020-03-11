package throwable.error;

/**
 * -XX:+PrintGCDetails -Xss1m
 */
public class StackofMemoryErrorDemo {

    public static void main(String[] args) throws InterruptedException{
        //Thread.sleep(Integer.MAX_VALUE);
        test();
    }

    public static void test(){
        test();
    }
}
