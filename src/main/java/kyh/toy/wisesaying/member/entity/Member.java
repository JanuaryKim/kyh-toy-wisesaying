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
public class Member {

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

    //해당 엔티티의 Id를 프라이머리키와 동시에 포린키로 갖는 테이블을 생성해준다.
    // 해당 테이블의 로우는 List의 요소 갯수만큼 Insert 된다. 즉 ID가 중복되는 테이블의 구조를 가진다.
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roles = new ArrayList<>();


}
