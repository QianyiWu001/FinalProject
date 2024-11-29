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
            // 如果用户是学生，获取学生 ID 并存储到会话
            if ("ROLE_STUDENT".equals(user.getRole())) {
                int studentId = userDAO.getUserIdByUsername(user.getUsername());
                if (studentId != -1) {
                    Session.setStudentId(studentId);
                }
            }
            return user; // 返回完整的用户对象
        }
        return null;
    }
}