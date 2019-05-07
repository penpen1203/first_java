package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.Domain.CalcDays;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class CalcDaysRepositoryTest {

  @Autowired
  private CalcDaysRepository cR;

  @Before
  public void setUp() throws Exception {
    cR.save(new CalcDays("AD01", "翌日", 1, 0, 0));
    cR.save(new CalcDays("BD01", "前日", -1, 0, 0));
  }

  @Test
  public void 全件取得して結果をリストで取得できる事() throws Exception {
    List<CalcDays> actual = cR.findAll();

    assertThat(actual.size()).isEqualTo(2);
  }

  @Test
  public void 日付IDで検索してキーに紐づく１件だけ取得できること() throws Exception {
    CalcDays actual = cR.findByNameIdIs("AD01");

    assertThat(actual.getNameId()).isEqualTo("AD01");
    assertThat(actual.getName()).isEqualTo("翌日");
    assertThat(actual.getAdDays()).isEqualTo(1);
    assertThat(actual.getAdMonths()).isEqualTo(0);
    assertThat(actual.getAdYears()).isEqualTo(0);
  }

  @Test
  public void 存在しないデータを検索すると結果がnullとなる事() throws Exception {
    CalcDays actual = cR.findByNameIdIs("EmptyData");

    assertThat(actual).isNull();
  }

  @Test
  public void nullで検索すると結果がnullとなる事() throws Exception {
    CalcDays actual = cR.findByNameIdIs(null);
    assertThat(actual).isNull();
  }

  @Test
  public void 新規登録ができる事() throws Exception {
    CalcDays calc = createCalc("AY01", "翌年", 0, 0, 1);

    cR.save(calc);
    CalcDays actual = cR.findByNameIdIs("AY01");
    assertThat(actual.getNameId()).isEqualTo("AY01");
    assertThat(actual.getName()).isEqualTo("翌年");
    assertThat(actual.getAdDays()).isEqualTo(0);
    assertThat(actual.getAdMonths()).isEqualTo(0);
    assertThat(actual.getAdYears()).isEqualTo(1);
  }

  @Test
  public void キーに紐づく１件の更新ができる事() throws Exception {
    CalcDays calcDays = createCalc("AD01", "翌々日", 2, 0, 0);
    cR.save(calcDays);
    CalcDays actual = cR.findByNameIdIs("AD01");

    assertThat(actual.getNameId()).isEqualTo("AD01");
    assertThat(actual.getName()).isEqualTo("翌々日");
    assertThat(actual.getAdDays()).isEqualTo(2);
    assertThat(actual.getAdMonths()).isEqualTo(0);
    assertThat(actual.getAdYears()).isEqualTo(0);
  }

  @Test
  public void キーに紐づく１件の削除ができる事() throws Exception {
    cR.deleteById("AD01");
    List<CalcDays> actual = cR.findAll();

    assertThat(actual.size()).isEqualTo(1);
  }

  private CalcDays createCalc(String 日付ID, String 日付名, int 加減日, int 加減月, int 加減年) {
    CalcDays calcDays = new CalcDays();
    calcDays.setNameId(日付ID);
    calcDays.setName(日付名);
    calcDays.setAdDays(加減日);
    calcDays.setAdMonths(加減月);
    calcDays.setAdYears(加減年);

    return calcDays;

  }


}
