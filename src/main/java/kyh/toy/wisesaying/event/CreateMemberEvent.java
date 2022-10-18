package kyh.toy.wisesaying.event;

import kyh.toy.wisesaying.member.entity.Member;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class CreateMemberEvent extends ApplicationEvent { //ApplicationEvent 를 상속받아서 이벤트 객체로써 만듦. 이벤트 처리에 적용되도록 함

    private String message;
    private Member member;

    private CreateMemberEvent(Object source , String message, Member createdMember) {
        super(source);
        this.message = message;
        this.member = createdMember;
    }

    public static CreateMemberEvent of(Object source , String message, Member createdMember){
        return new CreateMemberEvent(source, message, createdMember);
    }
}
