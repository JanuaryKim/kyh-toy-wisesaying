package kyh.toy.wisesaying.member.mapper;

import kyh.toy.wisesaying.card.dto.CardDto;
import kyh.toy.wisesaying.card.entity.Card;
import kyh.toy.wisesaying.card.mapper.CardMapper;
import kyh.toy.wisesaying.comments.mapper.CommentsMapper;
import kyh.toy.wisesaying.member.dto.MemberDto;
import kyh.toy.wisesaying.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberDtoPostToMember(MemberDto.Post dto);

    Member memberDtoPatchToMember(MemberDto.Patch dto);

    MemberDto.Response memberToMemberDtoResponse(Member member);


}
