package medical_records.medical_records.controller;

import com.example.medicalrecords.config.SecurityConfig;
import com.example.medicalrecords.data.entity.Patient;
import com.example.medicalrecords.dto.PatientDTO;
import com.example.medicalrecords.service.PatientService;
import com.example.medicalrecords.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientApiController.class)
@Import(SecurityConfig.class)
class PatientApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private MapperUtil mapperUtil;

    @Test
    void getAllPatients_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllPatients_WithValidJwt() throws Exception {
        mockMvc.perform(get("/api/patients")
                .with(jwt().authorities(List.of(new SimpleGrantedAuthority("doctor"))))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getAllPatients_ReturnsPatients() throws Exception {
        Patient patient1 = new Patient();
        patient1.setId(1L);
        Patient patient2 = new Patient();
        patient2.setId(2L);
        List<Patient> patients = Arrays.asList(patient1, patient2);

        PatientDTO patientDTO1 = new PatientDTO();
        patientDTO1.setId(1L);
        PatientDTO patientDTO2 = new PatientDTO();
        patientDTO2.setId(2L);

        given(patientService.getAllPatients()).willReturn(patients);
        given(mapperUtil.mapList(patients, PatientDTO.class)).willReturn(Arrays.asList(patientDTO1, patientDTO2));

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getPatientById_ReturnsPatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);

        given(patientService.getPatientById(1L)).willReturn(patient);
        given(mapperUtil.getModelMapper().map(patient, PatientDTO.class)).willReturn(patientDTO);

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getPatientsByGpDoctor_ReturnsPatients() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);

        given(patientService.getPatientsByGpDoctor(1L)).willReturn(Arrays.asList(patient));
        given(mapperUtil.mapList(Arrays.asList(patient), PatientDTO.class)).willReturn(Arrays.asList(patientDTO));

        mockMvc.perform(get("/api/patients/gp/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }
}