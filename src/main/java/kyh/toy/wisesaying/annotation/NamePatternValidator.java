package kyh.toy.wisesaying.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NamePatternValidator implements ConstraintValidator<NamePattern, String> { // <적용할 어노테이션, 검증 필드 타입>

    String engPattern = "^[a-zA-Z][a-zA-Z\\s]*$";
    String korPattern = "^[가-힣][가-힣\\s]*$";

    @Override
    public void initialize(NamePattern constraintAnnotation) { //유효성 검사를 위한 초기화 단계
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) { //예외사항 검증 메소드

        return value.matches(engPattern) || value.matches(korPattern);
    }
}
