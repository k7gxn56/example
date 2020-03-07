package hello;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyException {

    private static Logger logger = Logger.getLogger(MyException.class.toString());
    public static void main(String[] args){
        //throw new IOException("I/O异常.");
        //throw new FileFormatException("文件格式不符合要求.");
//        try{
//            FileReader fr = new FileReader("E:/xxx.xxx");
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//            System.out.println(e.getCause());
//        }finally {
//            System.out.println("走到这里就进了finally");
//        }
//        System.out.println(f(9));
//
//        Throwable t = new Throwable();
//        StackTraceElement[] frames = t.getStackTrace();
//        for (StackTraceElement f : frames){
//            System.out.println(f.getClassName());
//        }
//        Map<Thread,StackTraceElement[]> map = Thread.getAllStackTraces();
//        for (Thread t : map.keySet()){
//            StackTraceElement[] frames = map.get(t);
//            for (StackTraceElement e : frames){
//                System.out.println("================");
//                System.out.println(e.toString());
//                System.out.println("xxxxxxxxxxxxxxxx");
//            }
//        }
        logger.setLevel(Level.FINE);
        f(10);
        logger.info("ABCDEFG");

    }

    public static int f(int n){
        logger.warning("n="+n);
        try{
            int r = n * n;
            return r;
        }finally {
            if (n == 2)
                return 0;
        }
    }
}
