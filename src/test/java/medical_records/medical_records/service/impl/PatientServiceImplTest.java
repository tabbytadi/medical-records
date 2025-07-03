package medical_records.medical_records.service.impl;

import com.example.medicalrecords.data.entity.Patient;
import com.example.medicalrecords.data.repo.PatientRepository;
import com.example.medicalrecords.service.impl.PatientServiceImpl;
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
class PatientServiceImplTest {

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientServiceImpl patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getPatientById_ReturnsPatient() {
        given(patientRepository.findById(1L)).willReturn(Optional.of(patient));

        Patient found = patientService.getPatientById(1L);

        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getPatientsByGpDoctor_ReturnsPatients() {
        given(patientRepository.findByGpDoctorId(1L)).willReturn(Arrays.asList(patient));

        List<Patient> patients = patientService.getPatientsByGpDoctor(1L);

        assertThat(patients).hasSize(1);
        assertThat(patients.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @WithMockUser(authorities = {"doctor"})
    void getPatientsWithInsurance_ReturnsPatients() {
        patient.setHasInsurance(true);
        given(patientRepository.findByHasInsurance(true)).willReturn(Arrays.asList(patient));

        List<Patient> patients = patientService.getPatientsWithInsurance();

        assertThat(patients).hasSize(1);
        assertThat(patients.get(0).isHasInsurance()).isTrue();
    }
}