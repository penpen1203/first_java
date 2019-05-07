package com.example.demo.view.page;

import static com.codeborne.selenide.Selenide.page;
import org.openqa.selenium.support.FindBy;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class EditPage {

  @FindBy(id = "indexNavigation")
  private SelenideElement indexNavigation;

  @FindBy(id = "simNavigation")
  private SelenideElement simNavigation;

  @FindBy(id = "updateButton")
  private SelenideElement updateButton;

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

  public EditPage 日付IDは(String 日付ID) {
    nameId.setValue(日付ID);
    return page(EditPage.class);
  }

  public EditPage 日付名は(String 日付名) {
    name.setValue(日付名);
    return page(EditPage.class);
  }

  public EditPage 加減日は(String 加減日) {
    adDays.setValue(加減日);
    return page(EditPage.class);
  }

  public EditPage 加減月は(String 加減月) {
    adMonths.setValue(加減月);
    return page(EditPage.class);
  }

  public EditPage 加減年は(String 加減年) {
    adYears.setValue(加減年);
    return page(EditPage.class);
  }

  public RegisterPage 編集() {
    updateButton.click();
    return page(RegisterPage.class);
  }

}
