@startuml

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
      + handleBillManagement()
      + handleEnrollmentManagement()
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

    class AdminBillManagementPage {
      + AdminBillManagementPage()
      + refreshTable()
      + handleAddBill()
      + handleDeleteBill()
      + handleUpdateBill()
      + handleSearchBill()
    }

    class AddBillPage {
      + AddBillPage(JFrame parent)
      + handleAddBill()
    }

    class AdminEnrollmentManagementPage {
      + AdminEnrollmentManagementPage()
      + refreshTable()
      + handleAddEnrollment()
      + handleDeleteEnrollment()
      + handleUpdateEnrollment()
      + handleSearchEnrollment()
    }

    class AddEnrollmentPage {
      + AddEnrollmentPage(JFrame parent)
      + handleAddEnrollment()
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

@enduml