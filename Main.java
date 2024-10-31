import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JTextPane welcomeToMthoskoAgriculturalTextPane;
    private JButton btnLogin;
    private JButton btnAddUser;

    public Main(){

        setTitle("MTHOSKO AGRI MANAGEMENT");
        setContentPane(MainPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(750, 450));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var l = new Login();
                setVisible(false);
            }
        });
        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var r = new Register();
                setVisible(false);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        var main = new Main();
    }
}
