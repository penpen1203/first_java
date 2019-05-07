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
import com.example.demo.view.page.EditPage;
import com.example.demo.view.page.RegisterPage;

@RunWith(SpringRunner.class)
@DirtiesContext
@TestPropertySource(locations = "classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditViewTest {

  private EditPage page;

  @BeforeClass
  public static void setUp() {
    Configuration.holdBrowserOpen = false;
  }

  @Before
  public void setUpTest() {
    RegisterPage.open().日付IDは("AD01").日付名は("翌日").加減年は("0").加減月は("0").加減日は("1").新規登録();
    page = RegisterPage.open().編集(1);
  }

  @Test
  public void No1_トップページへ遷移する事() throws Exception {
    RegisterPage actual = page.トップページへ戻る();
    assertThat(actual.title()).isEqualTo("日付カリキュレーター");
  }

  @Test
  public void No2_更新ページで更新できる事() throws Exception {
    RegisterPage actual = page.日付IDは("AD01").日付名は("翌々日").加減年は("0").加減月は("0").加減日は("2").編集();
    actual.登録結果().shouldBe(visible);
  }

}
