package algorithm;

/**
 * @author shenxiang
 * @date 2020/12/2 10:04
 * @description
 */
public class Sum {

    static int func(int n){
        if (n == 1){
            return 1;
        }else{
            return func(n - 1) + n;
        }
    }

    static int func2(int n){
        int a[] = new int[n+1];
        a[1] = 1;
        for (int i = 2;i <= n;i++){
            a[i] = a[i-1] +i;
        }
        return a[n];
    }

    public static void main(String[] args) {
        System.out.println(func(100));
        System.out.println(func2(100));
    }
}
