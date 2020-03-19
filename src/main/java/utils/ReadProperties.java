package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static void main(String[] args){
        Properties properties = new Properties();
        try{
            System.out.println(System.getProperty("user.dir"));
            File f = new File("");
            System.out.println(f.getAbsoluteFile());
            System.out.println(f.getCanonicalPath());
            properties.load(new FileInputStream(new File("src\\main\\resources\\jdbc.properties")));
            System.out.println(properties.get("jdbc.url"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
