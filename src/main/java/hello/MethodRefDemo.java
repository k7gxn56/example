package hello;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodRefDemo {

    public static void main(String[] args){
        GaoDePoi gaoDePoi = new GaoDePoi();
        gaoDePoi.setName("上海交通大学");

        //实例方法
        Supplier<String> sp = gaoDePoi::getName;

        System.out.println(sp.get());

        //静态方法
        Comparator<Integer> cp = Integer::compareTo;
        System.out.println(cp.compare(2, 1));

        Function<Double,Long> f = Math::round;
        System.out.println(f.apply(3.6));

        //左边两个参数中第一个参数作为右边表达式的调用主体,第二个参数当做右侧表达式的值
        Comparator<String> cp2 = String::compareTo;
        System.out.println(cp2.compare("edc", "eda"));

        BiPredicate<String,String> pd = String::equals;
        System.out.println(pd.test("aaa", "ddd"));

        Function<GaoDePoi,String> function = GaoDePoi::getName;
        System.out.println(function.apply(gaoDePoi));
    }
}
