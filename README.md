# 🧠 PsicoApp API

This is the main API for **PsicoApp**, an intelligent management system designed for psychologists.

The purpose of this application is to provide:
- A clear and strategic overview of the clinic's operations
- Automated insights about appointments and finances
- Personalized features to help psychologists improve both clinical practice and business management

---

## 🚀 About the Development

This project is being built with a strong focus on software architecture and developer productivity, using:

- **Java 21 + Spring Boot 3.2 (LTS)**
- **Gradle with Groovy DSL**
- **PostgreSQL** as the primary database
- **Flyway** for database version control
- **Keycloak** as the OAuth2 authentication provider (Single Sign-On for psychologists)

### 🤖 AI Tools Supporting Development

This project is being co-developed with the help of artificial intelligence, including:
- **ChatGPT (GPT-4.1)**: for architectural decisions, ADR generation, and development support
- **Jules (Google)**: as a productivity assistant and development guide
- **GitHub Copilot**, **JetBrains AI Assistant**, and other AI tools: being explored for code completion, refactoring, and insights

---

## 🏁 Getting Started

### Prerequisites:
- Java 21
- Docker + Docker Compose
- IDE (IntelliJ, VSCode or similar)

### Steps:

1. Clone the repository:
```bash
git clone https://github.com/your-username/psicoapp-backend.git
cd psicoapp-backend
```

2. Start infrastructure services (Keycloak + databases):
```bash
cd infra
docker compose -f docker-compose-keycloak.yml up -d
```

3. Run the application:
```bash
./gradlew bootRun
```

Application will be available at:
```
http://localhost:8081
```

---

## 🗂️ Architectural Decision Records (ADRs)

All technical decisions are documented in the [`docs/adrs`](./docs/adrs/) folder.

---

## 📌 Project Status

- [x] Initial project configuration
- [x] Keycloak authentication setup
- [x] Database structure with Flyway
- [ ] Psychologist entity and CRUD
- [ ] Clinical dashboard and insights
- [ ] Cloud deployment (future)

---

## 🤝 Contribution

This project is being developed in an exploratory and experimental context, but feedback and contributions are always welcome!

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
