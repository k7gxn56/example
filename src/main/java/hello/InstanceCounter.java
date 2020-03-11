package hello;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import pojo.Company;
import pojo.Employee;

public class InstanceCounter {

    private static int num = 0;

    protected static int getCounter(){
        return InstanceCounter.num;
    }

    private static void addCounter(){
        InstanceCounter.num++;
    }

    public InstanceCounter(){
        InstanceCounter.addCounter();
    }

    public static void main(String[] args){
/*
        System.out.println(InstanceCounter.getCounter());
        for (int i = 0;i < 500;i++){
            new InstanceCounter();
        }
        System.out.println(InstanceCounter.getCounter());

        MyRunnable myRunnable = new MyRunnable();

        myRunnable.run();

        myRunnable.stop();
        */
        Company c = new Company();
        BeanWrapper bwComp = new BeanWrapperImpl(c);
        bwComp.setPropertyValue("name","驴妈妈旅游");
        PropertyValue v = new PropertyValue("name","阿里巴巴");
        bwComp.setPropertyValue(v);
        Employee jim = new Employee();
        jim.setSalary(20000);
        BeanWrapper bwJim = new BeanWrapperImpl(jim);

        bwJim.setPropertyValue("name","百度");
        bwComp.setPropertyValue("managingDirector",jim);

        Float salary = (Float) bwComp.getPropertyValue("managingDirector,salary");

        System.out.println(salary);

    }
}
