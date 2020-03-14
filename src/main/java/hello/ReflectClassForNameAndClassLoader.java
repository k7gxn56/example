package hello;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Class.forName() 默认会进行初始化操作,jdbc驱动使用此方法
 * ClassLoader 默认只加载到JVM中,并不会进行初始化操作,Spring的IOC就是使用此方法
 */
public class ReflectClassForNameAndClassLoader {
    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("hello.B");
        //Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("hello.B");
        //clazz.newInstance();
        for (Constructor constructor : clazz.getDeclaredConstructors()){
            System.out.println("==============");
            System.out.println("构造方法名称:"+constructor.getName());
            System.out.println("构造方法参数个数:"+constructor.getParameterCount());
            System.out.println("构造方法修饰符:"+ Modifier.toString(constructor.getModifiers()));
            System.out.println("====================");
        }
        Constructor c = clazz.getConstructor(new Class[]{
                String.class
        });
        B b = (B)c.newInstance("财务");
        System.out.println(b.work);


        for (Field field : clazz.getFields()){
            System.out.println(field.getName());
            for (Annotation annotation : field.getAnnotations()) {
                System.out.println(annotation.annotationType());
            }
        }
    }
}
