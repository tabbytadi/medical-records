package com.example.medicalrecords.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sick_leaves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SickLeave extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer daysCount;
}