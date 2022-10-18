package kyh.toy.wisesaying.event;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class CustomEventPublisher{

    private final ApplicationEventPublisher applicationEventPublisher; //실질적인 퍼블리셔 객체

    public void publish(ApplicationEvent applicationEvent) { //이벤트 객체를 인터페이스로 받아서 다형성 적용
        System.out.println("이벤트 발생");
        applicationEventPublisher.publishEvent(applicationEvent); //이벤트 발행
    }

}
