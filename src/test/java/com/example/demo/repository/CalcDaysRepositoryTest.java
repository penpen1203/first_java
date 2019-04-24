package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.Domain.CalcDays;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
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

}
