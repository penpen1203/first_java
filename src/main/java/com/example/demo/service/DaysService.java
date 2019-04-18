package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Domain.CalcDays;
import com.example.demo.repository.CalcDaysRepository;

@Service
@Transactional
public class DaysService {

  @Autowired
  private CalcDaysRepository repository;

  public List<CalcDays> searchAll() {
    return repository.findAll();
  }

  public String calculate(CalcDays calcDays, LocalDate refeDate) {
    LocalDate ld2 = refeDate.plusDays(calcDays.getAdDays()).plusMonths(calcDays.getAdMonths())
        .plusYears(calcDays.getAdYears());
    return ld2.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

  }

  public CalcDays search(String nameId) {
    return repository.findByNameIdIs(nameId);

  }

  public CalcDays create(CalcDays calcDays) {
    return repository.save(calcDays);
  }

  public CalcDays update(CalcDays calcDays) {
    return repository.save(calcDays);
  }


  public void delete(String nameId) {
    repository.deleteById(nameId);
  }



}