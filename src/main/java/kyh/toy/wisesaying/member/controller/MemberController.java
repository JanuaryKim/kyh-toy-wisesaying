package kyh.toy.wisesaying.member.controller;


import kyh.toy.wisesaying.dto.MultipleResponseDto;
import kyh.toy.wisesaying.dto.PageInfo;
import kyh.toy.wisesaying.dto.SingleResponseDto;
import kyh.toy.wisesaying.mail.EmailService;
import kyh.toy.wisesaying.mail.MailDto;
import kyh.toy.wisesaying.member.dto.MemberDto;
import kyh.toy.wisesaying.member.entity.Member;
import kyh.toy.wisesaying.member.mapper.MemberMapper;
import kyh.toy.wisesaying.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Validated
@RequestMapping("/api_v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    private final MemberMapper memberMapper;


    @PostMapping
    public ResponseEntity postMembers(@Valid @RequestBody MemberDto.Post dto) {

        Member member = memberMapper.memberDtoPostToMember(dto);
        Member createdMember = memberService.createMember(member);
        MemberDto.Response responseDto = memberMapper.memberToMemberDtoResponse(createdMember);

        return new ResponseEntity(new SingleResponseDto<>(responseDto), HttpStatus.CREATED);
    }


    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@Positive @PathVariable("member-id") Long memberId) {

        Member member = memberService.findMember(memberId);
        MemberDto.Response responseDto = memberMapper.memberToMemberDtoResponse(member);

        return new ResponseEntity(new SingleResponseDto<>(responseDto), HttpStatus.OK);
    }



    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMembers(@Positive @PathVariable("member-id") Long memberId) {

        memberService.eraseMember(memberId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMembers(@Positive @PathVariable("member-id") Long memberId, @RequestBody @Valid MemberDto.Patch dto) {

        Member member = memberMapper.memberDtoPatchToMember(dto);
        member.setMemberId(memberId);
        Member updatedMember = memberService.updateMember(member);
        MemberDto.Response responseDto = memberMapper.memberToMemberDtoResponse(updatedMember);
        return new ResponseEntity(new SingleResponseDto<>(responseDto), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam("size") int size,
                                     @Positive @RequestParam("page") int page) {

        Page<Member> memberPage = memberService.findMembers(size, page-1);
        PageInfo pageInfo = new PageInfo(memberPage.getPageable().getPageNumber() + 1, memberPage.getSize(),
                (int) memberPage.getTotalElements(), memberPage.getTotalPages());
        List<Member> content = memberPage.getContent();
        List<MemberDto.Response> responseList = content.stream().map(member -> memberMapper.memberToMemberDtoResponse(member)).collect(Collectors.toList());
        return new ResponseEntity<>(new MultipleResponseDto(responseList, pageInfo), HttpStatus.OK);
    }
}
