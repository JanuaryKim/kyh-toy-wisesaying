package kyh.toy.wisesaying.member;


import com.google.gson.Gson;

import kyh.toy.wisesaying.dto.PageInfo;
import kyh.toy.wisesaying.member.controller.MemberController;
import kyh.toy.wisesaying.member.dto.MemberDto;
import kyh.toy.wisesaying.member.entity.Member;
import kyh.toy.wisesaying.member.mapper.MemberMapper;
import kyh.toy.wisesaying.member.service.MemberService;

import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.nio.charset.StandardCharsets;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Transactional
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest({MemberMapper.class,MemberController.class})
public class MemberControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberMapper memberMapper;

    @MockBean
    MemberService memberService;


    @Autowired
    Gson gson;

    String baseURI = "/api_v1/members";

    MediaType mediaType = MediaType.APPLICATION_JSON;





    @Test
    void postMemberTest() throws Exception {

        //given
        MemberDto.Post postDto = MemberStubData.getPostDto();

        Member member = memberMapper.memberDtoPostToMember(postDto);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        String content = gson.toJson(postDto);


        //when
        ResultActions actions = mockMvc.perform(post(baseURI).contentType(mediaType).accept(mediaType).content(content));


        //then
        MvcResult mvcResult = actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value(postDto.getName()))
                .andExpect(jsonPath("$.data.email").value(postDto.getEmail()))
                .andExpect(jsonPath("$.data.phone").value(postDto.getPhone())).andReturn();


        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void patchMemberTest() throws Exception {

        //given
        MemberDto.Patch patchDto = MemberStubData.getPatchDto();
        Member member = memberMapper.memberDtoPatchToMember(patchDto);
        member.setMemberId(1L);

        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(member);

        String content = gson.toJson(patchDto);


        //when
        ResultActions actions = mockMvc.perform(
                patch(baseURI+"/{member-id}",String.valueOf(1))
                        .contentType(mediaType).accept(mediaType).content(content));


        //then
        MvcResult mvcResult = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(patchDto.getName()))
                .andExpect(jsonPath("$.data.phone").value(patchDto.getPhone())).andReturn();


        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void deleteMemberTest() throws Exception {

        //given


        doNothing().when(memberService).eraseMember(1L);


        //when
        ResultActions actions = mockMvc.perform(
                delete(baseURI+"/{member-id}",String.valueOf(1))
                .accept(mediaType));


        //then
        MvcResult mvcResult = actions
                .andExpect(status().isNoContent()).andReturn();


        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void getMemberTest() throws Exception {

        //given
        Long memberId = 1L;
        Member member = new Member();
        member.setMemberId(memberId);



        given(memberService.findMember(Mockito.anyLong())).willReturn(member);


        //when
        ResultActions actions = mockMvc.perform(
                get(baseURI + "/{member-id}", String.valueOf(memberId))
                .accept(mediaType));

        //then
        MvcResult mvcResult = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(memberId)).andReturn();


        System.out.println(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));

    }

    @Test
    void getMembers() throws Exception {

        //given
        int page = 1;
        int size = 10;

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("size", String.valueOf(size));
        params.add("page", String.valueOf(page));

        //findMembers
        Page<Member> memberPage = MemberStubData.getMembers(15, size, page);
        given(memberService.findMembers(Mockito.anyInt(),Mockito.anyInt())).willReturn(memberPage);

        //when
        ResultActions actions = mockMvc.perform(
                get(baseURI)
                        .params(params)
                        .accept(mediaType));

        //then
        MvcResult result = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].email").value("member1@naver.com"))
                .andReturn();


        System.out.println(result.getResponse().getContentAsString());
    }

}
