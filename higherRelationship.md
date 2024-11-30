@startuml
left to right direction
' 定义文件夹结构
package "Entity" as entity {
  class User
  class Student
  class Course
  class Enrollment
  class Grade
  class Bill
  class Attendance
}

package "DAO" as dao {
  class UserDAO
  class StudentDAO
  class CourseDAO
  class EnrollmentDAO
  class GradesDAO
  class BillDAO
  class AttendanceDAO
}

package "Service" as service {
  class StudentService
  class CourseService
  class GradesService
  class BillService
  class AttendanceService
}

package "Controller" as controller {
  class AdminLoginController
  class AttendanceController
  class BillController
  class CourseController
  class EnrollmentController
  class GradesController
  class LoginController
  class StudentController
}

package "View" as view {

      class BasicLoginPage {

    + BasicLoginPage()
    + actionPerformed(ActionEvent e)
  }

 package Admin {



    class AdminLoginPage {
      + AdminLoginPage()
      + handleCourseManagement()
      + handleStudentManagement()
      + handleAttendanceManagement()
      + handleGradesManagement()
      + handleBack()
      + handleExit()
    }

    class AdminAttendanceManagementPage {
      + AdminAttendanceManagementPage()
      + refreshTable()
      + handleAddAttendance()
      + handleDeleteAttendance()
    }

    class AddAttendancePage {
      + AddAttendancePage(JFrame parent)
      + handleAddAttendance()
    }
    class AdminCoursesManagementPage {
      + AdminCoursesManagementPage()
      + refreshTable()
      + handleAddCourse()
      + handleDeleteCourse()
    }
    class AddCoursePage {
      + AddCoursePage(JFrame parent)
      + handleAddCourse()
    }


    class AdminGradesManagementPage {
      + AdminGradesManagementPage()
      + refreshTable()
      + handleAddGrade()
      + handleDeleteGrade()
      + handleUpdateGrade()
      + handleSearchGrade()
    }

    class AddGradePage {
      + AddGradePage(JFrame parent)
      + handleAddGrade()
    }

        class AdminStudentsManagementPage {
      + AdminStudentsManagementPage()
      + refreshTable()
      + handleAddStudent()
      + handleDeleteStudent()
      + handleUpdateStudent()
      + handleSearchStudent()
    }

    class AddStudentPage {
      + AddStudentPage(JFrame parent)
      + handleAddStudent()
    }



  }
  package Student {
  class StudentProfilePage {
      
      + StudentProfilePage(int studentId)
      + actionPerformed(ActionEvent e)
    }

    class StudentLoginPage {
     
      + StudentLoginPage()
      + actionPerformed(ActionEvent e)
    }

    class StudentGradesPage {
      
      - GradesDAO gradesDAO
      + StudentGradesPage()
      + refreshTable()
      + handleBack()
    }

    class StudentCoursesListPage {
     
      - EnrollmentController enrollmentController
      + StudentCoursesListPage()
      + refreshTable()
      + handleBack()
    }

    class StudentBillPage {
      
      - BillController billController
      + StudentBillPage()
      + refreshTable()
      + handleBack()
    }

    class StudentAttendancePage {
      
      - AttendanceDAO attendanceDAO
      + StudentAttendancePage()
      + refreshTable()
      + handleBack()
    }
  }

}

' 定义流程关系
dao --> entity : Maps to Entity
service --> dao : Handles Data Operations
controller --> service : Calls Business Logic
view --> controller : Interacts with Controller

@enduml