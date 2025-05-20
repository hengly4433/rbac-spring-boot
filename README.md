# 🔐 Spring Boot RBAC – User Management System

A production-ready **Spring Boot** application implementing **User Management with Role-Based Access Control (RBAC)**. This backend service securely manages users, roles, and permissions using **JWT Authentication**, PostgreSQL, and Docker for deployment.

## 🚀 Features

- ✅ **User Authentication & Authorization**
- 🔒 **JWT-based Security**
- 🎭 **Role-Based Access Control (RBAC)**
- 🧑‍💼 Admin-restricted Role/Permission Assignment
- 🧾 DTOs & Mappers for clean API contracts
- 🐘 PostgreSQL for persistent storage
- 🐳 Docker & Docker Compose support
- 📊 Spring Boot Actuator & Swagger UI (OpenAPI)

---

## 🧱 Architecture

Controller -> Service -> Repository
↘️ ↘️
DTO Mapper


- **Controllers**: Expose secured REST APIs
- **Services**: Business logic with interface-driven development
- **Repositories**: JPA-based access to PostgreSQL
- **DTOs & Mappers**: Avoid direct entity exposure

---

## 🛠️ Tech Stack

| Layer        | Technology                             |
|--------------|----------------------------------------|
| Backend      | Spring Boot 3, Spring Security         |
| Persistence  | Spring Data JPA, PostgreSQL            |
| Auth         | JWT (JJWT `0.12.6`)                    |
| API Docs     | Springdoc OpenAPI + Swagger UI         |
| Dev Tools    | Spring Boot DevTools, Actuator         |
| Deployment   | Docker, Docker Compose                  |

---

## ⚙️ Environment Variables (.env)

```dotenv
# PostgreSQL
POSTGRES_DB=rbac
POSTGRES_USER=postgres
POSTGRES_PASSWORD=example

# App DB Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://rbac-db:5432/rbac
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=example

# JWT
APP_JWTSECRET=01234567890123456789012345678901
APP_JWTEXPIRATIONMS=3600000

# Port
SERVER_PORT=8080

```

## 🐳 Docker Setup

To build and run the application using Docker:

#### 1. Build & Run Containers

```bash
docker-compose up --build
```

#### 2. Check Containers
```bash
docker compose ps
```
### 🧪 API Endpoints

| Endpoint           | Method | Description             |
|--------------------| ------ | ----------------------- |
| `/api/auth/login`  | POST   | Login (get JWT token)   |
| `/api/users`       | GET    | List users (admin only) |
| `/api/roles`       | GET    | Get roles (admin only)  |
| `/api/permissions` | GET    | Get permissions         |

## 🧾 Swagger UI

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: /v3/api-docs

## 🔐 Role-Based Access Control (RBAC)

- ADMIN: Full access to users, roles, permissions
- MANAGER: Restricted access
- USER: Can view/edit own profile

## 🗂️ Project Structure

```text
src
├── main
│   ├── java/com.blockcode.rbac
│   │   ├── config           # Security, JWT, CORS configurations
│   │   ├── controller       # REST Controllers (Auth, User, Role)
│   │   ├── dto              # Data Transfer Objects for API requests/responses
│   │   ├── entity           # JPA Entities (User, Role, Permission)
│   │   ├── exception        # Exception Handling
│   │   ├── mapper           # MapStruct or manual DTO <-> Entity mappers
│   │   ├── repository       # Spring Data JPA Repositories
│   │   ├── service          # Service Interfaces / Service Implementations
│   │   └── SpringBootRbacApplication.java  # Main Spring Boot application
│   └── resources
│       └── application.properties         # Spring configuration
└── test
    └── com.blockcode.rbac                 # Unit & integration tests
```

## 🧪 Running Tests

```bash
mvn clean test
```

#### - If using JWT in tests, ensure the test secret is 256 bits:
```bash
@SpringBootTest(properties = {
  "APP_JWTSECRET=01234567890123456789012345678901",
  "APP_JWTEXPIRATIONMS=3600000"
})
```

## ✅ Health Check
- You can monitor the app with:
```bash
curl http://localhost:8080/actuator/health
```

## ✍️ Author
- Siev Hengly
- Backend Developer | Freelancer | Tech Enthusiast

## 📄 License
- MIT - Feel free to use this in your projects!


---

## 🧪 Sample SQL Data for Testing

Use the following SQL script to populate initial permissions, roles, users, and their many-to-many relationships for testing and development purposes.

```sql
-- PERMISSIONS
INSERT INTO permission (id, name) VALUES (1, 'VIEW_USER');
INSERT INTO permission (id, name) VALUES (2, 'CREATE_USER');
INSERT INTO permission (id, name) VALUES (3, 'UPDATE_USER');
INSERT INTO permission (id, name) VALUES (4, 'DELETE_USER');

-- ROLES
INSERT INTO role (id, name) VALUES (1, 'ADMIN');

-- ROLE-PERMISSIONS (Many-to-Many)
INSERT INTO role_permissions (role_id, permissions_id) VALUES (1, 1);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (1, 2);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (1, 3);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (1, 4);

-- USERS
-- Passwords encoded using BCrypt (password = `admin123`, `manager123`, `user123`)
INSERT INTO users (id, username, email, password) VALUES 
(1, 'admin', 'admin@example.com', '$2a$10$7QW9BZyQAvktPIpx0mI1BO/wJSsyoH6FuKmRHRwE7JAp6Ko3o/1Le')

-- USER-ROLES (Many-to-Many)
INSERT INTO user_roles (user_id, roles_id) VALUES (1, 1); -- admin -> ADMIN
```

### 🔐 Login credentials
| Username | Password     | Role    |
| -------- | ------------ | ------- |
| admin    | `admin123`   | ADMIN   |

