package kyh.toy.wisesaying.card.service;


import kyh.toy.wisesaying.card.entity.Card;
import kyh.toy.wisesaying.card.repository.CardRepository;
import kyh.toy.wisesaying.exception.BusinessException;
import kyh.toy.wisesaying.exception.ErrorCode;
import kyh.toy.wisesaying.member.entity.Member;
import kyh.toy.wisesaying.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CardService {

    private final CardRepository repository;
    private final CustomBeanUtils<Card> beanUtils;

    /** Controller와 직접 관련 **/
    public Card createCard(Card card) {

        Card savedCard = repository.save(card);

        return savedCard;
    }

    public Card updateCard(Card card) {

        Card findCard = verifyExistCard(card.getCardId());
        Card updatingCard = beanUtils.copyNotNullProperties(findCard, card);
        Card savedCard = repository.save(updatingCard);
        return savedCard;
    }

    public Card findCard(Long cardId) {

        Card findCard = verifyExistCard(cardId);

        return findCard;
    }

    public Page<Card> findCards(int size, int page) {

        Page<Card> cardPage = repository.findAll(PageRequest.of(page, size, Sort.by("cardId")));

        return cardPage;
    }

    /** ~ Controller와 직접 관련 **/

    private Card verifyExistCard(Long cardId) {

        return repository.findById(cardId).orElseThrow(()-> {throw new BusinessException(ErrorCode.CARD_NOT_EXISTS);});
    }
}
