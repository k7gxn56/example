package hello;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructRefDemo {

    public static void main(String[] args){

        BiConsumer<String,String> c0 = GaoDePoi::new;
        c0.accept("0000","3434443");
        Consumer<String> c1 = GaoDePoi::new;
        c1.accept("11111");
        Runnable runnable = GaoDePoi::new;
        runnable.run();

        Function<Integer,String[]> function = String[]::new;
        System.out.println(Arrays.asList(function.apply(3)));
    }
}
