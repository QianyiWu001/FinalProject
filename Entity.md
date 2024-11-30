@startuml
package "Entity Layer" as entity {
  class User {
    - userId: int
    - username: String
    - password: String
    - role: String
    + getUserId(): int
    + setUserId(int): void
    + getUsername(): String
    + setUsername(String): void
    + getPassword(): String
    + setPassword(String): void
    + getRole(): String
    + setRole(String): void
  }

  class Student {
    - name: String
    - email: String
    - phone: String
    - address: String
    + getName(): String
    + setName(String): void
    + getEmail(): String
    + setEmail(String): void
    + getPhone(): String
    + setPhone(String): void
    + getAddress(): String
    + setAddress(String): void
  }

  class Course {
    - courseId: int
    - courseName: String
    - description: String
    - credits: int
    + getCourseId(): int
    + setCourseId(int): void
    + getCourseName(): String
    + setCourseName(String): void
    + getDescription(): String
    + setDescription(String): void
    + getCredits(): int
    + setCredits(int): void
  }



  class Grade {
    - enrollmentId: int
    - studentId: int
    - courseId: int
    - grade: int
    + getEnrollmentId(): int
    + setEnrollmentId(int): void
    + getStudentId(): int
    + setStudentId(int): void
    + getCourseId(): int
    + setCourseId(int): void
    + getGrade(): int
    + setGrade(int): void
  }
  class Bill {
    - billID: int
    - studentId: int
    - billAmount: double
    - dueDate: LocalDate
    - paidStatus: String
    + getBillID(): int
    + setBillID(int): void
    + getStudentId(): int
    + setStudentId(int): void
    + getBillAmount(): double
    + setBillAmount(double): void
    + getDueDate(): LocalDate
    + setDueDate(LocalDate): void
    + getPaidStatus(): PaidStatus
    + setPaidStatus(PaidStatus): void
  }

  class Enrollment {
    - enrollmentId: int
    - studentId: int
    - courseId: int
    + getEnrollmentId(): int
    + setEnrollmentId(int): void
    + getStudentId(): int
    + setStudentId(int): void
    + getCourseId(): int
    + setCourseId(int): void
  }



  class Attendance {
    - enrollmentId: int
    - date: Date
    - status: String
    - studentId: int
    - courseId: int
    + getEnrollmentId(): int
    + setEnrollmentId(int): void
    + getDate(): Date
    + setDate(Date): void
    + getStatus(): String
    + setStatus(String): void
    + getStudentId(): int
    + setStudentId(int): void
    + getCourseId(): int
    + setCourseId(int): void
  }
}

  Student --|> User


  Enrollment --> Student : *..1
  Enrollment --> Course : *..1
  Grade --> Student : *..1
  Grade --> Course : *..1
  Bill --> Student : *..1
  Attendance --> Student : *..1
  Attendance --> Course : *..1