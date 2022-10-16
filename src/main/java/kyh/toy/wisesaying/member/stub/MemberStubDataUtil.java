package kyh.toy.wisesaying.member.stub;

import kyh.toy.wisesaying.member.dto.MemberDto;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberStubDataUtil {

    public static MemberDto.Response getMemberDtoResponse(MemberDto.Post dto) {

        MemberDto.Response response = new MemberDto.Response();
        response.setMemberId(1L);
        response.setEmail(dto.getEmail());
        response.setName(dto.getName());
        response.setPhone(dto.getPhone());
        response.setPhone(dto.getPhone());
        response.setCardList(new ArrayList<>());

        return response;
    }

    public static MemberDto.Response getMemberDtoResponse(MemberDto.Patch dto) {

        MemberDto.Response response = new MemberDto.Response();


        response.setMemberId(1L);
        response.setName(dto.getName());
        response.setPhone(dto.getPhone());
        response.setPhone(dto.getPhone());
        response.setCardList(new ArrayList<>());


        return response;

    }

}
