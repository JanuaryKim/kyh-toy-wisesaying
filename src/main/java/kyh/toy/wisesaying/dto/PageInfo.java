package kyh.toy.wisesaying.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@RequiredArgsConstructor
public class PageInfo {

    private final int page;

    private final int size;

    private final long totalElements;

    private final int totalPages;


}
