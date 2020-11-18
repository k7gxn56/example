package hello;

import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * @author shenxiang
 * @date 2020/9/2 14:03
 * @description
 */
public class IteratorClass {
    public Iterator<String> iterator(){
        return new Itr();
    }

    private class Itr implements Iterator<String>{
        protected String[] words = {"Hello","Java"};
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < words.length;
        }

        @Override
        public String next() {
            return words[index++];
        }

        @Override
        public void remove() {
            System.out.println("invoke remove...");
        }
    }

    public static void main(String[] args){
        Pattern p = Pattern.compile("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9])|(19[0,5-9]))\\d{8}$|^0\\d{2,3}-?\\d{7,8}$");
        System.out.println(p.matcher("5789407").matches());
        System.out.println(p.matcher("0791-5789407").matches());
        System.out.println(p.matcher("19062398660").matches());

    }
}
