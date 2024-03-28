package com.javatpoint.service;

import com.javatpoint.model.CoronaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface CoronaDetailsRepository extends JpaRepository<CoronaDetails,Long> {
    //for unvaccinated-count
    long countByVac1IsNullAndVac2IsNullAndVac3IsNullAndVac4IsNull();
    //for active-patients-last-month
    long countByRecoveryDateIsNullOrRecoveryDateAfterAndGetPositiveBefore(LocalDate date, LocalDate date2);
    boolean existsByHmoMemberId(Long id);
}
