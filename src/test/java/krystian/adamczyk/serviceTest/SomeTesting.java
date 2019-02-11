package krystian.adamczyk.serviceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import krystian.adamczyk.model.User;
import krystian.adamczyk.service.InzynierkaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SomeTesting {

  private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  @Autowired
  private InzynierkaService service;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnSomeData() throws Exception {
    this.mockMvc.perform(get("/boardMessages")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Sprzedam Opla")));

    this.mockMvc.perform(get("/getrooms")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Pokoj spokojnej nauki")));

    this.mockMvc.perform(get("/getlaundry")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Pralnia")));
  }

  @Test
  public void postUser() throws Exception {
    User user = new User();
    user.setLivingInRoomNumber(1234);
    user.setFirstName("Tester");
    user.setLastName("Testowy");

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(user);

    this.mockMvc.perform(post("/user/").contentType(APPLICATION_JSON_UTF8)
        .content(requestJson)).andDo(print()).andExpect(status().isCreated());

    assertEquals(user.getFirstName(), service.findFirstUserByFirstNameAndLastName(user.getFirstName(), user.getLastName()).getFirstName());

  }
}