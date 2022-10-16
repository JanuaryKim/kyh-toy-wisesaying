package kyh.toy.wisesaying.utils;


import kyh.toy.wisesaying.exception.BusinessException;
import kyh.toy.wisesaying.exception.ErrorCode;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CustomBeanUtils <T> {

    public T copyNotNullProperties(T source, T destination) {

        if (source == null || destination == null) {
            throw new BusinessException(ErrorCode.WRONG_COPY_PROPERTIES);
        }

        //객체에 final 적용은 할당만 할 수 없는것임. 내부 프로퍼티를 새로 설정하는건 상관없음
        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper dest = new BeanWrapperImpl(destination);

        for (final Field property : destination.getClass().getDeclaredFields()) {
            String name = property.getName();
            Object destPropertyValue = dest.getPropertyValue(name);
            if(destPropertyValue != null && !(destPropertyValue instanceof Collection)){
                src.setPropertyValue(property.getName(),destPropertyValue);
            }
        }

        return source;
    }

}
