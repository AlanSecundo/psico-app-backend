# ADR-004: Multi-Tenant Architecture with a Single Realm in Keycloak

**Date:** 06/08/2025  
**Decision:** Use a single Realm in Keycloak and implement multi-tenant data isolation within the application.

## Context
- The application is a SaaS platform for psychologists, where each professional has login access and isolated data.
- Keycloak will be used as the identity server, providing authentication and access control via JWT.
- The question arose between using multiple Realms (one per psychologist) or a single Realm with all users.

## Considered Alternatives

### - Multiple Realms:
- Complete isolation per client
- More secure in large systems where the client manages their own realm
- However, it increases maintenance complexity, scalability challenges, and integration effort

### - Single Realm:
- Easier to scale
- Ideal for SaaS where users (psychologists) are clients of the same application
- Easier backend integration, centralized control, and use of groups/roles for differentiation

## Final Decision
Use a single Realm (`psico-app`) for all users.  
Data isolation will be implemented in the backend using `keycloakId` (the JWT token `sub`) associated with a `Psychologist` entity.

## Consequences

- Multi-tenant architecture: all data is isolated by `psychologist_id`
- Greater control and easier maintenance
- Future potential to segment by groups, roles, or subscription plans within the same Realm

## Note on "Tenant"
- A "Tenant" represents each logged-in psychologist with their own data.
- "Multi-Tenant" = all users share the same application, but data is isolated.
- "Single-Tenant" with multiple Realms doesn't quite apply here, as each Realm would still contain multiple users. Thus, using multiple Realms would be a form of multi-tenant **with physical authentication isolation**, but **not necessarily single-tenant**.
