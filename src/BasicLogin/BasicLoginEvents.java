package BasicLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminJFrame.AdminLoginPage;
import StudentJFrame.StudentLoginPage;
/*
 * Admin Login Information:
 *     Username: admin1
 *     Password: 123
 *     User Type: Admin
 * 
 * Student Login Information:
 *     Username: student1
 *     Password: 123
 *     User Type: Student
 */
public class BasicLoginEvents implements ActionListener {
    private BasicLoginPage basicLoginPage;
    
    public BasicLoginEvents(BasicLoginPage basicLoginPage) {
        this.basicLoginPage = basicLoginPage;
    }
   
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            System.exit(0);
        }
        
        if (buttonText.equals("Login")) {
            String username = basicLoginPage.getUsernameField().getText();
            char[] charPassword = basicLoginPage.getPasswordField().getPassword();
            String password = new String(charPassword);
            String userType = (String) basicLoginPage.getUserTypeComboBox().getSelectedItem();

            // Validate if the input fields are empty.
            if (username.isEmpty() && password.isEmpty() && userType.equals("-")) {
                JOptionPane.showMessageDialog(basicLoginPage, "Please enter your login information.");
                return;
            } else if (username.isEmpty()) {
                JOptionPane.showMessageDialog(basicLoginPage, "Please enter your username.");
                return;
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(basicLoginPage, "Please enter your password.");
                return;
            } else if (userType.equals("-")) {
                JOptionPane.showMessageDialog(basicLoginPage, "Please select your user type.");
                return;
            }

            // Map userType to the corresponding field in the database.
            if (userType.equals("Admin")) {
                userType = "ROLE_ADMIN";
            } else if (userType.equals("Student")) {
                userType = "ROLE_STUDENT";
            } else {
                JOptionPane.showMessageDialog(basicLoginPage, "Invalid User Type, Please select a valid user type.");
                return;
            }
            // Database validation.
            int loginResult = loginDatabase.checkLogin(username, password, userType);
                // Determine login success based on the return value.
                if (loginResult != -1) {
                    // Open different pages based on userType.
                    if (userType.equals("ROLE_ADMIN")) {
                        new AdminLoginPage();
                        basicLoginPage.dispose();
                    } else if (userType.equals("ROLE_STUDENT")) {
                        new StudentLoginPage();
                        basicLoginPage.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(basicLoginPage, "Invalid Login Information, Please Check and Try Again.");
                }
            }
        }
    }