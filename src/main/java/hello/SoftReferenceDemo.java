package hello;


import java.lang.ref.SoftReference;

/**
 * 软引用
 */
public class SoftReferenceDemo {

    public static void softRef_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * -Xms1m -Xmx1m -XX:+PrintGCDetails
     */
    public static void softRef_NoEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        try{
            byte[] b = new byte[200*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }


    public static void main(String[] args){
        //softRef_Enough();
        softRef_NoEnough();
    }
}
