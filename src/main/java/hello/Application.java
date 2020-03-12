package hello;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.applet.AppletContext;

@Configuration
@ComponentScan
public class Application {
    @Bean
    MessageService mockMessageService(){
        return new MessageService() {
            public String getMessage() {
                return "Hello World";
            }
            public String getName(){
                return "ShenXiang";
            }
        };
    }

    public static void main(String[] args){
        t2();
    }

    public static void t2(){
        int i = 1;
        i = i++;
        int j = i++;
        int k = i+ ++i * i++;
        System.out.println("i = "+i+"\nj="+j+"\nk="+k);
    }


    public static void t1(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        printer.printMessage();
        printer.printName();
        try{
            XmlBeanFactory factory = new XmlBeanFactory(new FileSystemResource("bean.xml"));
            PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
            cfg.setLocation(new FileSystemResource("jdbc.properties"));
            cfg.postProcessBeanFactory(factory);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
