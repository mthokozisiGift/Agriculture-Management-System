import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Register extends JFrame{
    private JPanel RegisterPane;
    private JTextField tUsername;
    private JTextField tName;
    private JTextField tSurname;
    private JTextField tEmpNo;
    private JComboBox tEmpSec;
    private JButton btnRegister;
    private JButton btnCancel;
    private JTextField tEmail;
    private JTextField tPhone;
    private JPasswordField tpassword;
    private JPasswordField tpassword2;
    private JRadioButton rMale;
    private JRadioButton rFemale;

    public String username;
    public String name;
    public String surname;
    public String email;
    public String phone;
    public String employee_no;
    public String employment_sec;
    public String password;
    public String password2;
    public String gender;

    public Register(){
        setTitle("LOGIN PAGE");
        setContentPane(RegisterPane);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = tUsername.getText();
                name = tName.getText();
                surname = tSurname.getText();
                email = tEmail.getText();
                phone = tPhone.getText();
                employee_no = tEmpNo.getText();
                employment_sec = String.valueOf(tEmpSec.getSelectedItem());
                password = String.valueOf(tpassword.getPassword());
                password2 = String.valueOf(tpassword2.getPassword());
                if(rMale.isSelected() == true) {
                    gender = "Male";

                } else if (rFemale.isSelected() == true){
                    gender = "Female";
                }

                Adduser();

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var main = new Main();
                setVisible(false);
            }
        });
    }

    private void Adduser() {


        String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
        String cUsername="root";
        String cPassword="";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,cUsername,cPassword);
            String q = "Insert into employees_info(username,name,surname,email,phone,employee_no,employment_sector,gender,password) Values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(q);

            if (!password.equals(password2)){
                JOptionPane.showMessageDialog(this,
                        "Passwords do not match",
                        "try again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                pstmt.setString(1,username);
                pstmt.setString(2,name);
                pstmt.setString(3,surname);
                pstmt.setString(4,email);
                pstmt.setString(5,phone);
                pstmt.setString(6,employee_no);
                pstmt.setString(7,employment_sec);
                pstmt.setString(8,gender);
                pstmt.setString(9,password);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this,
                        "User registered",
                        "successful",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    String.valueOf(e),
                    "try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
