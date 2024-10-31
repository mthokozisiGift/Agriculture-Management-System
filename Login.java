import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JPanel LoginPane;
    private JTextField fUsername;
    private JTextField fPassword;
    private JButton btnProceed;
    private JButton btnBack;

    public String dUser;
    public String dName;
    public String dSurname;
    public String dEmail;
    public String dPhone;
    public String dpass;
    public String dGender;
    public String dEmp_no;
    public String dEmp_Sec;

    public Login(){
        setTitle("LOGIN PAGE");
        setContentPane(LoginPane);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btnProceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fUsername.getText();
                String password = fPassword.getText();
                UserLogin(username,password);
                setVisible(false);
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var main = new Main();
                setVisible(false);
            }
        });

    }

    private void UserLogin(String username, String password) {

        if (username.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter username",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else if (password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter password",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
            String cUsername="root";
            String cPassword="";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(url, cUsername, cPassword);
                Statement statement = connection.createStatement();
                String sql = "Select * from employees_info where username = ? and password = ?";
                PreparedStatement prepStatement = connection.prepareStatement(sql);
                prepStatement.setString(1, username);
                prepStatement.setString(2, password);
                ResultSet resultSet = prepStatement.executeQuery();

                if (resultSet.next()) {
                    dUser = resultSet.getString("username");
                    dName = resultSet.getString("name");
                    dSurname = resultSet.getString("surname");
                    dEmail = resultSet.getString("email");
                    dPhone = resultSet.getString("phone");
                    dGender = resultSet.getString("gender");
                    dEmp_Sec = resultSet.getString("employment_sector");
                    dEmp_no = resultSet.getString("employee_no");
                    dpass = resultSet.getString("password");

                    if (!dUser.equals(username)) {
                        JOptionPane.showMessageDialog(this,
                                "Username not found",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (!dpass.equals(password)) {
                        JOptionPane.showMessageDialog(this,
                                "Invalid password",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (!dUser.equals(username) && !dpass.equals(password)){
                        JOptionPane.showMessageDialog(this,
                                "User not registered",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        //sumthing else like a home page
                        setVisible(false);
                        var home = new Home(username,password);
                        home.dspUsername.setText(dUser);
                        home.dspName.setText(dName);
                        home.dspSurname.setText(dSurname);
                        home.dspEmail.setText(dEmail);
                        home.dspPhone.setText(dPhone);
                        home.dspGender.setText(dGender);
                        home.dspEmpNo.setText(dEmp_no);
                        home.dspEmpSec.setText(dEmp_Sec);
                        home.dspPass.equals(dpass);
                        home.dspEmpSts.setText("Active");

                    }
                }

                }  catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
