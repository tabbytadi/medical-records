package medical_records.medical_records.service.impl;

import com.example.medicalrecords.data.entity.Doctor;
import com.example.medicalrecords.data.repo.DoctorRepository;
import com.example.medicalrecords.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class DoctorServiceImplTest {

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorServiceImpl doctorService;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setSpecialty("Cardiology");
        doctor.setGp(true);
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getDoctorById_ReturnsDoctor() {
        given(doctorRepository.findById(1L)).willReturn(Optional.of(doctor));

        Doctor found = doctorService.getDoctorById(1L);

        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getDoctorsBySpecialty_ReturnsDoctors() {
        given(doctorRepository.findBySpecialty("Cardiology")).willReturn(Arrays.asList(doctor));

        List<Doctor> doctors = doctorService.getDoctorsBySpecialty("Cardiology");

        assertThat(doctors).hasSize(1);
        assertThat(doctors.get(0).getSpecialty()).isEqualTo("Cardiology");
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getGpDoctors_ReturnsGpDoctors() {
        given(doctorRepository.findByGp(true)).willReturn(Arrays.asList(doctor));

        List<Doctor> doctors = doctorService.getGpDoctors();

        assertThat(doctors).hasSize(1);
        assertThat(doctors.get(0).isGp()).isTrue();
    }
}