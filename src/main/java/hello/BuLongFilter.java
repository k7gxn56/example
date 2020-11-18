package hello;

import java.util.regex.Pattern;

/**
 * Guava实现布隆过滤器
 * @author shenxiang
 * @date 2020/8/7 17:34
 * @description
 */
public class BuLongFilter {
    // 插入的元素数量
    private static final int insertions = 1000000;
    // 期望的误判率
    private static double fpp = 0.02;

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5]{0,10}$");
        String t = "我是0900000汉字";
        System.out.println(pattern.matcher(t).find());
    }
}
