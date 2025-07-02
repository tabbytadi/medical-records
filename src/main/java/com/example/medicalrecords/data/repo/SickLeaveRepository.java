package com.example.medicalrecords.data.repo;

import com.example.medicalrecords.data.entity.SickLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {
    List<SickLeave> findByStartDateBetween(LocalDate start, LocalDate end);
}