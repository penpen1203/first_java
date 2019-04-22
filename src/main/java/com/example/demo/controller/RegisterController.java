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
import com.example.demo.service.DaysService;

@Controller
public class RegisterController {

  @Autowired
  private DaysService service;

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(CalcDays calcDays, Model model) {

    List<CalcDays> results = service.searchAll();
    model.addAttribute("results", results);
    return "index";

  }

  // セットされたインスタンスに値を代入
  @RequestMapping(value = "/index", method = RequestMethod.POST)
  @Transactional
  public String create(@ModelAttribute @Validated CalcDays calcDays, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return index(calcDays, model);
    }
    service.create(calcDays);
    return "redirect:/index";
  }

  @RequestMapping(value = "/{nameId}", method = RequestMethod.POST)
  @Transactional
  public String delete(@PathVariable String nameId) {

    service.delete(nameId);
    return "redirect:/index";

  }

}

