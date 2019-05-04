package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.time.LocalDate;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.Domain.CalcDays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DaysServiceTest {

  private DaysService service;

  @Before
  public void setUp() throws Exception {
    service = new DaysService();
  }

  @Test
  public void 基準日にNULLを渡すとNullPointerExceptionとなる事() throws Exception {
    assertThatThrownBy(() -> {
      service.calculate(setUpCalcDays(0, 0, 0), null);
    }).isInstanceOf(NullPointerException.class);
  }

  @Test
  public void 計算式にNULLを渡すとNullPointerExceptionとなる事() throws Exception {
    assertThatThrownBy(() -> {
      service.calculate(null, LocalDate.now());
    }).isInstanceOf(NullPointerException.class);
  }

  private CalcDays setUpCalcDays(int 加減日, int 加減月, int 加減年) {
    CalcDays calcDays = new CalcDays();
    calcDays.setAdDays(加減日);
    calcDays.setAdMonths(加減月);
    calcDays.setAdYears(加減年);
    return calcDays;
  }

}
