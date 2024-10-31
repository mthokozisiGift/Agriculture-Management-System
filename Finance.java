import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class Finance extends JFrame {
    private JPanel FinancePanel;
    private JTextField fPotatos;
    private JTextField fSpinach;
    private JTextField fTomatos;
    private JTextField fGreenBeans;
    private JTextField fCabbage;
    private JButton btnPotatos;
    private JButton exitButton;
    private JLabel txtPotato;
    private JLabel txtSpinach;
    private JLabel txtTomatos;
    private JLabel txtGreenBeans;
    private JLabel txtCabbage;
    private JLabel txtAChicken;
    private JLabel txtDChicken;
    private JLabel txtGoat;
    private JLabel txtCow;
    private JLabel txtTotal;
    private JTextField fAChicken;
    private JTextField fDChicken;
    private JTextField fGoat;
    private JTextField fCow;
    private JButton btnSpinach;
    private JButton btnTomato;
    private JButton btnGreenBeans;
    private JButton btnCabbage;
    private JButton btnAChicken;
    private JButton btnDChicken;
    private JButton btnGoat;
    private JButton btnCow;
    private JButton btnSavePriceTotal;
    public int tot;
    public int potato;
    public int spinach;
    public int tomato;
    public int gBeans;
    public int cabbage;
    public int AChicken;
    public int DChicken;
    public int goat;
    public int cow;


    public Finance(String user, String pass){
        setTitle("SET PRICE PAGE");
        setContentPane(FinancePanel);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        fPotatos.setText("0");
        fTomatos.setText("0");
        fSpinach.setText("0");
        fGreenBeans.setText("0");
        fCabbage.setText("0");
        fAChicken.setText("0");
        fDChicken.setText("0");
        fGoat.setText("0");
        fCow.setText("0");
        btnPotatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                potato = Integer.valueOf(fPotatos.getText());
                String pt = txtPotato.getText();
                if (pt.equals("R0.00")) {
                    txtPotato.setText("R" + potato + ".00");
                    tot = tot + potato;
                    txtTotal.setText("R" + tot);
                    fPotatos.setText("0");
                    fPotatos.setEnabled(false);
                }
            }
        });
        btnSpinach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinach = Integer.parseInt(fSpinach.getText());
                txtSpinach.setText("R"+spinach+".00");
                tot = tot + spinach;
                txtTotal.setText("R"+tot);
                fSpinach.setEnabled(false);
                btnSpinach.setEnabled(false);

            }
        });
        btnTomato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomato = Integer.parseInt(fTomatos.getText());
                txtTomatos.setText("R"+tomato+".00");
                tot = tot + tomato;
                txtTotal.setText("R"+tot);
                fTomatos.setEnabled(false);
                btnTomato.setEnabled(false);
            }
        });
        btnGreenBeans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gBeans = Integer.parseInt(fGreenBeans.getText());
//                String gb = txtGreenBeans.getText();
                txtGreenBeans.setText("R"+gBeans+".00");
                tot = tot + gBeans;
                txtTotal.setText("R"+tot);
                fGreenBeans.setEnabled(false);
                btnGreenBeans.setEnabled(false);
            }
        });
        btnCabbage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cabbage =  Integer.parseInt(fCabbage.getText());
                txtCabbage.setText("R"+cabbage+".00");
                tot = tot + cabbage;
                txtTotal.setText("R"+tot);
                fCabbage.setEnabled(false);
                btnCabbage.setEnabled(false);
            }
        });
        btnAChicken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AChicken = Integer.parseInt(fAChicken.getText());
                txtAChicken.setText("R"+AChicken+".00");
                tot = tot + AChicken;
                txtTotal.setText("R"+tot);
                fAChicken.setEnabled(false);
                btnAChicken.setEnabled(false);
            }
        });
        btnDChicken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DChicken = Integer.parseInt(fDChicken.getText());
                txtDChicken.setText("R"+DChicken+".00");
                tot = tot + DChicken;
                txtTotal.setText("R"+tot);
                fDChicken.setEditable(false);
                btnDChicken.setEnabled(false);
            }
        });
        btnGoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goat = Integer.parseInt(fGoat.getText());
                txtGoat.setText("R"+goat+".00");
                tot = tot + goat;
                txtTotal.setText("R"+tot);
                fGoat.setEditable(false);
                btnGoat.setEnabled(false);
            }
        });
        btnCow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cow = Integer.parseInt(fCow.getText());
                txtCow.setText("R"+cow+".00");
                tot = tot + cow;
                txtTotal.setText("R"+tot);
                fCow.setEditable(false);
                btnCow.setEnabled(false);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        btnSavePriceTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToDataBase();
            }
        });
        setVisible(true);
    }

    private void saveToDataBase() {
        String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
        String cUsername="root";
        String cPassword="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, cUsername, cPassword);
            String qry = "INSERT INTO daily_prices(date_of_price, potatos,spinach,tomatos,green_beans,cabbage,aChicken,dChicken,goat,cow,total)"+
                    "values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(qry);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(1,timestamp);
            pstmt.setInt(2, potato);
            pstmt.setInt(3, spinach);
            pstmt.setInt(4,tomato);
            pstmt.setInt(5,gBeans);
            pstmt.setInt(6,cabbage);
            pstmt.setInt(7,AChicken);
            pstmt.setInt(8,DChicken);
            pstmt.setInt(9,goat);
            pstmt.setInt(10,cow);
            pstmt.setInt(11, tot);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this,
                    "Done!!!",
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
