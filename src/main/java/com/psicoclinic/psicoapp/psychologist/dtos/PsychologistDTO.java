package com.psicoclinic.psicoapp.psychologist.dtos;

import java.util.UUID;

public record PsychologistDTO(
    UUID id,
    String name,
    String email,
    String phone,
    String crp,
    boolean onBoardCompleted
) {}

