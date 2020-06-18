package hello;

import org.springframework.util.StringUtils;
import utils.StringUtil;

import java.util.*;

/**
 * 压缩/解压字符串
 * @author shenxiang
 * @date 2020/6/18 19:13
 * @description
 */
public class ZipAndUnzip {
    public static void main(String[] args) {
        String str = "aaaaaavvddsaaa";
        String str2 = "6a2v2d1s3a";
        // 变为6a2v2d1s3a
        //t1(str);
        //t2(str);
        // 变为aaaaaavvddsaaa
        t3(str2);

    }

    private static void t3(String str){
        if (StringUtils.isEmpty(str)){
            System.out.println();
        }
        String[] split = str.split("");
        String numStr = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            if (StringUtil.isNumber(s)){
                numStr += s;
            }else{
                Integer n = Integer.parseInt(numStr);
                for (int i = 0; i < n; i++) {
                    stringBuilder.append(s);
                }
                numStr = "";
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private static void t2(String str){
        if (StringUtils.isEmpty(str)){
            System.out.println();
        }
        String[] split = str.split("");
        List<String> list = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String s : split) {
            if (list.isEmpty()){
                list.add(s);
            } else {
                if (!s.equals(list.get(0))) {
                    strList.add(list.size() + list.get(0));
                    list.clear();
                }
                list.add(s);
            }
        }
        if (list.size() > 0){
            strList.add(list.size()+list.get(0));
        }
        for (String s : strList) {
            System.out.print(s);
        }
        System.out.println();
    }
    private static void t1(String str){
        Map<String,Integer> arr = new HashMap<>();
        List<String> result = new ArrayList<>();
        String[] split = str.split("");
        String x = split[0];
        for (String s : split) {
            if (x.equals(s)){
                if (arr.containsKey(s)){
                    arr.put(s,arr.get(s)+1);
                }else{
                    arr.put(s,1);
                }
            }else{
                x = s;
                Set<Map.Entry<String, Integer>> entrySet = arr.entrySet();
                for (Map.Entry<String, Integer> entry : entrySet) {
                    result.add(entry.getValue()+entry.getKey());
                    arr.remove(entry.getKey());
                }
                arr.put(s,1);
            }
        }
        if (arr.size() > 0){
            Set<Map.Entry<String, Integer>> entrySet = arr.entrySet();
            for (Map.Entry<String, Integer> entry : entrySet) {
                result.add(entry.getValue()+entry.getKey());
                arr.remove(entry.getKey());
            }
        }

        for (String s : result) {
            System.out.print(s);
        }
        System.out.println();
    }
}
