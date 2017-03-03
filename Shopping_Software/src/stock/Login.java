package stock;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login extends JFrame implements ActionListener {
    JTextField logintext = new JTextField(12);
    JPasswordField passwordtext = new JPasswordField(12);
    JButton okbutton = new JButton("  Login  ");
    JButton mainmenu = new JButton("Main Menu");
    JLabel username = new JLabel("User Name");
    JLabel password = new JLabel("Password");
    JLabel loginlbl = new JLabel("Login");
    public Login() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 330, 280);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        loginlbl.setBounds(140, 20, 110, 30);
        loginlbl.setFont(new Font("", Font.BOLD, 18));
        username.setBounds(30, 70, 110, 30);
        logintext.setBounds(130, 70, 150, 30);
        password.setBounds(30, 110, 110, 30);
        passwordtext.setBounds(130, 110, 150, 30);
        okbutton.setBounds(30, 170, 110, 30);
        mainmenu.setBounds(170, 170, 110, 30);
        add(loginlbl);
        add(username);
        add(logintext);
        add(password);
        add(passwordtext);
        add(okbutton);
        add(mainmenu);
        okbutton.addActionListener(this);
        mainmenu.addActionListener(this);

        JPanel top = new JPanel();
        add("North", top);
        JPanel middle = new JPanel();
        add("Center", middle);

        setResizable(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = logintext.getText();
        String pass = passwordtext.getText();

        if (e.getSource() == okbutton) {
                if ("admin".equals(user) && "admin".equals(pass)) {
                UpdateStock UpdateStock = new UpdateStock();
                Login.this.dispose();
            } 
                else {
                
                JOptionPane.showMessageDialog(null, " invalud UserName or Password");
                logintext.setText("");
                passwordtext.setText("");
            } 
        }
        if (e.getSource() == mainmenu) {
            Master master = new Master();
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        Login login = new Login();
    }
}
