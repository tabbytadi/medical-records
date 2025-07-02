package com.example.medicalrecords.service;

import com.example.medicalrecords.data.entity.SickLeave;
import java.time.LocalDate;
import java.util.List;

public interface SickLeaveService {
    List<SickLeave> getAllSickLeaves();
    SickLeave getSickLeaveById(Long id);
    SickLeave createSickLeave(SickLeave sickLeave);
    SickLeave updateSickLeave(Long id, SickLeave sickLeave);
    void deleteSickLeave(Long id);
    List<SickLeave> getSickLeavesBetweenDates(LocalDate start, LocalDate end);
}