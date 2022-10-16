package kyh.toy.wisesaying.exception;

public class BusinessException extends RuntimeException{

    ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
