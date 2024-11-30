@startuml
package "Database" {
  class Users {
    + user_id : INT [PK]
    + username : VARCHAR(255) [UNIQUE]
    + password : VARCHAR(255)
    + role : VARCHAR(255)
  }

  class Students {
    + student_id : INT [PK, FK -> Users.user_id]
    + name : VARCHAR(255)
    + email : VARCHAR(255) [UNIQUE]
    + phone : VARCHAR(255)
    + address : VARCHAR(255)
  }

  class Courses {
    + course_id : INT [PK]
    + course_name : VARCHAR(255)
    + description : VARCHAR(255)
    + credits : INT
  }

  class Enrollments {
    + enrollment_id : INT [PK]
    + student_id : INT [FK -> Students.student_id]
    + course_id : INT [FK -> Courses.course_id]
  }

  class Attendance {
    + enrollment_id : INT [PK, FK -> Enrollments.enrollment_id]
    + date : DATE [PK]
    + status : VARCHAR(20)
  }

  class Grades {
    + enrollment_id : INT [PK, FK -> Enrollments.enrollment_id]
    + grade : INT
  }

  class Bills {
    + bill_id : INT [PK]
    + student_id : INT [FK -> Students.student_id]
    + bill_amount : FLOAT
    + paid_status : ENUM('Paid', 'Unpaid')
    + due_date : DATE
  }
}


' Relationships between tables
Users "1" <-- "1" Students : "inherits"
Students "1" --> "*" Enrollments : "enrolls"
Courses "1" --> "*" Enrollments : "offers"
Enrollments "1" --> "*" Attendance : "records"
Enrollments "1" --> "1" Grades : "earns"
Students "1" --> "*" Bills : "has"

