package BasicLogin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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

            // 连一下数据库**********
            if (!username.isEmpty() && !password.isEmpty() && !userType.equals("-")) {
                int loginResult = loginDatabase.checkLogin(username, password, userType);
                
                // 根据返回值判断登录是否成功
                if (loginResult != -1) {
                    // 根据 userType 打开不同的页面
                    if (userType.equals("ROLE_ADMIN")) {
                        new AdminLoginPage(); // 打开管理员页面
                        basicLoginPage.dispose(); // 关闭当前页面
                    } else if (userType.equals("ROLE_STUDENT")) {
                        new StudentLoginPage(); // 打开学生页面
                        basicLoginPage.dispose(); // 关闭当前页面
                    }
                } else {
                    // 登录失败的错误提示
                    // JOptionPane.showMessageDialog(basicLoginPage, "Invalid Login Information, Please Check and Try Again.");
                    // return;
                }
            }
            
                // 下面这些就是随便试试
                if (username.equals("1") && password.equals("1") && userType.equals("Admin")) {
                    new AdminLoginPage();
                    basicLoginPage.dispose();
                } else if (username.equals("2") && password.equals("2") && userType.equals("Student")) {
                    new StudentLoginPage();
                    basicLoginPage.dispose();
                } else {
                    JOptionPane.showMessageDialog(basicLoginPage, "Invalid Login Information, Please Check and Try Again.");
                    return;
                }
            }
        }
    }