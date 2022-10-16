package kyh.toy.wisesaying.comments.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentsDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Post {

        private Long memberId;

        private Long cardId;

        private String content;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Patch{

        private Long commentsId;


        private Long cardId;

        private String content;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Response {

        private Long commentsId;

        private Long memberId;

        private Long cardId;

        private String content;

    }
}
