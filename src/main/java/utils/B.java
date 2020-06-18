package utils;

import java.util.ArrayList;

/**
 * @author shenxiang
 * @date 2020/5/10 19:28
 * @description
 */
public class B {

    public static void main(String[] args) {
        ArrayList<Class> allClassByInterface = ClassUtil.getAllClassByInterface(A.class);

        for (Class aClass : allClassByInterface) {
            System.out.println(aClass.getTypeName());
        }
    }
}
