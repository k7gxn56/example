package hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shenxiang
 * @date 2020/7/13 14:24
 * @description
 */
public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method.invoke(proxy,args);
        System.out.println("Hello Lvmama.");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        InvocationHandler handler = new MyInvocationHandler();
        Class proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(),new Class[]{Foo.class});
        Foo f = (Foo)proxyClass.getConstructor(new Class[]{InvocationHandler.class}).newInstance(new Object[]{handler});
        f.doSomething();
    }
}
