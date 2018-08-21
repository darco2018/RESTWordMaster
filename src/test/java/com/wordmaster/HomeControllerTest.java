package com.wordmaster;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wordmaster.webparser.HomeController;
import com.wordmaster.webparser.WebpageParser;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
	
	@Value("${onet.url}")
	private String defaultUrl; // "http://www.onet.pl/"
	@Value("${onet.headlines.tag}")
	private String defaultTag;
	
	//@MockBean
	//private WebpageParser parser;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WebpageParser webpageParser;
	

	@Test
	public void shouldReturnOKStatus_whenCorrectURLAndCorrectTag() throws Exception {
		
		//arrange
		Mockito.when(this.webpageParser.getHTMLElementsList(new URI(defaultUrl), defaultTag))																	
				.thenReturn(new LinkedList<String>());
		
		String slash = "%E2";
		String protocol = "http:" + slash + slash;
		String correctURLandTag = protocol + defaultUrl + "/" + defaultTag;
		
		//act
		RequestBuilder reqBuilder = MockMvcRequestBuilders
				.get("/headlines/" + correctURLandTag)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		//assert
		mockMvc.perform(reqBuilder)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
	}
	
	@Test
	public void shouldUseDefaultUrl_whenQueryWithIncorrectURLAndCorrectTag() throws Exception {
		
		//arrange
		
		Mockito.when(this.webpageParser.getHTMLElementsList(new URI(defaultUrl), defaultTag))																	
		.thenReturn(new LinkedList<String>());
		
		String wrongURl = "wrong";
		String slash = "%E2";
		String protocol = "http:" + slash + slash;
		String incorrectURLandcorrectTag = protocol + wrongURl + "/" + defaultTag;
		
		//act
		RequestBuilder reqBuilder = MockMvcRequestBuilders
				.get("/headlines/" + incorrectURLandcorrectTag)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		//assert
		mockMvc.perform(reqBuilder)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
	}

}
