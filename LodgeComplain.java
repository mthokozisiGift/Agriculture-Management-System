import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class LodgeComplain extends JFrame {
    private JButton submitButton;
    private JButton cancelButton;
    private JComboBox comLevel;
    private JTextField tType;
    private JTextArea taDescription;
    private JLabel txtusername;
    private JPanel LodgeComplainPanel;
    public String level;
    public String type;
    public String description;
    public String user;

    public LodgeComplain(String username) {
        setTitle("LODGE COMPLAINT PAGE");
        setContentPane(LodgeComplainPanel);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        user = username;
        txtusername.setText(user);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubmitComplaint();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    } //The System takes time to load, which causes the staff to miss deadlines

    private void SubmitComplaint() {

        level = String.valueOf(comLevel.getSelectedItem());
        description = taDescription.getText();
        type = tType.getText();

        String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
        String cUsername="root";
        String cPassword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, cUsername, cPassword);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String qry = "INSERT INTO complains(username,level,comp_type,comp_date,comp_description) " +
                    "values('"+user+"','"+level+"','"+type+"','"+timestamp+"','"+description+"')";
            PreparedStatement pstmt = connection.prepareStatement(qry);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this,
                    "Complaint sent",
                    "Successful",
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
