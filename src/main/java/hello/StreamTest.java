package hello;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        List<Car> carList = Arrays.asList(
                new Car("benz", 100),
                new Car("bmw", 200),
                new Car("qq", 50),
                new Car("qq", 60)
        );

        Car c = carList.stream()
                .filter(car -> car.getName().equals("qq"))
                .filter(car -> car.getPrice() < 60)
                .findFirst().get();

        System.out.println(c.name+":"+c.price);
    }

    static class Car{
        private String name;
        private Integer price;

        public Car(String name, Integer price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            //System.out.println(name);
            return name;
        }

        public Integer getPrice() {
            //System.out.println(price);
            return price;
        }
    }
}
