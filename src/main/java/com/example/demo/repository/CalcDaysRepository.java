package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Domain.CalcDays;

@Repository
public interface CalcDaysRepository extends JpaRepository<CalcDays, String> {

  CalcDays findByNameIdIs(String nameId);

}
