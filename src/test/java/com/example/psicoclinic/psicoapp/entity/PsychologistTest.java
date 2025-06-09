package com.example.psicoclinic.psicoapp.entity;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PsychologistTest {

    @Test
    void testGettersAndSetters() {
        Psychologist psychologist = new Psychologist();
        UUID id = UUID.randomUUID();
        String name = "Dr. John Doe";
        String email = "john.doe@example.com";
        String crp = "123456";

        psychologist.setId(id);
        psychologist.setName(name);
        psychologist.setEmail(email);
        psychologist.setCrp(crp);

        assertEquals(id, psychologist.getId());
        assertEquals(name, psychologist.getName());
        assertEquals(email, psychologist.getEmail());
        assertEquals(crp, psychologist.getCrp());
    }
}
