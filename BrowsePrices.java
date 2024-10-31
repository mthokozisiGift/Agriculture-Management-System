import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BrowsePrices extends JFrame {
    private JPanel BrowsePricesPanel;
    private JButton exitButton;
    private JLabel lblTomatos;
    private JLabel lblSpinach;
    private JLabel lblPotatos;
    private JLabel lblGreenBeans;
    private JLabel lblCabbage;
    private JLabel lblAChicken;
    private JLabel lblDChicken;
    private JLabel lblGoat;
    private JLabel lblCow;
    public int p;
    public int t;
    public int gb;
    public int s;
    public int c;
    public int ac;
    public int dc;
    public int g;
    public int co;
    public String p1;

    public BrowsePrices(){
        setTitle("LOGIN PAGE");
        setContentPane(BrowsePricesPanel);
        setMinimumSize(new Dimension(750, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        UploadPrices();

        lblPotatos.setText("R"+p1+".00");
        lblTomatos.setText("R"+t+".00");
        lblSpinach.setText("R"+s+".00");
        lblGreenBeans.setText("R"+gb+".00");
        lblCabbage.setText("R"+c+".00");
        lblAChicken.setText("R"+ac+".00");
        lblDChicken.setText("R"+dc+".00");
        lblGoat.setText("R"+g+".00");
        lblCow.setText("R"+co+".00");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void UploadPrices() {
        String url = "jdbc:mysql://localhost:3306/mthosko_agri_management";
        String cUsername="root";
        String cPassword="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, cUsername, cPassword);
            String q = "Select * From daily_prices Order BY date_of_price DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(q);
            if (resultSet.next()){
                p = resultSet.getInt("potatos");
                t = resultSet.getInt("tomatos");
                gb = resultSet.getInt("green_beans");
                s = resultSet.getInt("spinach");
                c = resultSet.getInt("cabbage");
                ac = resultSet.getInt("aChicken");
                dc = resultSet.getInt("dChicken");
                g = resultSet.getInt("goat");
                co = resultSet.getInt("cow");
                p1 = resultSet.getString("potatos");


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
