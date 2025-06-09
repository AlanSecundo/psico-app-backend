package com.example.psicoclinic.psicoapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.flyway.enabled=false", exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class PsicoappApplicationTests {

	@Test
	void contextLoads() {
	}

}
