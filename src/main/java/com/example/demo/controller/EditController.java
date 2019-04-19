package com.example.demo.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.Domain.CalcDays;
import com.example.demo.service.DaysService;

@Controller
@RequestMapping(value = "/edit/{nameId}")
public class EditController {

  @Autowired
  private DaysService service;

  @RequestMapping(method = RequestMethod.GET)
  public String detail(Model model, @PathVariable String nameId) {

    model.addAttribute("calcResult", service.search(nameId));
    return "edit";

  }

  @RequestMapping(method = RequestMethod.POST)
  @Transactional
  public String edit(@ModelAttribute CalcDays calcDays) {

    service.update(calcDays);
    return "redirect:index";

  }


}
