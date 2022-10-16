package kyh.toy.wisesaying.member.dto;


import kyh.toy.wisesaying.annotation.NamePattern;
import kyh.toy.wisesaying.card.dto.CardDto;
import kyh.toy.wisesaying.card.entity.Card;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;



public class MemberDto {


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Post{

//        @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]*$")
//        @Pattern(regexp = "^[가-힣][가-힣\\s]*$")

        @NamePattern
        private String name;

        @Email
        private String email;


        @Pattern(regexp = "^010-[0-9]{3,4}-[0-9]{4}$") //010은 무조건, 중간 숫자는 3 or 4, 끝에는 무조건 4개
        private String phone;

        //추후 추가
        private String password;

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Patch{

        @NamePattern
        private String name;

        @Pattern(regexp = "^010-[0-9]{3,4}-[0-9]{4}$") //010은 무조건, 중간 숫자는 3 or 4, 끝에는 무조건 4개
        private String phone;

        private String password;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Response{

        private Long memberId;

        private String name;

        private String email;

        private String phone;

        private List<CardDto.Response> cardList;
    }

}
