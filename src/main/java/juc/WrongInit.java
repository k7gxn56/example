package juc;

import java.util.HashMap;
import java.util.Map;

public class WrongInit {

    private Map<Integer,String> students;

    public WrongInit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                students = new HashMap<>();
                students.put(1,"张三");
                students.put(2,"李四");
                students.put(3,"王五");
                students.put(4,"马六");
            }
        }).start();
    }

    public Map<Integer, String> getStudents() {
        return students;
    }

    public static void main(String[] args) throws InterruptedException{
        WrongInit wrongInit = new WrongInit();
        //Thread.sleep(100);
        System.out.println(wrongInit.getStudents().get(1));
    }
}
