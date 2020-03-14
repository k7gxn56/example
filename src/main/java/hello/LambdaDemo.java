package hello;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaDemo {

    public static void main(String[] args){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("我执行了");
            }
        };
        r.run();
        Runnable r2 = () -> System.out.println("我用Lambda方式执行了");
        r2.run();

        Comparator<Integer> r3 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(r3.compare(1,2));

        Comparator<Integer> r4 = (o1,o2) -> o1.compareTo(o2);

        System.out.println(r4.compare(1,1));
        //这里是方法引用
        Comparator<Integer> r5 = Integer::compareTo;

        System.out.println(r5.compare(3,4));

        YourInterface r6 = s -> System.out.println(s);

        r6.echo("一个是听的人当真了,一个是说的人当真了");

        Consumer<String> r7 = s -> System.out.println(s);

        r7.accept("juc的消费者");

        Supplier<String> r8 = () -> {return "producer";};

        System.out.println(r8.get());

        Function<String,Integer> r9 = a -> Integer.parseInt(a);
        System.out.println(r9.apply("35"));

        List<String> list = Arrays.asList("南京","西京","普京","天津","北京","虚惊","东京","盛京");
        System.out.println(predicateFunc(list, s -> s.contains("京")));

        PrintStream ps = System.out;
        Consumer<String> c12 = ps::println;
        c12.accept("我使用方法引用来进行调用");

        consumerFunc(500.00,monry -> System.out.println("太累了,去迪士尼消费了"+monry+"大洋"));

        System.out.println(producerFunc(() -> "今年生产了10亿吨大米"));

    }

    private static List<String> predicateFunc(List<String> list,Predicate<String> predicate){
        List<String> rs = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)){
                rs.add(s);
            }
        }
        return rs;
    }

    private static void consumerFunc(Double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

    private static String producerFunc(Supplier<String> stringSupplier){
        return stringSupplier.get();
    }
}
