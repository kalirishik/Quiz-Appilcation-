# 🎯 Quiz App - Spring Boot REST API  

A **Quiz Application** built with **Spring Boot, Spring Data JPA, Hibernate, Lombok, and MySQL**, providing a robust backend for creating, managing, and attempting quizzes.  
The application exposes **RESTful APIs** for adding questions, generating quizzes, submitting answers, and retrieving results with pagination support.  

## 🚀 Features  
- 📌 **Question Management** – Add, update, delete, and fetch quiz questions.  
- 📌 **Quiz Management** – Generate quizzes dynamically based on category & difficulty.  
- 📌 **Answer Submission** – Submit responses and calculate scores instantly.  
- 📌 **Pagination Support** – Retrieve questions efficiently using pagination.  
- 📌 **Database Integration** – MySQL database with `data.sql` for preloaded questions.  
- 📌 **Swagger UI** – API documentation for easy testing.  
- 📌 **Layered Architecture** – Follows Controller-Service-Repository design.  

## 🛠️ Tech Stack  
- **Backend:** Spring Boot (REST APIs)  
- **Database:** MySQL with Spring Data JPA & Hibernate  
- **ORM:** Hibernate with JPA annotations  
- **Utilities:** Lombok (for boilerplate code reduction)  
- **API Docs:** Swagger UI  
- **Build Tool:** Maven  

## 📂 Use Cases  
- Teachers can add and manage quiz questions.  
- Students can attempt quizzes and receive instant scores.  
- Admins can filter and view quizzes/questions with pagination.  
---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.9+
- MySQL 8.x

# 📁 Project Structure
```
quiz-app/
├─ docs/
│  └─ screenshots/
│     ├─ swagger-questions.png
│     └─ swagger-quiz.png
├─ src/
│  ├─ main/java/com/project/quizapp/
│  │  ├─ QuizAppApplication.java
│  │  ├─ controller/
│  │  │  ├─ QuestionController.java
│  │  │  └─ QuizController.java
│  │  ├─ model/
│  │  │  ├─ Question.java
│  │  │  ├─ Quiz.java
│  │  │  ├─ QuestionWrapper.java
│  │  │  └─ Response.java
│  │  ├─ repository/
│  │  │  ├─ QuestionRepository.java
│  │  │  └─ QuizRepository.java
│  │  └─ service/
│  │     ├─ QuestionService.java
│  │     └─ QuizService.java
│  └─ resources/
│     ├─ application.properties
│     └─ data.sql
└─ pom.xml
```
# 🔌 API Overview

- **Question APIs (/question)**
  - GET /question/allquestions – list all (no pagination)
  - GET /question/viewQuestions?page={p}&size={s} – paginated list
  - GET /question/category/{category} – by category
  - GET /question/difficulty/{level} – by difficulty (Easy/Medium/Hard)
  - GET /question/random/{category}/{numQ} – N random by category
  - GET /question/getQuestionById/{id} – get by id
  - POST /question/addQuestion – add one
  - POST /question/addQuestions – add many
  - PUT /question/updateQuestion/{id} – update
  - DELETE /question/deleteQuestion/{id} – delete
  - GET /question/countByCategory/{category} – analytics count

- **Quiz APIs (/quiz)**
  - POST /quiz/createQuiz?category={c}&numQ={n}&title={t}
  - GET /quiz/getQuizQuestionsById/{id} – wrapped questions (no answers)
  - POST /quiz/submitQuiz/{id} – send responses → score
  - GET /quiz/getAllQuizzes – list quizzes
  - DELETE /quiz/deleteQuiz/{id} – delete quiz
-- DELETE /quiz/deleteQuiz/{id} – delete quiz


# 🧩 Implementation Notes
  - @ManyToMany between Quiz and Question.
  - /quiz/getQuizQuestionsById/{id} → returns QuestionWrapper (hides correct answer).
  - Quiz scoring = match submitted response with right Answer.
  - Pagination handled via Pageable in Spring Data JPA.

# 📸 Screenshots
