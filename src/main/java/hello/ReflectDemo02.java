package hello;

import java.lang.reflect.Constructor;

public class ReflectDemo02 {

    public static void main(String[] args) throws Exception{
        String s = new String(new StringBuffer("abc"));

        Constructor constructor = String.class.getConstructor(StringBuffer.class);

        String str = (String) constructor.newInstance(new StringBuffer("Hello"));

        System.out.println(s);
        System.out.println(str.charAt(4));

    }
}
