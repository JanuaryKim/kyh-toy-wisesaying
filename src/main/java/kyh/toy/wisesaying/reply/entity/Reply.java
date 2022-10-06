package kyh.toy.wisesaying.reply.entity;

import kyh.toy.wisesaying.card.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Reply  {

    @Id
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "CARD_ID", nullable = false)
    private Card card;

    @Column(nullable = false, length = 200)
    private String content;
}
