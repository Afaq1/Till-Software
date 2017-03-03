package stock;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Master extends JFrame implements ActionListener {

    JButton check = new JButton("Check Stock");
    JButton purchase = new JButton("Purchase Item");
    JButton stock = new JButton("Update Stock");
    JButton quit = new JButton("Exit");

    public static void main(String[] args) {
        Master master = new Master();
    }

    public Master() {
        setLayout(new FlowLayout());
        setBounds(100, 100, 600, 300);
        setTitle("Master");

        // close application only by clicking the quit button
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel top = new JPanel();
        JLabel label1 = new JLabel("Main Menu");
        label1.setFont(new Font("", Font.BOLD, 22));
        top.add(label1);
        JLabel label3 = new JLabel("Select an option by clicking one of the buttons below");
        top.add(label3);
        label3.setFont(new Font("", Font.BOLD, 18));
        top.setPreferredSize(new Dimension(500, 100));

        JPanel middle = new JPanel();
        middle.setPreferredSize(new Dimension(150, 400));
        middle.add(check);
        check.addActionListener(this);
       
        middle.add(purchase);
        purchase.addActionListener(this);
        middle.add(stock);
        stock.addActionListener(this);
        middle.add(quit);
        quit.addActionListener(this);
        
        add(top);
        add(middle);
        setResizable(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == check) {
            CheckStock checkStock = new CheckStock();
            setVisible(false);
        } else if (e.getSource() == purchase) {
            PurchaseItem1 purchaseItem = new PurchaseItem1();
            setVisible(false);
        } else if (e.getSource() == stock) {
            Login login = new Login();
            setVisible(false);
        } else if (e.getSource() == quit) {
            StockData.close();
            System.exit(0);
        }
    }
}
