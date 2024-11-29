@startuml
skinparam componentStyle rectangle

package "Controller Layer" {
  class UserController {
    +login(userID, pwd): boolean
    +logout(): void
    +checkRole(userID): String
  }
  class AdminController {
    +viewAllCourses(): List<Course>
    +addStudent(student: Student): boolean
    +editCourse(courseID, updatedInfo): boolean
  }
  class StudentController {
    +viewProfile(): Student
    +viewEnrolledCourses(): List<Course>
    +viewAttendance(courseID): List<Attendance>
  }
}

package "Service Layer" {
  class UserService {
    +login(userID, pwd): boolean
    +logout(): void
    +checkRole(userID): String
  }
  class AdminService {
    +manageCourses(): void
    +manageStudents(): void
    +manageAttendance(): void
    +manageGrades(): void
  }
  class StudentService {
    +viewProfile(): Student
    +viewBill(): Bill
    +viewGrades(courseID): Grade
  }
}

package "DAO Layer" {
  class StudentDAO {
    +getAllStudents(): List<Student>
    +addStudent(student: Student): boolean
    +updateStudent(student: Student): boolean
  }
  class CourseDAO {
    +getAllCourses(): List<Course>
    +addCourse(course: Course): boolean
    +updateCourse(courseID, updatedInfo): boolean
  }
  class AttendanceDAO {
    +recordAttendance(studentID, courseID, date, status): boolean
    +getAttendanceByCourse(courseID): List<Attendance>
  }
  class GradeDAO {
    +setGrade(studentID, courseID, gradeValue): boolean
    +getGrade(studentID, courseID): Grade
  }
}

package "Entity Layer" {
  class User {
    -userID: int
    -username: String
    -password: String
    -role: String
  }
  class Admin {
    +manageStudents(): void
    +manageCourses(): void
    +manageGrades(): void
  }
  class Student {
    +viewProfile(): void
    +viewGrades(): void
    +viewAttendance(): void
  }
  class Course {
    -courseID: int
    -courseName: String
    -credits: int
  }
  class Attendance {
    -studentID: int
    -courseID: int
    -date: Date
    -status: String
  }
  class Grade {
    -studentID: int
    -courseID: int
    -gradeValue: String
  }
}

User <|-- Admin
User <|-- Student

UserController --> UserService
AdminController --> AdminService
StudentController --> StudentService

UserService --> UserDAO
AdminService --> StudentDAO
AdminService --> CourseDAO
AdminService --> AttendanceDAO
AdminService --> GradeDAO
StudentService --> StudentDAO
StudentService --> CourseDAO
StudentService --> AttendanceDAO
StudentService --> GradeDAO
@enduml
