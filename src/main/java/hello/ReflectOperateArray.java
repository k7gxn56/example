package hello;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class ReflectOperateArray {

    public static void main(String[] args) throws Exception{
        //t2();
        t3();
    }

    //利用反射对数组进行扩容
    private static void t3(){
        int array[] = {1,2,3,4,5};
        System.out.println("初始长度:"+array.length);
        System.out.println("新数组长度"+((int[])doubleArray(array)).length);
        System.out.println(((int[])doubleArray(array))[1]);
    }
    private static Object doubleArray(Object original){
        Object returnValue = null;
        Class<?> type = original.getClass();
        if (type.isArray()){
            int length= Array.getLength(original);
            returnValue = Array.newInstance(int.class,length *2);
            System.arraycopy(original,0,returnValue,0,length);
        }
        return returnValue;
    }

    /**
     * 利用反射创建、填充和显示数组
     */
    private static void t2(){
        Object array = Array.newInstance(int.class,3);
        printType(array);
        fillArray(array);
        displayArray(array);
    }

    private static void printType(Object object){
        Class<?> type = object.getClass();
        if (type.isArray()){
            Class<?> elementType = type.getComponentType();
            System.out.println("Array of:"+elementType);
            System.out.println("Array size:"+Array.getLength(object));
        }
    }

    private static void fillArray(Object array){
        int length = Array.getLength(array);
        Random generator = new Random(System.currentTimeMillis());
        for (int i = 0;i < length;i++){
            int random = generator.nextInt();
            Array.setInt(array,i,random);
        }
    }

    private static void displayArray(Object array){
        int length = Array.getLength(array);
        for (int i = 0;i < length;i++){
            int value = Array.getInt(array,i);
            System.out.println("position:"+i+",value:"+value);
        }
    }

    public static void t1(){
        int[] arr = {1,2,3,4,5};

        Class<?> c = arr.getClass().getComponentType();
        System.out.println("数组类型:"+c.getName());
        int len = Array.getLength(arr);
        System.out.println("数组长度:"+len);
        for (int i = 0;i < len;i++){
            System.out.println(Array.get(arr,i)+"");
        }
        System.out.println();
        System.out.println("修改前的第一个元素"+Array.get(arr,0));
        Array.set(arr,0,3);
        System.out.println("修改后的第一个元素"+Array.get(arr,0));
    }
}
