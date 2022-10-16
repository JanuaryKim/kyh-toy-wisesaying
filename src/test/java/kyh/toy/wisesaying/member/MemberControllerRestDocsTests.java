package kyh.toy.wisesaying.member;


import com.google.gson.Gson;
import kyh.toy.wisesaying.ControllerTestHelper;
import kyh.toy.wisesaying.member.controller.MemberController;
import kyh.toy.wisesaying.member.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static kyh.toy.wisesaying.utils.apiDocumentUtils.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) //RestDocumentationExtension.class - 출력 디렉토리 자동 구성
@WebMvcTest(MemberController.class) // api 계층의 테스트에 필요한 빈들만 등록, 통합테스트 보다 가벼움
@MockBean(JpaMetamodelMappingContext.class) //Jpa 관련 빈들을 등록, 해당 애노테이션 없으면 현재 @EnableJpaAuditing 때문에 에러남. @EnableJpaAuditing이 @SpringBootApplication 와 같이 쓰여서 테스트시에도 활성화되는데, 그러면서 Jpa 관련 빈들을 찾기 때문.
@AutoConfigureRestDocs

public class MemberControllerRestDocsTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Gson gson;

    String baseURI = "/api_v1/members";


    @Test
    void postMemberTest() throws Exception {


        //given
        MemberDto.Post post = new MemberDto.Post();
        post.setEmail("january@gmail.com");
        post.setName("멋쟁이");
        post.setPhone("010-2222-3333");
        post.setPassword("1234");


        String content = gson.toJson(post);


        //when
        // 단순히 request 보내는 부분
        ResultActions actions = mockMvc.perform(post(baseURI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
        //~ 단순히 request 보내는 부분


        //then
        MvcResult mvcResult = actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
                .andDo(document("post-member",  //실질적으로 문서를만드는 구문, document()
                        //request 관련 문서를 만들기전, response 과련 무서를 만들기 전 전처리기 추가
                        getRequestPreProcessor(), getResponsePreProcessor(),
                        requestFields( //문서에 입력되는 실제 필드 내용 정의
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀 번호")
                                )
                        ),
                        responseFields( //문서에 입력되는 실제 필드 내용 정의
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data.phone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                                        fieldWithPath("data.cardList").type(JsonFieldType.ARRAY).description("카드 리스트").ignored()
                                )
                        ))).andReturn();


        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void patchMemberTest() {

    }




}
