package kyh.toy.wisesaying.card.mapper;

import kyh.toy.wisesaying.card.dto.CardDto;
import kyh.toy.wisesaying.card.entity.Card;
import kyh.toy.wisesaying.comments.dto.CommentsDto;
import kyh.toy.wisesaying.comments.entity.Comments;
import kyh.toy.wisesaying.comments.mapper.CommentsMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CardMapper {



    Card cardDtoPostToCard(CardDto.Post dto);
    Card cardDtoPatchToCard(CardDto.Patch dto);
    CardDto.Response cardToCardDtoResponse(Card card);



//    default List<CardDto.Response> CardListToCardResponseDtoList(List<Card> list, CommentsMapper commentsMapper){
//
//
//        List<CardDto.Response> dtoList = list.stream().map(c -> new CardDto.Response
//                (c.getCardId(), c.getPhotoUrl(), c.getContent(), c.getLikeCtn(), c.getMember().getMemberId(),
//                        commentsMapper.CommentsListToCommentsResponseDtoList(c.getCommentsList()))).collect(Collectors.toList());
//
//        return dtoList;
//
//
//    }
}
