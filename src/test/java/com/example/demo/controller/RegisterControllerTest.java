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
public class RegisterControllerTest {

  private MockMvc sut;

  @MockBean
  private DaysService service;

  @Autowired
  private RegisterController target;

  @Before
  public void setUp() throws Exception {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("classpath:templates/");
    viewResolver.setSuffix(".html");

    sut = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
  }

  @Test
  public void トップページのリクエスト結果が正常となりViweとしてindexが返る事() throws Exception {
    sut.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"));
  }

  @Test
  public void 新規登録ページで登録処理を行うとサービスが処理されてトップページに遷移される事() throws Exception {
    sut.perform(post("/index").param("nameId", "TEST").param("name", "テスト日付名"))
        .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/index"));
    verify(service, times(1)).create(any());
  }

  @Test
  public void 新規登録ページで日付IDが空の状態で登録処理を行うと例外情報が入った状態で画面が返る事() throws Exception {
    sut.perform(post("/index").param("nameId", "").param("name", "テスト日付名"))
        .andExpect(status().isOk()).andExpect(view().name("index"));
  }

  @Test
  public void 新規登録ページで日付IDが11桁以上の状態で登録処理を行うと例外情報が入った状態で画面が返る事() throws Exception {
    sut.perform(post("/index").param("nameId", "012345678901").param("name", "テスト日付名"))
        .andExpect(status().isOk()).andExpect(view().name("index"));
  }

  @Test
  public void 新規登録ページで日付名がNULLの状態で登録処理を行うと例外情報が入った状態で画面が返る事() throws Exception {
    sut.perform(post("/index").param("nameId", "TEST")).andExpect(status().isOk())
        .andExpect(view().name("index"));
  }

  @Test
  public void 新規登録ページで日付名が空の状態で登録処理を行うと例外情報が入った状態で画面が返る事() throws Exception {
    sut.perform(post("/index").param("nameId", "TEST").param("name", "")).andExpect(status().isOk())
        .andExpect(view().name("index"));
  }

  @Test
  public void 新規登録ページで日付名が空白の状態で登録処理を行うと例外情報が入った状態で画面が返る事() throws Exception {
    sut.perform(post("/index").param("nameId", "TEST").param("name", " "))
        .andExpect(status().isOk()).andExpect(view().name("index"));
  }

}
