package com.example.demo.view.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class RegisterPage {

  private static final String URL = "http://localhost:8080/index";

  @FindBy(id = "indexNavigation")
  private SelenideElement indexNavigation;

  @FindBy(id = "simNavigation")
  private SelenideElement simNavigation;

  @FindBy(id = "createButton")
  private SelenideElement createButton;

  @FindBy(id = "editButton")
  private SelenideElement editButton;

  @FindBy(id = "delButton")
  private SelenideElement delButton;

  @FindBy(name = "nameId")
  private SelenideElement nameId;

  @FindBy(name = "name")
  private SelenideElement name;

  @FindBy(name = "adDays")
  private SelenideElement adDays;

  @FindBy(name = "adMonths")
  private SelenideElement adMonths;

  @FindBy(name = "adYears")
  private SelenideElement adYears;

  public static RegisterPage open() {
    return Selenide.open(URL, RegisterPage.class);
  }

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

  public RegisterPage 日付IDは(String 日付ID) {
    nameId.setValue(日付ID);
    return page(RegisterPage.class);
  }

  public RegisterPage 日付名は(String 日付名) {
    name.setValue(日付名);
    return page(RegisterPage.class);
  }

  public RegisterPage 加減日は(String 加減日) {
    adDays.setValue(加減日);
    return page(RegisterPage.class);
  }

  public RegisterPage 加減月は(String 加減月) {
    adMonths.setValue(加減月);
    return page(RegisterPage.class);
  }

  public RegisterPage 加減年は(String 加減年) {
    adYears.setValue(加減年);
    return page(RegisterPage.class);
  }

  public RegisterPage 新規登録() {
    createButton.click();
    return page(RegisterPage.class);
  }

  public EditPage 編集(int 行) {
    $(By.id(行 + "_" + "editButton")).click();
    return page(EditPage.class);
  }

  public RegisterPage 削除(int 行) {
    $(By.id(行 + "_" + "delButton")).click();
    return page(RegisterPage.class);
  }

  public SelenideElement 登録結果() {
    return $(By.id("registerResult"));
  }

}
