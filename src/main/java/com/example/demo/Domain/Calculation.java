package com.example.demo.Domain;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Calculation {

  @NotNull
  private LocalDate refeDate;

  @NotNull
  private String nameId;

  private String calcResultDate;
}
