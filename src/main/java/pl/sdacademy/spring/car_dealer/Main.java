package pl.sdacademy.spring.car_dealer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Application application = context.getBean("application", Application.class);
        application.start();

        context.close();


    }
}
