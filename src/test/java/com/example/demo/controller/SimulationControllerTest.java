package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.example.demo.service.DaysService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")

public class SimulationControllerTest {

  private MockMvc sut;

  @MockBean
  private DaysService service;

  @Autowired
  private SimulationController target;

  @Before
  public void setUp() throws Exception {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("classpath:templates/");
    viewResolver.setSuffix(".html");

    sut = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();

  }

  @Test
  public void シミュレーションページのリクエスト結果が正常となりViewとしてsimulationが返る事() throws Exception {
    sut.perform(get("/simulation")).andExpect(status().isOk()).andExpect(view().name("simulation"));
  }

  @Test
  public void シミュレーションページで計算基準日を入力して計算実行を押すと計算サービスが呼ばれている事() throws Exception {
    sut.perform(post("/simulation").param("refeDate", "20180501")).andExpect(status().isOk())
        .andExpect(view().name("simulation"));
  }

}
