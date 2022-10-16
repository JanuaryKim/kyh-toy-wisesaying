package kyh.toy.wisesaying;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public interface ControllerTestHelper {

    MediaType mediaType = MediaType.APPLICATION_JSON;

    default MockHttpServletRequestBuilder postRequestBody(String url, String jsonContent){

        return MockMvcRequestBuilders.post(url).accept(mediaType).contentType(mediaType).content(jsonContent);
    }

    default MockHttpServletRequestBuilder patchRequestBody(String url, String jsonContent){

        return MockMvcRequestBuilders.patch(url).accept(mediaType).contentType(mediaType).content(jsonContent);
    }

    default MockHttpServletRequestBuilder deleteRequestBody(String url, String jsonContent){

        return MockMvcRequestBuilders.delete(url).accept(mediaType).contentType(mediaType).content(jsonContent);
    }

    default MockHttpServletRequestBuilder getRequestBody(String url, String jsonContent){

        return MockMvcRequestBuilders.get(url).accept(mediaType).contentType(mediaType).content(jsonContent);
    }
}
