package com.example.demo.view;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.codeborne.selenide.Configuration;
import com.example.demo.view.page.EditPage;
import com.example.demo.view.page.RegisterPage;
import com.example.demo.view.page.SimulationPage;

@RunWith(SpringRunner.class)
@DirtiesContext
@TestPropertySource(locations = "classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterViewTest {

  private RegisterPage page;

  @BeforeClass
  public static void setUp() {
    Configuration.holdBrowserOpen = false;
  }

  @Before
  public void setUpTest() {
    page = RegisterPage.open().トップページへ戻る();
  }

  @Test
  public void No1_シミュレーション画面へ遷移する事() throws Exception {
    SimulationPage actual = page.シミュレーションページへ();

    assertThat(actual.title()).isEqualTo("計算ページ");
  }

  @Test
  public void No2_新規登録できる事() throws Exception {
    RegisterPage actual = page.日付IDは("AD01").日付名は("翌日").加減年は("0").加減月は("0").加減日は("1").新規登録();
    actual.登録結果().shouldBe(visible);
  }

  @Test
  public void No3_編集ページへ遷移する事() throws Exception {
    EditPage actual = page.編集(1);
    assertThat(actual.title()).isEqualTo("編集ページ");
  }

  @Test
  public void No4_削除処理ができる事() throws Exception {
    RegisterPage actual = page.削除(1);
    actual.登録結果().shouldBe(hidden);
  }

}
