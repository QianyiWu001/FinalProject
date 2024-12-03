package controller;

import dao.UserDAO;
import entity.User;
import DatabaseUtilities.Session;

public class LoginController {
    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    public User login(String username, String password) {
        User user = userDAO.validateLogin(username, password);
        if (user != null) {
      //if student, get student id to the session
            if ("ROLE_STUDENT".equals(user.getRole())) {
                int studentId = userDAO.getUserIdByUsername(user.getUsername());
                if (studentId != -1) {
                    Session.setStudentId(studentId);
                }
            }
            return user; 
        }
        return null;
    }
}