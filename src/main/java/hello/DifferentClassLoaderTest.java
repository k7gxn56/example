package hello;

import java.io.InputStream;

/**
 * 非双亲委派模型的类加载器
 */
public class DifferentClassLoaderTest
{
    public static void main(String[] args)throws Exception{
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null){
                    return super.loadClass(name);
                }
                try{
                    byte[] b = new byte[stream.available()];
                    stream.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };

        System.out.println(ClassLoader.getSystemClassLoader());

        Object obj = classLoader.loadClass("hello.DifferentClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof DifferentClassLoaderTest);
    }
}
