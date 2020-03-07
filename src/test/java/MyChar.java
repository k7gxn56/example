import org.omg.CORBA.TIMEOUT;
import org.omg.CORBA.portable.ValueOutputStream;

import java.sql.Time;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;

public class MyChar {

    public MyChar(){

    }

    char ch = 'a';

    char uniChar = '\u039A';

    char[] charArray = {'a','b','c','d','e'};

        Character ch2 = 'a';

    public char test(Character c){
        return c;
    }

    public static void main(String[] args){
        MyChar myChar = new MyChar();
        /*
        System.out.println(myChar.ch);
        System.out.println(myChar.uniChar);
        System.out.println(myChar.charArray);
        System.out.println(Character.isLetter(myChar.ch2));
        System.out.println(Character.toUpperCase(myChar.ch));
        System.out.println(myChar.test('x'));
        TimeZone china = TimeZone.getTimeZone("GMT+08:00");
        TimeZone chongqing = TimeZone.getTimeZone("Asia/Chongqing");
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids){
            System.out.println(id);
        }
        System.out.println(china);
        System.out.println(chongqing);
        System.out.println(new Date(System.currentTimeMillis()));

        A();
        testOtherApis();
        printAllTimeZones();

        System.setProperty("user.timezone","Asia/Nanchang");
        System.out.println(System.getProperty("user.timezone"));
        Date d = new Date();
        System.out.printf("%tF%n", d);
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.printf("%tT%n",d);*/
    }

    public static void A(){
        TimeZone tz;
        tz = TimeZone.getDefault();
        printDateIn(tz);
        tz = TimeZone.getTimeZone("GMT+10:00");
        printDateIn(tz);
        tz = TimeZone.getTimeZone("Asia/Chongqing");
        printDateIn(tz);

    }

    public static void printDateIn(TimeZone tz){
        Date date = new Date(118,8,19,14,22,30);
        DateFormat df = DateFormat.getDateInstance();
        df.setTimeZone(tz);
        String str = df.format(date);
        System.out.println(tz.getID()+":"+str);
    }

    public static void testOtherApis(){
        TimeZone tz = TimeZone.getDefault();
        String id = tz.getID();
        String name = tz.getDisplayName();
        int offset = tz.getRawOffset();
        int gmt = offset/(3600*1000);

        System.out.printf("id=%s,name=%s,offset=%s(ms),gmt=%s\n",id,name,offset,gmt);
    }

    public static void printAllTimeZones(){
        String[] ids = TimeZone.getAvailableIDs();
        for (String id :ids){
            System.out.println(id);
        }
    }
}
