package br.com.nexaas.challenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.nexaas.challenge.service.FileUploadService;

/**
 * @author emanuel.foliveira
 * @since 03/01/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FileUploadControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private FileUploadService FileUploadService;
	
	   @Test
	    public void loginPage() throws Exception {
		   mvc.perform(get("/login")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }
	   
}
