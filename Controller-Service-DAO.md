@startuml


' 设置全局样式
skinparam packageStyle rect
skinparam defaultFontSize 12
skinparam packageBorderThickness 1
skinparam packagePadding 10
left to right direction

package "Entity Layer" as entity {
  class User
  class Student
  class Course
  class Enrollment
  class Grade
  class Bill
  class Attendance
}
' 定义 DAO 包
package "DAO Layer" as dao {
  class BaseDAO {
    + getConnection(): Connection
  }

  class UserDAO {
    + addUser(User user): boolean
    + deleteUser(int userId): boolean
    + updateUser(int userId, String username, String password, String role): boolean
    + getUserRole(int userId): String
    + getUserById(int userId): String[]
    + generateUserId(): int
    + validateLogin(String username, String password): User
    + getUserIdByUsername(String username): int
  }

  class StudentDAO {
    + addStudent(Student student): boolean
    + deleteStudent(int studentId): boolean
    + updateStudent(Student student): boolean
    + getStudentById(int studentId): Student
    + searchStudents(String keyword): List<Student
    + getAllStudents(): List<Student>
  }

  class CourseDAO {
    + addCourse(Course course): boolean
    + deleteCourse(int courseId): boolean
    + updateCourse(Course course): boolean
    + getCourseById(int courseId): Course
    + searchCourses(String searchText): List<Course>
    + getAllCourses(): List<Course>
  }

  class EnrollmentDAO {
    + getCoursesByStudentId(int studentId): List<Course>
    + enrollStudentInCourse(int studentId, int courseId): boolean
    + unenrollStudentFromCourse(int studentId, int courseId): boolean
  }

  class GradesDAO {
    + getAllGrades(): List<Grade>
    + addGrade(Grade grade): boolean
    + deleteGrade(int enrollmentId): boolean
    + updateGrade(Grade grade): boolean
    + searchGrades(String query): List<Grade>
    + getGradesByStudentId(int studentId): List<Grade>
  }

  class BillDAO {
    + addBill(Bill bill): boolean
    + deleteBill(int billId): boolean
    + updateBill(Bill bill): boolean
    + getBillById(int billId): Bill
    + getBillsByStudentId(int studentId): List<Bill>
  }

  class AttendanceDAO {
    + markAttendance(Attendance attendance): boolean
    + getAttendanceByStudentId(int studentId): List<Attendance>
    + getAttendanceByCourseId(int courseId): List<Attendance>
  }
}

' 定义 Service 包
package "Service Layer" as service {
  class StudentService {
    + addStudent(Student student): boolean
    + deleteStudent(int studentId): boolean
    + updateStudent(Student student): boolean
    + getStudentById(int studentId): Student
    + searchStudents(String keyword): List<Student>
    + getAllStudents(): List<Student>
  }

  class CourseService {
    + getAllCourses(): List<Course>
    + addCourse(Course course): boolean
    + updateCourse(Course course): boolean
    + deleteCourse(int courseId): boolean
    + searchCourses(String searchText): List<Course>
  }

  class GradesService {
    + getAllGrades(): List<Grade>
    + addGrade(Grade grade): boolean
    + deleteGrade(int enrollmentId): boolean
    + updateGrade(Grade grade): boolean
    + searchGrades(String query): List<Grade>
  }

  class BillService {
    + getBillsByStudentId(int studentId): List<Bill>
    + addBill(Bill bill): boolean
    + deleteBill(int billId): boolean
  }

  class AttendanceService {
    + getAllAttendance(): List<Attendance>
    + addAttendance(Attendance attendance): boolean
    + updateAttendance(Attendance attendance): boolean
    + deleteAttendance(int enrollmentId, String date): boolean
    + searchAttendance(String searchText): List<Attendance>
    + isEnrollmentValid(int enrollmentId): boolean
    + getEnrollmentId(int studentId, int courseId): int
  }
}

' 定义 Controller 包
package "Controller Layer" as controller {
  class LoginController {
    + login(String username, String password): User
  }

  class AdminLoginController {
    + handleCourseManagement(): void
    + handleStudentManagement(): void
    + handleAttendanceManagement(): void
    + handleGradesManagement(): void
    + handleBack(): void
    + handleExit(): void
  }

  class AttendanceController {
    + getAllAttendance(): List<Attendance>
    + addAttendance(Attendance attendance): boolean
    + updateAttendance(Attendance attendance): boolean
    + deleteAttendance(int enrollmentId, String date): boolean
    + searchAttendance(String searchText): List<Attendance>
  }

  class BillController {
    + getBillsByStudentId(int studentId): List<Bill>
    + addBill(Bill bill): boolean
    + deleteBill(int billId): boolean
  }

  class CourseController {
    + getAllCourses(): List<Course>
    + addCourse(Course course): boolean
    + updateCourse(Course course): boolean
    + deleteCourse(int courseId): boolean
    + searchCourses(String searchText): List<Course>
  }

  class EnrollmentController {
    + getCoursesByStudentId(int studentId): List<Course>
    + enrollStudentInCourse(int studentId, int courseId): boolean
    + unenrollStudentFromCourse(int studentId, int courseId): boolean
  }

  class GradesController {
    + getAllGrades(): List<Grade>
    + addGrade(Grade grade): boolean
    + deleteGrade(int enrollmentId): boolean
    + updateGrade(Grade grade): boolean
    + searchGrades(String query): List<Grade>
  }

  class StudentController {
    + getAllStudents(): List<Student>
    + addStudent(Student student): boolean
    + deleteStudent(int studentId): boolean
    + updateStudent(Student student): boolean
    + getStudentById(int studentId): Student
  }
}







' Service 与 DAO 的依赖
StudentService --> StudentDAO
StudentService --> UserDAO
CourseService --> CourseDAO
GradesService --> GradesDAO
BillService --> BillDAO
AttendanceService --> AttendanceDAO

' Controller 与 Service 或 DAO 的依赖
LoginController --> UserDAO
AdminLoginController --> AttendanceController
AdminLoginController --> BillController
AdminLoginController --> CourseController
AdminLoginController --> GradesController
AdminLoginController --> StudentController
AttendanceController --> AttendanceService
BillController --> BillService
CourseController --> CourseService
EnrollmentController --> EnrollmentDAO
GradesController --> GradesService
StudentController --> StudentService


' DAO 继承 BaseDAO
UserDAO --|> BaseDAO
StudentDAO --|> BaseDAO
CourseDAO --|> BaseDAO
EnrollmentDAO --|> BaseDAO
GradesDAO --|> BaseDAO
BillDAO --|> BaseDAO
AttendanceDAO --|> BaseDAO
dao --> entity : Maps to Entity Layer


@enduml