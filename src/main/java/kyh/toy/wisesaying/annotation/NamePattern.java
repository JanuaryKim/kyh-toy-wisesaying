package kyh.toy.wisesaying.annotation;


import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NamePatternValidator.class)
public @interface NamePattern {
    String message() default "올바르지 않은 이름입니다. (영문은 영문만, 한글은 한글만)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
