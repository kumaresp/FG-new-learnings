package com.fg.integrationtesting;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fg.UserServiceApplication;
import com.fg.constants.UserConstants;
import com.fg.model.InlineObject2;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
classes= {
		UserServiceApplication.class,
	//	SecurityConfiguration.class
}
)
@AutoConfigureMockMvc
public class TokensControllerIntegrationTest {
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	@Qualifier("idpServerRestTemplate")
	protected RestTemplate restTemplate;
	
	protected MockRestServiceServer mockIdpServer;
	
	HttpHeaders headers=new HttpHeaders();
	
	@BeforeEach
	public void setUp() {
		mockIdpServer=createServer(restTemplate);
	}

	void validateAccessTokenHappyPath() throws Exception{
		
		String access_token="7fljlsjfljsdlf442lj143";
		InlineObject2 inlineObject2=new InlineObject2();
		inlineObject2.setAccessToken(access_token);
		
		headers.set(UserConstants.X_FIS_IDP_TRANSACTION_ID,"324334fdfd43242");
		
		
		//Given
		
		mockIdpServer
		      .expect(ExpectedCount.once(),
		    		  requestTo("https://fake_host/idp/poc/rest/1.0/idptoken/validatetoken"))
		       .andExpect(method(POST))
		       .andRespond(withStatus(HttpStatus.OK)
		       .contentType(MediaType.APPLICATION_JSON)
		       .body(new ObjectMapper().writeValueAsString(inlineObject2))
		       .headers(headers));
		
		//When
		
MvcResult mvcResult=mockMvc.perform(post("/USERSERVICE/v1/issueToken/validatetoken?access_token=7fljlsjfljsdlf442lj143")
				           .servletPath("/USERSERVICE/v1/issueToken/validatetoken")
	                       .param("access_token", access_token)
	                       .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                       .headers(buildHttpHeaders())	
		                           ).andExpect(status().isOk()).andReturn();
		
		
          MockHttpServletResponse response = mvcResult.getResponse();
          assertNotNull(response.getContentAsString());
		
		
		
		
		
		
		
	}
	private HttpHeaders buildHttpHeaders() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		httpHeaders.set(UserConstants.X_SUN_GARD_IDP_API_KEY, idpConfigs.getIdpAPIkey());

		return httpHeaders;
	}

	
	
}
