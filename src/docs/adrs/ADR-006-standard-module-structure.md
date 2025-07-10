
# ADR-006: Standard Module Folder Structure

**Date:** 2025-06-22  
**Status:** Accepted  
**Decision:** All modules must follow a specific structure.

## Context
To maintain a clean, modular, and scalable architecture, we decided to standardize the folder structure for all domain modules (e.g., psychologist, patient, clinic).

Each module is responsible for its domain logic, clearly separating concerns to enhance readability, testability, and developer onboarding.

## Decision
All domain modules must adhere to the following standardized structure:

```
<module>/
├── adapter/
│   └── controllers/            # HTTP entry points (e.g., REST controllers)
├── application/
│   └── usecases/               # Application-specific orchestration logic
├── domain/
│   ├── services/               # Domain-specific business logic
│   ├── repositories/           # Interfaces for data access
│   ├── entities/               # Domain entities (e.g., JPA annotated classes)
│   └── enums/                  # Domain enums (optional)
├── infrastructure/
│   ├── persistence/            # Repository implementations and data sources
│   ├── mappers/                # Mapping logic between entities and DTOs
│   └── config/                 # Module-specific configuration (optional)
└── dtos/                       # Data Transfer Objects (input/output models)
```

### Responsibilities

- **adapter/controllers**: Handles HTTP requests and delegates to use cases.
- **application/usecases**: Coordinates application flow; orchestrates domain services and repositories.
- **domain/services**: Encapsulates domain-specific business logic, reusable within the module.
- **domain/repositories**: Defines interfaces for persistence, ensuring decoupling from implementation.
- **domain/entities**: Represents persistent domain structures.
- **domain/enums**: Contains enumerations specific to the domain (optional).
- **infrastructure/persistence**: Implements repositories and integrates with external data sources.
- **infrastructure/mappers**: Manages transformations between domain entities and DTOs.
- **infrastructure/config**: Module-specific configuration settings (optional).
- **dtos**: Data structures used for input/output across layers, decoupling API and domain logic.

## Positive Consequences
- Ensures consistency across modules.
- Facilitates easier onboarding and maintenance.
- Promotes strong separation of concerns and better testability.
- Supports independent evolution and scalability of modules.

## Negative Trade-offs
- **Increased boilerplate**: Many files must be created, even for simple features.
- **Risk of overengineering**: Smaller modules might find this structure overly complex or redundant.
- **Initial complexity**: New contributors may require guidance to understand layer responsibilities.
- **Cross-module logic complexity**: Logic spanning multiple modules may necessitate additional orchestration layers or shared abstractions, potentially increasing complexity.

## Alternatives Considered
- Flat structure mixing responsibilities: rejected due to maintainability concerns.
- Global grouping by technical role (e.g., single `controller` package for all modules): rejected in favor of domain-oriented modularization.
