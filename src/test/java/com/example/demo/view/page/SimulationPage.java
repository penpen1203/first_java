package com.example.demo.view.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class SimulationPage {

  @FindBy(id = "indexNavigation")
  private SelenideElement indexNavigation;

  @FindBy(id = "simNavigation")
  private SelenideElement simNavigation;

  @FindBy(name = "refeDate")
  private SelenideElement refeDate;

  @FindBy(name = "nameId")
  private SelenideElement nameId;

  @FindBy(id = "simButton")
  private SelenideElement simButton;

  public String title() {
    return Selenide.title();
  }

  public RegisterPage トップページへ戻る() {
    indexNavigation.click();
    return page(RegisterPage.class);
  }

  public SimulationPage シミュレーションページへ() {
    simNavigation.click();
    return page(SimulationPage.class);
  }

  public SimulationPage 基準日は(String 基準日) {
    refeDate.setValue(基準日);
    return page(SimulationPage.class);
  }

  public SimulationPage 日付IDは(String 日付ID) {
    nameId.setValue(日付ID);
    return page(SimulationPage.class);
  }

  public SimulationPage シミュレーション() {
    simButton.click();
    return page(SimulationPage.class);
  }

  public SelenideElement 計算結果() {
    return $(By.id("calcResult"));
  }
}

