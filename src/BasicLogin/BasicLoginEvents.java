package BasicLogin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import AdminJFrame.AdminLoginPage;
import StudentJFrame.StudentLoginPage;

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

            if (username.isEmpty()) {
                basicLoginPage.getUsernameErrorField().setText("* Required");
                basicLoginPage.getUsernameErrorField().setForeground(Color.RED);
            }else {
                basicLoginPage.getUsernameErrorField().setText(" ");
            }

            if (password.isEmpty()) {
                basicLoginPage.getPasswordErrorField().setText("* Required");
                basicLoginPage.getPasswordErrorField().setForeground(Color.RED);
            }else {
                basicLoginPage.getPasswordErrorField().setText(" ");
            }

            if (userType.equals("-")) {
                basicLoginPage.getUserTypeErrorField().setText("* Required");
                basicLoginPage.getUserTypeErrorField().setForeground(Color.RED);
            }else {
                basicLoginPage.getUserTypeErrorField().setText(" ");
            }

            // 连一下数据库**********
            if (!username.isEmpty() && !password.isEmpty() && !userType.equals("-----Select-----")) {
                /*boolean isLoginValid = loginDatabase.checkLogin(username, password, userType);
                if (!isLoginValid) {
                    if (userType.equals("Admin")) {
                        new AdminLoginPage();
                        basicLoginPage.dispose();
                    } else if (userType.equals("Student")) {
                        new StudentLoginPage();
                        basicLoginPage.dispose();
                    } else {
                        basicLoginPage.getUserLoginErrorField().setText("Invalid Login Information, Please Check and Try Again.");
                        basicLoginPage.getUserLoginErrorField().setForeground(Color.RED);
                    }
                } 
                */
                
                // 下面这些就是随便试试
                if (username.equals("1") && password.equals("1") && userType.equals("Admin")) {
                    new AdminLoginPage();
                    basicLoginPage.dispose();
                    
                } else if (username.equals("2") && password.equals("2") && userType.equals("Student")) {
                    new StudentLoginPage();
                    basicLoginPage.dispose();
                } else {
                    basicLoginPage.getUserLoginErrorField().setText("Invalid Login Information, Please Check and Try Again.");
                    basicLoginPage.getUserLoginErrorField().setForeground(Color.RED);
                }
            }
        }
    }
    
}
