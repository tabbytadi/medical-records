package com.example.medicalrecords.service.impl;

import com.example.medicalrecords.data.entity.SickLeave;
import com.example.medicalrecords.data.repo.SickLeaveRepository;
import com.example.medicalrecords.exceptions.SickLeaveNotFoundException;
import com.example.medicalrecords.service.SickLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SickLeaveServiceImpl implements SickLeaveService {
    private final SickLeaveRepository sickLeaveRepository;

    @Override
    public List<SickLeave> getAllSickLeaves() {
        return sickLeaveRepository.findAll();
    }

    @Override
    public SickLeave getSickLeaveById(Long id) {
        return sickLeaveRepository.findById(id)
                .orElseThrow(() -> new SickLeaveNotFoundException(id));
    }

    @Override
    public SickLeave createSickLeave(SickLeave sickLeave) {
        return sickLeaveRepository.save(sickLeave);
    }

    @Override
    public SickLeave updateSickLeave(Long id, SickLeave sickLeave) {
        SickLeave existing = getSickLeaveById(id);
        existing.setExamination(sickLeave.getExamination());
        existing.setStartDate(sickLeave.getStartDate());
        existing.setEndDate(sickLeave.getEndDate());
        existing.setDaysCount(sickLeave.getDaysCount());
        return sickLeaveRepository.save(existing);
    }

    @Override
    public void deleteSickLeave(Long id) {
        sickLeaveRepository.deleteById(id);
    }

    @Override
    public List<SickLeave> getSickLeavesBetweenDates(LocalDate start, LocalDate end) {
        return sickLeaveRepository.findByStartDateBetween(start, end);
    }
}