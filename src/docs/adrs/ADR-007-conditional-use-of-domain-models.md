# ADR-007: Conditional Use of Domain Models

**Date:** 2025-06-22  
**Status:** Accepted  
**Context:**  
In layered architectures (such as DDD, Clean Architecture, or Hexagonal), it's common to separate the domain layer (pure models) from the infrastructure layer (e.g., JPA entities). However, during the development of the PsicoClinica MVP, some structures like `Specialty` and `ClinicProfile` emerged as simple data holders without relevant business logic.

The question arose whether to strictly create domain models for these structures or directly use the JPA entities in use cases.

**Decision:**  
To accelerate development and keep things simple during the MVP phase:

- It is allowed to use JPA entities directly within `UseCases` **as long as the entity has no business logic** and represents only simple data.
- This is a **conscious and documented exception**, and **does not imply abandoning the architecture**.
- If in the future `Specialty`, `ClinicProfile`, or any other entity gains responsibilities, rules, or needs to be tested independently, they will be refactored into domain models and mapped via mappers.

**Positive Consequences:**
- Reduced complexity and overhead during initial development.
- Fewer unnecessary classes and mappings.
- Faster delivery of value without compromising long-term practices.

**Negative Consequences:**
- The project may appear inconsistent: some `UseCases` use `models`, others use `entities`.
- May cause confusion for new developers if the guideline is not clear.

**Mitigations:**
- This ADR will be stored in the repository to justify the choice.
- Use cases that use entities directly should include a comment noting this deliberate decision.

**Conclusion:**  
We choose **pragmatism guided by clarity**: keeping the system clean and ready to evolve, without prematurely adding unnecessary layers. The architecture remains modular and well-defined, with full separation applied as real complexity emerges.
