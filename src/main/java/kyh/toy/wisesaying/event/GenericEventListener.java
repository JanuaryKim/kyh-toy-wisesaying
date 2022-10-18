package kyh.toy.wisesaying.event;

import kyh.toy.wisesaying.mail.EmailService;
import kyh.toy.wisesaying.mail.MailDto;
import kyh.toy.wisesaying.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class GenericEventListener {

    private final EmailService emailService;

    @Async //해당 리스너를 비동기로 처리함
    @EventListener(classes = CreateMemberEvent.class) //이벤트 리스너임을 알리는 어노테이션, PostEvent 의 핸들러 메소드
    public void handleEvent(CreateMemberEvent createMemberEvent) {

        System.out.println(createMemberEvent.getMessage());

        Member member = createMemberEvent.getMember();

        String title = member.getName() + " 님 가입을 축하합니다!";
        String content = "앞으로 많은 이용 부탁드립니다";

        MailDto mailDto = new MailDto();
        mailDto.setAddress(member.getEmail());
        mailDto.setTitle(title);
        mailDto.setContent(content);

        emailService.sendSimpleMessage(mailDto);
    }

    @Async //해당 리스너를 비동기로 처리함
    @EventListener(classes = DeleteMemberEvent.class) //이벤트 리스너임을 알리는 어노테이션, PatchEvent 의 핸들러 메소드
    public void handleEvent(DeleteMemberEvent deleteMemberEvent) {

        System.out.println(deleteMemberEvent.getMessage());

        Member member = deleteMemberEvent.getMember();

        String title = member.getName() + " 님 그동안 사용해주셔서 감사합니다!";
        String content = "감사합니다";

        MailDto mailDto = new MailDto();
        mailDto.setAddress(member.getEmail());
        mailDto.setTitle(title);
        mailDto.setContent(content);

        emailService.sendSimpleMessage(mailDto);

        System.out.println(deleteMemberEvent.getMessage());
    }
}


