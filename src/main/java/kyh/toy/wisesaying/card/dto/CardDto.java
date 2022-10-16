package kyh.toy.wisesaying.card.dto;

import kyh.toy.wisesaying.comments.dto.CommentsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;
import java.util.List;

public class CardDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Post {

        @Length(min = 1, max = 200)
        private String content;

        @Positive
        private Long memberId;

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Patch{

        @Positive
        private Long cardId;

        @Length(min = 1, max = 200)
        private String content;

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Response{

        private Long cardId;

        private String photoUrl;

        private String content;

        private Long likeCtn;

        private Long memberId;

        List<CommentsDto.Response> commentsDtoList;
    }

}
