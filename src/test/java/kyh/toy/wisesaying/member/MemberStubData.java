package kyh.toy.wisesaying.member;

import kyh.toy.wisesaying.member.dto.MemberDto;
import kyh.toy.wisesaying.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberStubData {




    private static Map<HttpMethod, Object> memberStub = new HashMap<>();

    static{

        MemberDto.Post post = new MemberDto.Post("부여융", "booyeo@baekje.com", "010-2222-3333", "1234");
        MemberDto.Patch patch = new MemberDto.Patch("강감찬","010-7979-9898","2234");

        memberStub.put(HttpMethod.POST, post);
        memberStub.put(HttpMethod.PATCH, patch);

    }


    public static MemberDto.Post getPostDto() {

        MemberDto.Post postDto = (MemberDto.Post)memberStub.get(HttpMethod.POST);

        return postDto;
    }


    public static MemberDto.Patch getPatchDto() {

        MemberDto.Patch patchDto = (MemberDto.Patch)memberStub.get(HttpMethod.PATCH);

        return patchDto;
    }

    public static Page<Member> getMembers(int count, int size, int page) {

        List<Member> contents = new ArrayList<>();
        for (Long i = 1L; i <= count; i++) {

            Member member = new Member();
            member.setMemberId(i);
            member.setEmail("member"+ i + "@naver.com");
            contents.add(member);
        }

        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<Member> memberPage = new PageImpl(contents,pageRequest,count);

        return memberPage;

    }


}
