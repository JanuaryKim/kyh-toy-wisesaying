package kyh.toy.wisesaying.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ALREADY_MEMBER_EXISTS(409,"already member exists"),
    MEMBER_NOT_EXISTS(404,"member not exists"),
    WRONG_COPY_PROPERTIES(412,"wrong copy properties"), //전제 조건 실패

    CARD_NOT_EXISTS(404,"card not exists");


    int errorCode;
    String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
