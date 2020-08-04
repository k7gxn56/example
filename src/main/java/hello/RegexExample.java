package hello;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 正则表达式的4种断言
 * (?=pattern) 零宽正向先行断言(zero-width positive lookahead assertion)
 * (?!pattern) 零宽负向先行断言(zero-width negative lookahead assertion)
 * (?<=pattern) 零宽正向后行断言(zero-width positive lookbehind assertion)
 * (?<!pattern) 零宽负向后行断言(zero-width negative lookbehind assertion)
 * @author shenxiang
 * @date 2020/8/4 14:35
 * @description
 */
public class RegexExample {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "%3D%E3%80%9019.8%E5%85%836%E7%9B%92%E9%99%90%E6%97%B6%E7%A7%92%E6%9D%80%E3%80%91%E6%AF%8F%E6%97%A5%E9%BB%91%E5%B7%A7%E5%AD%90%E5%BC%B9%E5%9D%9A%E6%9E%9C%2C%E8%BF%9B%E5%8F%A3%E5%8F%AF%E5%8F%AF%E8%84%8298%25%E9%BB%91%E5%B7%A7%E5%85%8B%E5%8A%9B%EF%BC%8C%EF%BC%88%E6%A6%9B%E5%AD%9048g%2A2%2B%E5%B7%B4%E6%97%A6%E6%9C%A848g%2A2%2B%E6%A6%9B%E5%AD%9030g%2A1%2B%E5%B7%B4%E6%97%A6%E6%9C%A830g%2A1%EF%BC%89%EF%BC%8C%E4%BF%9D%E8%B4%A8%E6%9C%9F2020%E5%B9%B410%E6%9C%8814%E6%97%A5-11%E6%9C%8824%E6%97%A5%EF%BC%88%E5%90%84%E5%8F%A3%E5%91%B3%E6%9C%89%E6%95%88%E6%9C%9F%E4%B8%8D%E5%90%8C%EF%BC%89%26";
        String s = URLDecoder.decode(str, "UTF-8");
        System.out.println(s);
        t1();
        t2();
        t3();
        t4();
    }

    /**
     * (?=pattern) 正向先行断言
     */
    public static void t1(){
        String str = "a regular expression";
        // 要想匹配regular中的re，但不能匹配expression中的re
        String s = str.replaceAll("re(?=)gular", "xxxx");
        System.out.println(s);
    }

    /**
     * (?!pattern) 负向先行断言
     */
    public static void t2(){
        String str = "regex represents regular expression";
        // 要想匹配除regex和regular之外的re
        System.out.println(str.replaceAll("re(?!g)", "###"));
    }

    /**
     * (?<=pattern) 正向后行断言
     */
    public static void t3(){
        String str = "regex represents regular expression";
        // 想匹配单词内部的re，但不匹配单词开头的re
        System.out.println(str.replaceAll("(?<=\\w)re", "@@"));
    }

    /**
     * (?<!pattern) 负向后行断言
     */
    public static void t4(){
        String str = "regex represents regular expression";
        // 要想匹配单词开头的re
        System.out.println(str.replaceAll("(?<!\\w)re", "++"));
    }
}
