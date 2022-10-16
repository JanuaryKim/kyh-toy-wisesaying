package kyh.toy.wisesaying.member.entity;

import kyh.toy.wisesaying.card.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Member  {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberId;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    List<Card> cardList = new ArrayList<>();

}
