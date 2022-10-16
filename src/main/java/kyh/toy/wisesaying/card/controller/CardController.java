package kyh.toy.wisesaying.card.controller;


import com.google.gson.Gson;
import kyh.toy.wisesaying.card.dto.CardDto;
import kyh.toy.wisesaying.card.entity.Card;
import kyh.toy.wisesaying.card.mapper.CardMapper;
import kyh.toy.wisesaying.card.service.CardService;
import kyh.toy.wisesaying.comments.dto.CommentsDto;
import kyh.toy.wisesaying.comments.mapper.CommentsMapper;
import kyh.toy.wisesaying.dto.MultipleResponseDto;
import kyh.toy.wisesaying.dto.PageInfo;
import kyh.toy.wisesaying.dto.SingleResponseDto;
import kyh.toy.wisesaying.member.dto.MemberDto;
import kyh.toy.wisesaying.member.entity.Member;
import kyh.toy.wisesaying.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/api_v1/cards")
@RestController
public class CardController {

    private final CardService service;
    private final CardMapper mapper;
    private final Gson gson;
    private final CommentsMapper commentsMapper;

    /** 카드 데이터 관련 **/

    @GetMapping("/{card-id}")
    public ResponseEntity getCard(@Positive @PathVariable("card-id") Long cardId)  {

        Card findCard = service.findCard(cardId);
        CardDto.Response response = mapper.cardToCardDtoResponse(findCard);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity getCards(@Positive @RequestParam("size") Integer size, @Positive @RequestParam("page") Integer page) {

        Page<Card> cardPage = service.findCards(size, page-1);
        PageInfo pageInfo = new PageInfo(cardPage.getPageable().getPageNumber() + 1, cardPage.getSize(),
                (int) cardPage.getTotalElements(), cardPage.getTotalPages());
        List<Card> content = cardPage.getContent();
        List<CardDto.Response> responseList = content.stream().map(card -> mapper.cardToCardDtoResponse(card)).collect(Collectors.toList());
        return new ResponseEntity<>(new MultipleResponseDto(responseList, pageInfo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postCard(@RequestBody @Valid CardDto.Post dto) {

        Card card = mapper.cardDtoPostToCard(dto);
        card.setMember(dto.getMemberId());
        Card createdCard = service.createCard(card);
        CardDto.Response response = mapper.cardToCardDtoResponse(createdCard);
        response.setCommentsDtoList(commentsMapper.CommentsListToCommentsResponseDtoList(createdCard.getCommentsList()));

        response.setMemberId(createdCard.getMember().getMemberId());

        return new ResponseEntity(new SingleResponseDto(response),HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity patchCard(@RequestBody @Valid CardDto.Patch dto) {

        Card card = mapper.cardDtoPatchToCard(dto);
        Card updatedCard = service.updateCard(card);
        CardDto.Response response = mapper.cardToCardDtoResponse(updatedCard);
        return new ResponseEntity(new SingleResponseDto(response),HttpStatus.OK);

    }

    /** **/


    @PostMapping("/images/upload")
    public ResponseEntity uploadCardImage(@RequestParam("card") String card, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        user.setPhotos(fileName);
//
//        User savedUser = repo.save(user);

        gson.toJson(card);
        String uploadDir = "user-photos/" + "user1";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new ResponseEntity(HttpStatus.CREATED);
    }



}
