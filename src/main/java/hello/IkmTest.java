package hello;

import java.util.stream.Stream;

/**
 * @author shenxiang
 * @date 2020/8/14 12:21
 * @description
 */
public class IkmTest {

    public IkmTest(){
        this(10);
    }
    public IkmTest(int data){
        this.data = data;
    }

    public void display(){
        class Decrementer{
            public void decrement(){
                data--;
            }
        }
        Decrementer d = new Decrementer();
        d.decrement();
        System.out.println("data = "+data);
    }

    private int data;

    public static void main(String[] args) {
//        int data = 0;
//        IkmTest t = new IkmTest();
//        t.display();
//        System.out.println("data = "+data);
//        System.out.println(Stream.iterate(100,i -> ++i).limit(600).filter(integer -> integer > 650).findAny().orElse(0));
        int low = 2,hight = 11;
        if (low % 2 != 0 || hight % 2 != 0){
            System.out.println((hight - low) / 2 + 1);
        }else{
            System.out.println((hight - low) / 2);
        }
    }
}
