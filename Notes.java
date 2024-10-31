import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Notes extends JFrame {
    private JTextArea taNew;
    private JTextPane taOld;
    private JButton saveNoteButton;
    private JLabel lblEmployeeNum;
    private JPanel NotePanel;
    private JButton exitButton;
    public int empNo;
    public String noteText;


    public  Notes(String employee_num){
        setTitle("Note PAGE");
        setContentPane(NotePanel);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        lblEmployeeNum.setText(employee_num);
        empNo = Integer.valueOf(lblEmployeeNum.getText());
        saveNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNote();
                taOld.setText(noteText);
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

    private void saveNote() {
        noteText = taNew.getText();
        String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
        String cUsername="root";
        String cPassword="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, cUsername, cPassword);
            String q = "Insert into notes(employee_num,notes_desc) Values('"+empNo+"','"+noteText+"')";
            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.executeUpdate();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    String.valueOf(e),
                    "try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
