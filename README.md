# Backend Developer Task

## Project Overview
This is a Spring Boot backend project that uses Firebase Realtime Database for all data storage. It provides RESTful APIs for managing courses and students, with full CRUD operations. API documentation is available via Swagger UI.

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven
- Firebase project (with serviceAccountKey.json downloaded)

### Steps
1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd Backend-Developer-Task/backend
   ```
2. **Add your Firebase service account key:**
   - Place your `serviceAccountKey.json` file in `src/main/resources/`.
3. **Configure Firebase Database URL:**
   - The URL is set in `FirebaseConfig.java` (default: `https://task-c22fb.firebaseio.com`).
4. **Install dependencies and build:**
   ```bash
   mvn clean install
   ```
5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
6. **Access Swagger UI:**
   - Open [http://localhost:8045/swagger-ui.html](http://localhost:8045/swagger-ui.html) in your browser.

## API Documentation

### Swagger UI
- All endpoints are documented and testable via Swagger UI at `/swagger-ui.html`.

### Main Endpoints
#### Course
- `POST /api/v1/course/save` — Add a new course
- `GET /api/v1/course/get-all` — Get all courses
- `GET /api/v1/course/get/{id}` — Get course by ID
- `PUT /api/v1/course/update/{id}` — Update course
- `DELETE /api/v1/course/delete/{id}` — Delete course

#### Student
- `POST /api/v1/student/save` — Add a new student
- `GET /api/v1/student/get-all` — Get all students
- `GET /api/v1/student/get/{id}` — Get student by ID
- `PUT /api/v1/student/update/{id}` — Update student
- `DELETE /api/v1/student/delete/{id}` — Delete student

## Sample Requests & Responses

### Add Course
**Request:**
```json
POST /api/v1/course/save
{
  "name": "Math 101",
  "fee": 1000.0,
  "lecturerId": "L123",
  "lecturerName": "John Doe"
}
```
**Response:**
```
Course saved successfully
```

### Get All Courses
**Request:**
```
GET /api/v1/course/get-all
```
**Response:**
```json
[
  {
    "id": "-Nabc123...",
    "name": "Math 101",
    "fee": 1000.0,
    "lecturerId": "L123",
    "lecturerName": "John Doe"
  }
]
```

### Add Student
**Request:**
```json
POST /api/v1/student/save
{
  "title": "Mr.",
  "name": "Alice Smith",
  "address": "123 Main St",
  "city": "Colombo",
  "course": "Math 101"
}
```
**Response:**
```
Student saved successfully
```

### Get All Students
**Request:**
```
GET /api/v1/student/get-all
```
**Response:**
```json
[
  {
    "id": "-Nxyz456...",
    "title": "Mr.",
    "name": "Alice Smith",
    "address": "123 Main St",
    "city": "Colombo",
    "course": "Math 101"
  }
]
```

## Notes
- All data is stored in Firebase Realtime Database under `courses` and `students` nodes.
- Make sure your Firebase project allows read/write access for development.
- For production, secure your Firebase rules appropriately.


