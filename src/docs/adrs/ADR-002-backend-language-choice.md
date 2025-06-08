# ADR-002: Backend Language Choice - Java vs Kotlin

**Date:** 06/08/2025  
**Decision:** Use **Java 21** as the primary backend language for the application.

## Context
- The project will be built with Spring Boot and requires stability, scalability, and integration with tools like Keycloak.
- The founder is familiar with Java and wants to deepen knowledge in backend architecture and security.
- Kotlin was considered for its advantages in conciseness, null safety, and expressiveness, but it introduces a learning curve and has fewer official examples with Spring.

## Considered Alternatives
- **Java 21:** traditional and robust language with full support for Spring Boot 3.2 LTS, a large knowledge base, and complete interoperability with existing libraries.
- **Kotlin:** modern and concise language with excellent Spring integration, but with a steeper learning curve and fewer production-level examples in corporate environments.

## Final Decision
Java 21 with Spring Boot 3.2 LTS will be used as the main backend foundation.

## Consequences
- Easier maintenance, hiring, and continuous learning.
- The project will be structured with potential for future integration with Kotlin modules or components, if desired.
- Full compatibility with Spring, Keycloak, and the broader Java ecosystem.
