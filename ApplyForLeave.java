import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class ApplyForLeave extends JFrame {
    private JPanel ApplyForLeavePanel;
    private JButton submitRequestButton;
    private JButton exitButton;
    private JComboBox comLeaveType;
    private JTextField tNumDays;
    private JTextField tFrom;
    private JTextField tTo;
    private JTextField tDateBackAtWork;
    private JTextArea taReason;
    private JLabel lblUsername;
    public String user;
    public String ltype;
    public String numDays;
    public String dFrom;
    public String dTo;
    public  String dReturn;
    public String reason;

    public ApplyForLeave(String username){
        setTitle("APPLY FOR LEAVE PAGE");
        setContentPane(ApplyForLeavePanel);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        lblUsername.setText(username);
        user = username;

        submitRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddToDatabase();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void AddToDatabase() {
        ltype = String.valueOf(comLeaveType.getSelectedItem());
        numDays = tNumDays.getText();
        dFrom = tFrom.getText();
        dTo = tTo.getText();
        dReturn = tDateBackAtWork.getText();
        reason = taReason.getText();

        String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
        String cUsername="root";
        String cPassword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, cUsername, cPassword);
            String qry = "INSERT INTO leave_application(username,leave_type,num_days,date_from,date_to,date_return,reason_for_leave)"+
                    "values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(qry);
            pstmt.setString(1,user);
            pstmt.setString(2,ltype);
            pstmt.setString(3,numDays);
            pstmt.setString(4,dFrom);
            pstmt.setString(5,dTo);
            pstmt.setString(6,dReturn);
            pstmt.setString(7,reason);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this,
                    "Application Sent",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    String.valueOf(e),
                    "try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
