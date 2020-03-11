package error;

import java.nio.ByteBuffer;

/**
 * JVM 参数 -XX:+PrintGCDetails -XX:MaxDirectMemorySize=2m
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args){
        System.out.println(sun.misc.VM.isBooted());
        System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory() / (1024*1024))+"MB");
         //Runtime.getRuntime().maxMemory();
        ByteBuffer.allocateDirect(3*1024*1024);
    }
}
