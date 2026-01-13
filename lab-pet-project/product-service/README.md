# Lab Pet Project — Product CRUD API

Небольшой backend-проект на **Spring Boot**, реализующий CRUD-операции для сущности **Product**  
с использованием **PostgreSQL** и **Docker**.

Проект создан в учебных целях для закрепления основ:
- Spring Boot
- REST API
- JPA / Hibernate
- работы с базой данных
- контейнеризации через Docker

---

## 📦 Функциональность

Реализован CRUD для сущности `Product`:

- ➕ Создание продукта
- 📄 Получение списка продуктов
- 🔍 Получение продукта по id
- ✏️ Обновление продукта
- ❌ Удаление продукта

### Product
```json
{
  "id": 1,
  "name": "Apple",
  "price": 100
}
```

## 🛠 Используемые технологии

- Java 25
- Spring Boot 3.5.9
- Spring Web
- Spring Data JPA (Hibernate)
- PostgreSQL 16
- Docker / Docker Compose
- Maven
- HikariCP
- Apache Tomcat (embedded)

## 📁 Структура проекта
```
src/main/java/kz/lab/petproject
│
├── controller
│   └── ProductController.java
│
├── service
│   └── ProductService.java
│
├── repository
│   └── ProductRepository.java
│
├── domain
│   └── Product.java
│
└── LabPetProjectApplication.java
```
## 🚀 Запуск проекта

### 🔹 Предварительные требования

Убедись, что установлено:
- Docker
- Docker Compose

Проверка:

```bash
docker --version
docker compose version
```

### 🔹 Запуск

В корне проекта выполнить:
```bash
docker compose up --build
```
приложение запустится на порту 8080

### 🔹 Проверка работы

```curl
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Apple","price":100}'
```

### 🧠 Архитектурные решения

- Controller — принимает HTTP-запросы
- Service — содержит бизнес-логику
- Repository — отвечает за доступ к данным
- Entity — отображение таблицы БД
