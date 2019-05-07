package com.example.demo.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.Domain.CalcDays;
import com.example.demo.service.DaysService;

@Controller
@RequestMapping
public class EditController {

  @Autowired
  private DaysService service;

  @GetMapping(value = "calcList/edit/{nameId}")
  public String edit(@PathVariable("nameId") String nameId, CalcDays calcDays, Model model) {

    model.addAttribute("calcResult", service.search(nameId));
    return "calcList/edit";

  }

  @PostMapping(value = "calcList/edit/{nameId}")
  @Transactional
  public String update(@ModelAttribute @Validated CalcDays calcDays, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {

      model.addAttribute("calcResult", calcDays);
      return "calcList/edit";
    }
    service.update(calcDays);
    return "redirect:/index";
  }

}


