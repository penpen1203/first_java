package com.example.demo.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.Domain.CalcDays;
import com.example.demo.Domain.Calculation;
import com.example.demo.service.DaysService;

@Controller
public class DaysController {

  @Autowired
  private DaysService service;

  /**
   * @param model
   * @return
   */
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(Model model, @ModelAttribute("calc") Calculation calc) {

    List<CalcDays> results = service.searchAll();
    model.addAttribute("results", results);
    return "index";

  }

  @RequestMapping(value = "/index", method = RequestMethod.POST)
  public String calculate(@ModelAttribute @Validated Calculation calc, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "redirect:index";
    }
    CalcDays result = service.search(calc.getNameId());
    calc.setCalcResultDate(service.calculate(result, calc.getRefeDate()));
    model.addAttribute("calc", calc);
    return "index";
  }


  @RequestMapping(value = "/edit/{nameId}", method = RequestMethod.GET)
  public String detail(Model model, @PathVariable String nameId) {

    model.addAttribute("calcResult", service.search(nameId));
    return "edit";

  }

  @RequestMapping(value = "/edit/{nameId}", method = RequestMethod.POST)
  @Transactional
  public String edit(@ModelAttribute CalcDays calcDays) {

    service.update(calcDays);
    return "redirect:index";

  }

  @RequestMapping(value = "/{nameId}", method = RequestMethod.POST)
  @Transactional
  public String delte(@PathVariable String nameId) {

    service.delete(nameId);
    return "redirect:index";

  }



}

