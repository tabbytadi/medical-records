package medical_records.medical_records.controller;

import com.example.medicalrecords.config.SecurityConfig;
import com.example.medicalrecords.data.entity.Doctor;
import com.example.medicalrecords.dto.DoctorDTO;
import com.example.medicalrecords.service.DoctorService;
import com.example.medicalrecords.util.MapperUtil;
import com.example.medicalrecords.web.api.DoctorApiController;
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

@WebMvcTest(DoctorApiController.class)
@Import(SecurityConfig.class)
class DoctorApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private MapperUtil mapperUtil;

    @Test
    void getAllDoctors_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllDoctors_WithValidJwt() throws Exception {
        mockMvc.perform(get("/api/doctors")
                        .with(jwt().authorities(List.of(new SimpleGrantedAuthority("doctor")))))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getAllDoctors_ReturnsDoctors() throws Exception {
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        List<Doctor> doctors = Arrays.asList(doctor1, doctor2);

        DoctorDTO doctorDTO1 = new DoctorDTO();
        doctorDTO1.setId(1L);
        DoctorDTO doctorDTO2 = new DoctorDTO();
        doctorDTO2.setId(2L);

        given(doctorService.getAllDoctors()).willReturn(doctors);
        given(mapperUtil.mapList(doctors, DoctorDTO.class)).willReturn(Arrays.asList(doctorDTO1, doctorDTO2));

        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getDoctorsBySpecialty_ReturnsDoctors() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setSpecialty("Cardiology");
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(1L);
        doctorDTO.setSpecialty("Cardiology");

        given(doctorService.getDoctorsBySpecialty("Cardiology")).willReturn(Arrays.asList(doctor));
        given(mapperUtil.mapList(Arrays.asList(doctor), DoctorDTO.class)).willReturn(Arrays.asList(doctorDTO));

        mockMvc.perform(get("/api/doctors/specialty/Cardiology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].specialty").value("Cardiology"));
    }
}