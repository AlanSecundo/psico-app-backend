# ADR-001: User Authentication with Keycloak

**Date:** 06/08/2025  
**Status:** Accepted  
**Decision:** Use **Keycloak** as the authentication provider for the management application for psychologists.

## Context
- The application requires secure authentication for psychologists, with the possibility of social login (Google).
- The founder wants to learn more about robust and scalable IAM tools.
- There are future plans to scale the system for multiple clinics, where realm-based control would be useful.

## Considered Alternatives
- **Manual authentication with custom database:** simple for the MVP, but would require implementing password reset, hashing, sessions, etc.
- **Firebase Auth or Auth0:** easier to integrate, but with customization limitations or higher costs in the future.

## Final Decision
Self-hosted Keycloak via Docker.

## Consequences
- Greater robustness and scalability from the start.
- Steeper technical learning curve, but aligned with the founder’s goals.
- Google login will be implemented.
