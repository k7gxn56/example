package algorithm;

/**
 * @author shenxiang
 * @date 2020/12/2 9:51
 * @description
 */
public class Fibonacci {
    /**
     * 递归解法
     * @param n
     * @return
     */
    static int fun(int n){
        if (n == 1 || n == 2){
            return 1;
        }else{
            int prev = fun(n-1);
            int next = fun(n-2);
            System.out.println("prev = "+prev+",next = "+next);
            return  prev + next;
        }
    }

    static int fun2(int n){
        int[] a = new int[n+1];
        a[1] = 1;
        a[2] = 1;
        for (int i = 3; i <= n; i++) {
            a[i] = a[i-1] + a[i -2];
        }
        return a[n];
    }

    public static void main(String[] args) {
        System.out.println(fun(7));
        System.out.println(fun2(7));
    }
}
