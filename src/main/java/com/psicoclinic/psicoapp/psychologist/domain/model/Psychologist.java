package com.psicoclinic.psicoapp.psychologist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Psychologist {

  private final UUID id;
  private final String name;
  private final String externalId;
  private final String email;
  private final String phone;
  private final String crp;
  private final boolean onBoardCompleted;

  public static Psychologist newRegister(String externalId, String name, String email) {
    return Psychologist.builder()
        .externalId(externalId)
        .name(name)
        .email(email)
        .onBoardCompleted(false)
        .build();
  }

  public Psychologist completeOnboard(String crp, String phone) {
    return this.toBuilder()
        .crp(crp)
        .phone(phone)
        .onBoardCompleted(true)
        .build();
  }
}

