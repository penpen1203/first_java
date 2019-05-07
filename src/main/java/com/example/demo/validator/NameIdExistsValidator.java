package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Domain.CalcDays;
import com.example.demo.service.DaysService;

public class NameIdExistsValidator implements ConstraintValidator<NameIdExists, String> {

  @Autowired
  DaysService service;

  @Override
  public void initialize(NameIdExists annotation) {}

  @Override
  public boolean isValid(String velue, ConstraintValidatorContext context) {
    if (velue == null) {
      return true;
    }

    CalcDays calc = service.search(velue);
    if (calc != null) {
      return true;
    } else {
      return false;
    }
  }
}
