package com.example.demo.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "java_study")
public class CalcDays {

  @Id
  @Column(name = "nameId", length = 11, nullable = false)
  private String nameId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "adDays")
  private int adDays;

  @Column(name = "adMonths")
  private int adMonths;

  @Column(name = "adYears")
  private int adYears;


}
