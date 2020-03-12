package hello;

class Num{

    Integer n = 0;

    public synchronized void add(){
        n++;
    }
//    public AtomicInteger n = new AtomicInteger(0);
//
//    public void setVal(AtomicInteger n){
//        this.n = n;
//    }
//
//    public void add(){
//        n.getAndIncrement();
//    }
}
public class MyValue {

    public static void main(String[] args){
        Long start = System.currentTimeMillis();

        Num m = new Num();
        for (int i = 0;i < 100;i++){
            new Thread(() -> {
                for (int j = 0;j < 1000;j++){
                    m.add();
                }
                System.out.println(Thread.currentThread().getName()+" executed n = "+m.n);
            },"Thread "+i).start();
        }

        System.out.println(Thread.currentThread().getName()+" n = "+m.n);

        while (Thread.activeCount() > 2){
            Thread.yield();
            //System.out.println("while "+Thread.currentThread().getName()+" n = "+m.n);
        }

        System.out.println(Thread.currentThread().getName()+" n = "+m.n);
        Long end = System.currentTimeMillis();

        System.out.println("cost time:"+(end - start)+" ms");
    }
}