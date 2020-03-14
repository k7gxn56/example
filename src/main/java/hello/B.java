package hello;

import org.springframework.beans.factory.annotation.Value;

public class B extends A {

    static {
        System.out.println("执行了静态代码块");
    }

    private static String staticField = staticMethod();

    public B(){

    }
    public B(String work){
        this.work = work;
    }

    public B(Integer salary){
        this.salary = salary;
    }

    private B(String work,Integer salary){
        this.work = work;
        this.salary = salary;
    }


    @Value("${a.b}")
    public String work;

    private Integer salary;

    public static String staticMethod(){
        System.out.println("执行了静态方法");
        return "给静态字段赋值了";
    }
}
