package com.example.demo.view;

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
import com.example.demo.view.page.RegisterPage;
import com.example.demo.view.page.SimulationPage;

@RunWith(SpringRunner.class)
@DirtiesContext
@TestPropertySource(locations = "classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimulationViewTest {

  private SimulationPage page;

  @BeforeClass
  public static void setUp() {
    Configuration.holdBrowserOpen = false;
  }

  @Before
  public void setUpTest() {
    RegisterPage.open().日付IDは("AD01").日付名は("翌日").加減年は("0").加減月は("0").加減日は("1").新規登録();
    page = RegisterPage.open().シミュレーションページへ();
  }

  @Test
  public void No1_トップページへ遷移する事() throws Exception {
    RegisterPage actual = page.トップページへ戻る();
    assertThat(actual.title()).isEqualTo("日付カリキュレーター");
  }

  @Test
  public void No2_基準日に20190501を入力して結果が取得できる事() throws Exception {
    SimulationPage actual = page.基準日は("2019/05/01").日付IDは("AD01").シミュレーション();
    actual.計算結果().shouldBe(visible);
  }

}
