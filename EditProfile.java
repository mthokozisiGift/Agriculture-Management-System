import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditProfile extends JFrame {
    private JPanel EditPanel;
    private JTextField eName;
    private JTextField eSurname;
    private JTextField eEmail;
    private JTextField ePhone;
    private JRadioButton rYes;
    private JRadioButton rNo;
    private JPasswordField ePassword;
    private JPasswordField ePassword2;
    private JButton btnSave;
    private JButton btnCancel;
    private JLabel eUser;
    public String username;
    public String name;
    public String surname;
    public String email;
    public String phone;
    public String empSec;
    public String passcode;
    public String passcode2;
    public String decision;
    public String u;


    public EditProfile(String username,String name,String surname,String email,String phone,String password){
        setTitle("EDIT PAGE");
        setContentPane(EditPanel);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        eUser.setText(username);
        eName.setText(name);
        eSurname.setText(surname);
        eEmail.setText(email);
        ePhone.setText(phone);
        ePassword.setText(password);
        ePassword2.setText(" ");

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Edit_data();
            }
        });

        rYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ePassword.setEnabled(true);
                ePassword2.setEnabled(true);
            }
        });

        rNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ePassword.setEnabled(false);
                ePassword2.setEnabled(false);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void Edit_data() {

        name = eName.getText();
        surname = eSurname.getText();
        email = eEmail.getText();
        phone = ePhone.getText();
        passcode = String.valueOf(ePassword.getPassword());
        passcode2 = String.valueOf(ePassword2.getPassword());

        String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
        String cUsername="root";
        String cPassword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, cUsername, cPassword);

            if (rYes.isSelected()) {
                u = eUser.getText();
                String qy = "Update employees_info set name = '"+name+"',surname = '"+surname+"',email = '"+email+"',phone = '"+phone+"',password = '"+passcode+"'" +
                        " where username = '"+u+"'";
                PreparedStatement pstmt = connection.prepareStatement(qy);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this,
                        "---Record updated---",
                        "success",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (rNo.isSelected()) {
                u = eUser.getText();
                String qn = "Update employees_info set name = '"+name+"',surname = '"+surname+"',email = '"+email+"',phone = '"+phone+"'" +
                        " where username = '"+u+"'";
                PreparedStatement pstmt = connection.prepareStatement(qn);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this,
                        "Record updated !!!",
                        "success",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if(!rYes.isSelected() && !rNo.isSelected()){
                JOptionPane.showMessageDialog(this,
                        "Please select option if whether you want password edited or not",
                        "try again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!ePassword.equals(ePassword2)){
                JOptionPane.showMessageDialog(this,
                        "Passwords don't match",
                        "try again",
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
