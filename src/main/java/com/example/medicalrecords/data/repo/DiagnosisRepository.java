package com.example.medicalrecords.data.repo;

import com.example.medicalrecords.data.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Optional<Diagnosis> findByCode(String code);
    List<Diagnosis> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String nameQuery, String codeQuery);
}