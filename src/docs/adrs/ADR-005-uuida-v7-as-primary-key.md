# ADR-005: Use of UUID v7 as the Primary Key for Entities

**Date:** 2025-06-08  
**Status:** Accepted  
**Decision:** Adopt UUID v7 as the primary key for all core entities in the system.

## Context

The project is a SaaS platform for psychologists, requiring:
- Data isolation and security across different users (multi-tenant)
- Safe and unique identifiers suitable for public APIs
- The potential to scale across multiple databases and distributed environments

Historically, auto-incrementing integer primary keys (`SERIAL`, `BIGINT`) provide excellent index performance but lack the benefits of global uniqueness and security offered by UUIDs.  
UUID v4 is the traditional random version but can degrade index performance on large relational databases due to lack of ordering.

## Considered Alternatives

- **UUID v4:** Random, but may degrade index and insert performance due to lack of sequential ordering.
- **UUID v1/v6:** Time-based, but can leak sensitive host information (such as MAC addresses).
- **ULID:** A compatible alternative, but not an RFC official standard like UUID.
- **BIGINT/SERIAL:** Highly performant, but exposes record order/volume and is not ideal for multi-tenant or distributed systems.

## Decision

Adopt **UUID v7** for primary keys, generated in the backend (Java) using an RFC 4122-compliant library.

## Consequences

- **Improved index performance:** UUID v7 values are time-ordered, reducing index fragmentation in databases such as PostgreSQL.
- **Global uniqueness:** Retains the primary benefit of UUIDs for distributed systems.
- **Readiness for distributed and multi-tenant architectures:** Facilitates data migration and interoperability.
- **Future compatibility:** UUID v7 is an emerging RFC 4122 standard and is expected to be supported natively by major databases soon.

## Implementation

- **In the database:** Use the `uuid` type for primary key columns (PostgreSQL).
- **In Java:** Generate UUIDs using the [`uuid-creator`](https://github.com/f4b6a3/uuid-creator) library, e.g. `UuidCreator.getTimeOrdered()`.

