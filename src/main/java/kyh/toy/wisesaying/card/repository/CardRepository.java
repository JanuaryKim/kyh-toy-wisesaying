package kyh.toy.wisesaying.card.repository;

import kyh.toy.wisesaying.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardRepository extends JpaRepository<Card,Long> {

}
