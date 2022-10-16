package kyh.toy.wisesaying.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SingleResponseDto <T>{

    private final T data;


}
