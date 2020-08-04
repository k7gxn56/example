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
//        InvocationHandler handler = new MyInvocationHandler();
//        Class proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(),new Class[]{Foo.class});
//        Foo f = (Foo)proxyClass.getConstructor(new Class[]{InvocationHandler.class}).newInstance(new Object[]{handler});
//        f.doSomething();
        String cmd =
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/1982f34e5285890804349232782/f0.mp4 -O ./127/1.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/f65ce0fd5285890804345996766/f0.mp4 -O ./127/2.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/00dd51665285890804342314546/f0.mp4 -O ./127/3.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/f6cd30215285890804335103264/f0.mp4 -O ./127/4.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/927fab2b5285890804527357035/f0.mp4 -O ./128/1.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/b489775d5285890804524704001/f0.mp4 -O ./128/2.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/85c8826e5285890804520972645/f0.mp4 -O ./128/3.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/397c66535285890804519099561/f0.mp4 -O ./140/1.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/3b2a3ea45285890804519101184/f0.mp4 -O ./140/2.mp4\n" +
                "wget http://1258344707.vod2.myqcloud.com/1b87576bvodcq1258344707/a1b8dff25285890804517179446/f0.mp4 -O ./140/3.mp4";


    }
}
