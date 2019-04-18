package com.example.demo.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.Domain.CalcDays;
import com.example.demo.service.DaysService;

@Controller
@RequestMapping(value = "/create")
public class RegisterController {

  @Autowired
  private DaysService service;

  // createページ内にエンティティのインスタンスをセット
  @RequestMapping(method = RequestMethod.GET)
  public String newCalc(@ModelAttribute CalcDays calcDays) {
    return "create";
  }

  // セットされたインスタンスに値を代入
  @RequestMapping(method = RequestMethod.POST)
  @Transactional
  public String create(@ModelAttribute @Validated CalcDays calcDays, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "create";
    }
    service.create(calcDays);
    return "redirect:index";
  }
}

