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

        if (buttonText.equals("Login")) {
            String username = basicLoginPage.getUsernameField().getText();
            char[] charPassword = basicLoginPage.getPasswordField().getPassword();
            String password = new String(charPassword);

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

            // 用户名、密码这里需要用数据库吗？**********
            if (!username.isEmpty() && !password.isEmpty()) {
                if (username.equals("1") && password.equals("1")) {
                    //管理员界面
                    new AdminLoginPage();
                    basicLoginPage.dispose();
                    
                } else if (username.equals("2") && password.equals("2")) {
                    new StudentLoginPage();
                    basicLoginPage.dispose();
                }
            }
        }
    }
    
}
