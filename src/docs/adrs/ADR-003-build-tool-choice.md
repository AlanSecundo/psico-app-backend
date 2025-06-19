# ADR-003: Build Tool Choice - Maven vs Gradle (Groovy)

**Date:** 06/08/2025
**Status:** Accepted  
**Decision:** Use **Gradle with Groovy DSL** as the build tool for the project.

## Context
- The project will be built with Spring Boot, using Java 21 as the base.
- The team is seeking productivity, flexibility, and fast builds to efficiently scale the project.
- Maven was considered for its stability and strong documentation but is more verbose and less flexible than Gradle.
- Gradle was chosen for its **superior performance** in incremental builds and greater flexibility, in addition to being more modern and concise.

## Considered Alternatives
- **Maven:** Stable, widely used, and easy to configure, but more verbose and less flexible.
- **Gradle with Groovy DSL:** More flexible, with faster builds and simpler syntax, and the preferred choice for modern and reactive projects.

## Final Decision
We chose to use **Gradle with Groovy DSL** as the primary build tool, prioritizing agility and flexibility for the project's evolution.

## Consequences
- The build configuration will be cleaner and more scalable.
- Faster incremental builds will help increase productivity.
- Flexibility to include logic in build scripts and facilitate project evolution.
