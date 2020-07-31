package sb2.test.converter.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigurationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void makeAuthorization_withCorrectUsernameAndPassword_expectedAuthenticated() throws Exception{
        mvc.perform(formLogin().user("user").password("user")).andExpect(authenticated());
    }

    @Test
    public void makeAuthorization_withIncorrectUsername_expectedUnauthenticated() throws Exception{
        mvc.perform(formLogin().user("WrongUser").password("11111111")).andExpect(unauthenticated());
    }

    @Test
    public void makeAuthorization_withCorrectUsernameAndIncorrectPassword_expectedUnauthenticated() throws Exception{
        mvc.perform(formLogin().user("user").password("00000001")).andExpect(unauthenticated());
    }
}
