# ADR-006: Standard Module Folder Structure

**Date:** 2025-06-08  
**Status:** Accepted  
**Decision:** All modules should follow a specific structure. 

## Context
To maintain a clean, modular, and scalable architecture across the project, we decided to standardize the folder structure for all business modules (e.g., psychologist, patient, clinic).

Each module is responsible for its own domain logic and should follow a clear separation of concerns to improve readability, testing, and developer onboarding.

## Decision
All domain modules should follow the structure below:

```
<module>/
├── controllers/               # HTTP entry points (e.g., REST controllers)
├── usecases/                  # Application-specific orchestration logic
├── services/                  # Business logic services (reusable within the module)
├── repositories/               # Interfaces and implementations for data access
├── dtos/                      # Data Transfer Objects (input/output models)
├── mappers/                   # Mapping logic between entities and DTOs
├── entities/                   # Domain entities (e.g., JPA annotated classes)
└── config/                   # Module-specific configuration (optional)
```

### Responsibilities

- **controller**: Handles HTTP requests and delegates logic.
- **usecase**: Coordinates the application flow; acts as the "application service" layer.
- **service**: Contains reusable domain logic within the module.
- **repository**: Abstracts persistence and enables swapping data sources if needed.
- **dto**: Encapsulates input and output models to decouple API and domain layers.
- **mapper**: Transforms data between domain models and DTOs.
- **entity**: Represents the persistent domain structure.
- **config**: Holds configuration specific to the module (if any).

## Positive Consequences
- Promotes consistency across modules.
- Facilitates onboarding and maintenance.
- Encourages separation of concerns and testability.
- Modules can evolve independently and scale better.

## Negative Trade-offs
- **Increased boilerplate**: Requires creation of many files for even simple features.
- **Overengineering risk**: For very small modules, this structure may feel too heavy or redundant.
- **Initial complexity**: New contributors may need guidance to understand the role of each layer.
- **Cross-module logic**: Logic spanning multiple modules might require additional orchestration layers or shared abstractions, increasing complexity.

## Alternatives Considered
- Flat structure with mixed responsibilities: rejected due to poor maintainability.
- Grouping by technical role globally (e.g., one `controller` package for all modules): rejected in favor of domain-oriented modularization.
