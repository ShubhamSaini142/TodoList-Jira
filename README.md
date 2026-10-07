<h1 align="center">📋 TodoList-Jira</h1>

<p align="center">
  A Jira-style task board: a <b>Spring Boot</b> REST API secured with <b>Spring Security</b>, and a <b>React</b> Kanban UI with drag-and-drop.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 17"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3-6DB33F?style=flat-square&logo=springboot&logoColor=white" alt="Spring Boot 3.3"/>
  <img src="https://img.shields.io/badge/Spring%20Security-BCrypt-6DB33F?style=flat-square&logo=springsecurity&logoColor=white" alt="Spring Security"/>
  <img src="https://img.shields.io/badge/React-18-61DAFB?style=flat-square&logo=react&logoColor=black" alt="React 18"/>
  <img src="https://img.shields.io/badge/Tailwind%20CSS-3-06B6D4?style=flat-square&logo=tailwindcss&logoColor=white" alt="Tailwind CSS 3"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white" alt="MySQL"/>
</p>

## ✨ Features

- 🗂️ **Kanban board** with *To Do*, *In Progress*, *Under Review* and *Completed* columns, using drag-and-drop from `react-beautiful-dnd`
- ➕ **Create tasks** with a name and details. New tasks start in *To Do*.
- 🔐 **Register and log in**, with passwords hashed using BCrypt
- 🌐 **REST API** built with Spring Web and Spring Data JPA, with CORS enabled for the React dev server

## 🏗️ Project Structure

```
TodoList-Jira/
├── todlistbackend/        # Spring Boot API (Maven)
│   └── src/main/java/com/jira/todolist/todlistbackend/
│       ├── Controller/    # AuthController, TaskController
│       ├── Services/      # task and user services
│       ├── Repositories/  # Spring Data JPA repositories
│       ├── beans/         # Tasks and Users entities
│       └── security/      # Spring Security configuration
└── todolistfrontend/      # React app styled with Tailwind CSS
    └── src/               # Home, DragDrop board, Login page, routes
```

## 🔌 API

| Method | Endpoint | Description |
|:--|:--|:--|
| `POST` | `/register` | Register a new user |
| `POST` | `/login` | Log in |
| `POST` | `/tasks/create` | Create a task |
| `GET` | `/tasks/getAll` | List all tasks |
| `PATCH` | `/tasks/update/{id}` | Update a task's status |

## 🚀 Getting Started

**Prerequisites:** Java 17, Node.js 18+, MySQL 8

**1. Start the backend**

```bash
cd todlistbackend
# set spring.datasource.url, username and password in src/main/resources/application.properties
./mvnw spring-boot:run        # runs on http://localhost:8080
```

**2. Start the frontend**

```bash
cd todolistfrontend
npm install
npm start                     # opens http://localhost:3000
```
