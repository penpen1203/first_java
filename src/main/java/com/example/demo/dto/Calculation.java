package com.example.demo.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.example.demo.validator.NameIdExists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Calculation {

  @NotNull
  private LocalDate refeDate;

  @NotBlank
  @NameIdExists
  private String nameId;

  private String calcResultDate;
}
