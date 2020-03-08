package hello;

import java.lang.ref.WeakReference;

public class WeakREeferenceDemo {


    public static void main(String[] args){
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);

        System.out.println(o);
        System.out.println(weakReference.get());

        o = null;
        System.gc();

        System.out.println("========================");

        System.out.println(o);
        System.out.println(weakReference.get());
    }
}
