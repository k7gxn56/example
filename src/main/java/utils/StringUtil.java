package utils;

import org.springframework.util.StringUtils;

/**
 * @author shenxiang
 * @date 2020/6/18 19:14
 * @description
 */
public class StringUtil {
    public static boolean isNumber(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        return str.matches("^\\d+");
    }
}
