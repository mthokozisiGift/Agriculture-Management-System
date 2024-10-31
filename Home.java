import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JPanel HomePanel;
    private JButton editProfileButton;
    private JButton financeButton;
    private JButton stockButton;
    private JButton notesButton;
    private JButton suggestionsButton;
    private JButton applyForLeaveButton;
    private JButton lodgeAComplainButton;
    private JButton exitButton;
    public JLabel dspName;
    public JLabel dspSurname;
    public JLabel dspUsername;
    public JLabel dspEmpSec;
    public JLabel dspGender;
    public JLabel dspEmail;
    public JLabel dspEmpNo;
    public JLabel dspPhone;
    public JLabel dspEmpSts;
    private JButton browsePricesButton;
    public String dspPass;

    public Home(String username, String password){
        setTitle("Home PAGE");
        setContentPane(HomePanel);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        dspEmpSts.setText("Active");
        setVisible(true);
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var ed = new EditProfile(dspUsername.getText(),dspName.getText(),dspSurname.getText(),dspEmail.getText(),dspPhone.getText(),dspPass);
//                setVisible(false);
            }
        });
        financeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var f = new Finance(dspUsername.getText(),dspPass);
//                setVisible(false);
            }
        });
        notesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var n = new Notes(dspEmpNo.getText());
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var l = new Login();
                setVisible(false);
            }
        });
        browsePricesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var vp = new BrowsePrices();
            }
        });
        applyForLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var apl = new ApplyForLeave(dspUsername.getText());
            }
        });
        lodgeAComplainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var com = new LodgeComplain(dspUsername.getText());
            }
        });
    }
}
