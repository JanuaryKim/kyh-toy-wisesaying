package kyh.toy.wisesaying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync //비동기 처리 사용시 비동기를 활성화 시킴
@EnableJpaAuditing
@SpringBootApplication
public class WiseSayingApplication {
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {

        applicationContext = SpringApplication.run(WiseSayingApplication.class, args);

    }
}
