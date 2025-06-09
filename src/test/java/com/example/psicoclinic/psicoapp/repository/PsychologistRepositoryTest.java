package com.example.psicoclinic.psicoapp.repository;

import com.example.psicoclinic.psicoapp.entity.Psychologist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use the actual database (H2, PostgreSQL, etc.)
@ActiveProfiles("test") // Ensure a specific test profile is used if needed
class PsychologistRepositoryTest {

    @Autowired
    private PsychologistRepository psychologistRepository;

    @Test
    void testSaveAndFindById() {
        Psychologist psychologist = new Psychologist();
        psychologist.setName("Dr. Jane Doe");
        psychologist.setEmail("jane.doe@example.com");
        psychologist.setCrp("654321");

        Psychologist savedPsychologist = psychologistRepository.save(psychologist);
        assertNotNull(savedPsychologist.getId());

        Psychologist foundPsychologist = psychologistRepository.findById(savedPsychologist.getId()).orElse(null);
        assertNotNull(foundPsychologist);
        assertEquals(savedPsychologist.getName(), foundPsychologist.getName());
        assertEquals(savedPsychologist.getEmail(), foundPsychologist.getEmail());
        assertEquals(savedPsychologist.getCrp(), foundPsychologist.getCrp());
    }
}
