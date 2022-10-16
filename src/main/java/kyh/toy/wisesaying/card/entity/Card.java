package kyh.toy.wisesaying.card.entity;


import kyh.toy.wisesaying.member.entity.Member;

import kyh.toy.wisesaying.comments.entity.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cardId;

    @Column
    private String photoUrl;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long likeCtn = 0L;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Comments> commentsList = new ArrayList<>();

    public void setMember(Long memberId) {
        Member member = new Member();
        member.setMemberId(memberId);
        this.member = member;
    }

}
