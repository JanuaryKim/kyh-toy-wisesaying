package kyh.toy.wisesaying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WiseSayingApplication {
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {





        applicationContext = SpringApplication.run(WiseSayingApplication.class, args);
//        displayAllBeans();
    }


//    public static void displayAllBeans() {
//        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
//        for(String beanName : allBeanNames) {
//            System.out.println(beanName);
//        }
//    }
}
