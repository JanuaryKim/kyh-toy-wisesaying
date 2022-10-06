package kyh.toy.wisesaying.card.entity;


import kyh.toy.wisesaying.member.entity.Member;

import kyh.toy.wisesaying.reply.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card {

    @Id
    private Long cardId;

    @Column(nullable = false)
    private String photoUrl;

    @Column(nullable = false)
    private String content;

    private Long likeCtn;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Reply> replyList;


}
