package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author shenxiang
 * @date 2020/8/17 11:16
 * @description
 */
public class ListOp {
    public static void main(String[] args) {
        Map<String,String> treeMap = new TreeMap<>();
    }

    public static void t1(){
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l1.add("A");
        l1.add("B");
        l1.add("D");
        l1.add("E");
        l2.add("B");
        l2.add("C");
        l2.add("D");
        l2.add("F");
        // 求交集
        l1.retainAll(l2);
        System.out.println(l1);
//        List<String> retainCollection = l1.stream().filter(item -> l2.contains(item)).collect(Collectors.toList());
//        System.out.println(retainCollection);
        // 差集
        l1.removeAll(l2);
        //l1.stream().filter(item -> !l2.contains(item))
        System.out.println(l1);
    }
}
