package medical_records.medical_records.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DoctorApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void getAllDoctors_Unauthorized() throws Exception {
        mvc.perform(get("/api/doctors"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getAllDoctors_Authorized() throws Exception {
        mvc.perform(get("/api/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"patient"})
    void getAllDoctors_AuthorizedForPatients() throws Exception {
        mvc.perform(get("/api/doctors"))
                .andExpect(status().isOk());
    }
}