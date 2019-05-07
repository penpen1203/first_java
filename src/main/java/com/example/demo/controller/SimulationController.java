package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.dto.Calculation;
import com.example.demo.service.DaysService;

@Controller
public class SimulationController {

  @Autowired
  private DaysService service;

  @RequestMapping(value = "/simulation", method = RequestMethod.GET)
  public String simulation(@ModelAttribute("calc") Calculation calc, Model model) {
    model.addAttribute("calc", calc);
    return "simulation";
  }

  @RequestMapping(value = "/simulation", method = RequestMethod.POST)
  public String calculate(@ModelAttribute @Validated Calculation calc, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {

      return simulation(calc, model);
    }
    calc.setCalcResultDate(service.calculate(service.search(calc.getNameId()), calc.getRefeDate()));
    model.addAttribute("calc", calc);
    return "simulation";
  }

}

