package kyh.toy.wisesaying.comments.entity;

import kyh.toy.wisesaying.card.entity.Card;
import kyh.toy.wisesaying.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comments {

    @Id
    private Long commentsId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member; //댓글 작성자

    @ManyToOne
    @JoinColumn(name = "CARD_ID", nullable = false)
    private Card card;

    @Column(nullable = false, length = 200)
    private String content;
}
