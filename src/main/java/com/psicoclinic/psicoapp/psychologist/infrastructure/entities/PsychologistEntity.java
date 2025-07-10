package com.psicoclinic.psicoapp.psychologist.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "psychologist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PsychologistEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "external_id", nullable = false, unique = true)
  private String externalId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(length = 50)
  private String crp;

  @Column(length = 20)
  private String phone;

  @Column(name = "on_board_completed", nullable = false)
  @Builder.Default
  private boolean onBoardCompleted = false;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "edited_at")
  private LocalDateTime editedAt;
}

