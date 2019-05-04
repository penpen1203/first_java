package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
public class EditControllerTest {

  private MockMvc sut;

  @MockBean
  private DaysService service;

  @Autowired
  private EditController target;

  @Before
  public void setUp() throws Exception {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("classpath:templates/");
    viewResolver.setSuffix(".html");

    sut = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
  }

  @Test
  public void 更新ページのリクエスト結果が正常となりサービスの検索が呼ばれてviewとしてeditが返る事() throws Exception {
    sut.perform(get("/calcList/edit/{nameId}", "AD01")).andExpect(status().isOk())
        .andExpect(view().name("calcList/edit"));
    verify(service, times(1)).search("AD01");
  }

  @Test
  public void 更新ページで更新処理を行うとサービスで処理されてシミュレーション画面に遷移される事() throws Exception {
    sut.perform(
        post("/calcList/edit/{nameId}", "AD01").param("nameId", "AD01").param("name", "TEST日付"))
        .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/index"));
    verify(service, times(1)).update(any());
  }

  @Test
  public void 更新ページで日付名がNULLの状態で更新処理を行うと例外情報が入った状態で画面が返る事() throws Exception {
    sut.perform(post("/calcList/edit/{nameId}", "AD01").param("nameId", "AD01"))
        .andExpect(status().isOk()).andExpect(view().name("calcList/edit"));
  }



}
