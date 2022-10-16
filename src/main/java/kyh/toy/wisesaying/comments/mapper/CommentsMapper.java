package kyh.toy.wisesaying.comments.mapper;

import kyh.toy.wisesaying.comments.dto.CommentsDto;
import kyh.toy.wisesaying.comments.entity.Comments;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    default List<CommentsDto.Response> CommentsListToCommentsResponseDtoList(List<Comments> list){

        List<CommentsDto.Response> dtoList = list.stream().map(c -> new CommentsDto.Response
                (c.getCommentsId(), c.getMember().getMemberId(), c.getCard().getCardId(), c.getContent()))
                .collect(Collectors.toList());

        return dtoList;

    }
}
