package kyh.toy.wisesaying.comments.controller;


import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import kyh.toy.wisesaying.comments.dto.CommentsDto;
import kyh.toy.wisesaying.dto.MultipleResponseDto;
import kyh.toy.wisesaying.dto.PageInfo;
import kyh.toy.wisesaying.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


@Validated
@RequestMapping("/api_v1/comments")
@RestController
public class CommentsController {


    @PostMapping
    public ResponseEntity postComments(CommentsDto.Post dto) {

        CommentsDto.Response stub = new CommentsDto.Response();

        stub.setCommentsId(1L);
        stub.setMemberId(dto.getMemberId());
        stub.setCardId(dto.getCardId());
        stub.setContent(dto.getContent());

        return new ResponseEntity(new SingleResponseDto(stub), HttpStatus.CREATED);
    }

    @GetMapping("/{comments-id}")
    public ResponseEntity getComments(@PathVariable("comments-id") @Positive Long commentsId) {

        CommentsDto.Response stub = new CommentsDto.Response();

        stub.setCommentsId(commentsId);
        stub.setMemberId(1L);
        stub.setCardId(1L);
        stub.setContent("스텁");

        return new ResponseEntity(new SingleResponseDto(stub), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity patchComments(CommentsDto.Patch dto) {

        CommentsDto.Response stub = new CommentsDto.Response();

        stub.setCommentsId(dto.getCommentsId());
        stub.setMemberId(1L);
        stub.setCardId(dto.getCardId());
        stub.setContent(dto.getContent());

        return new ResponseEntity(new SingleResponseDto(stub), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getComments() {

        CommentsDto.Response stub1 = new CommentsDto.Response();

        stub1.setCommentsId(1L);
        stub1.setMemberId(1L);
        stub1.setCardId(1L);
        stub1.setContent("스텁1");

        CommentsDto.Response stub2 = new CommentsDto.Response();

        stub2.setCommentsId(2L);
        stub2.setMemberId(1L);
        stub2.setCardId(2L);
        stub2.setContent("스텁2");


        PageInfo pageInfo = new PageInfo(1,10,2,1);

        return new ResponseEntity(new MultipleResponseDto(List.of(stub1,stub2), pageInfo), HttpStatus.CREATED);
    }

}
