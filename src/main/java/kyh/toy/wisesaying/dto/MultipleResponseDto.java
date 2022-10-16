package kyh.toy.wisesaying.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class MultipleResponseDto <T>{

    private final List<T> data;
    private final PageInfo pageInfo;

}
