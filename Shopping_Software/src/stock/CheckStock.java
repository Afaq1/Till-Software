package stock;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CheckStock extends JFrame implements ActionListener {

    JTextField stockNo = new JTextField(7);
    TextArea information = new TextArea(4, 50);
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    JButton check = new JButton("Check");
    JButton checkstock = new JButton("All Data");
    JLabel imagelabel = new JLabel(new ImageIcon("images/empty.JPG"));
    JButton back = new JButton("Main Menu");
    JLabel alllbl = new JLabel("To see all Data");
    JLabel stocklbl = new JLabel("Enter Stock Number:");
    Catalog cat;

    public CheckStock() {
        try {
            // make a border for form
            cat = new Catalog();
        } catch (SQLException ex) {
            Logger.getLogger(CheckStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLayout(new BorderLayout());
        //it is the boudries of the border layout 
        setBounds(100, 100, 450, 250);
        //make the title for form
        setTitle("Check Stock");
        //when close button is pressed it closed the form
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //it allow the form to open in the middle
        setLocationRelativeTo(null);
        alllbl.setBounds(40, 20, 150, 30);
        add(alllbl);
        checkstock.setBounds(180, 20, 110, 30);
        add(checkstock);
        checkstock.addActionListener(this);
        back.setBounds(300, 20, 110, 30);
        add(back);
        back.addActionListener(this);
        stocklbl.setBounds(40, 60, 150, 30);
        add(stocklbl);
        stockNo.setBounds(180, 60, 110, 30);
        add(stockNo);
        check.setBounds(300, 60, 110, 30);
        add(check);
        check.addActionListener(this);
        information.setBounds(40, 100, 250, 100);
        add(information);
        imagelabel.setBounds(300, 100, 100, 87);
        add(imagelabel);
        JPanel top = new JPanel();
        add("North", top);
        JPanel middle = new JPanel();
        add("Center", middle);
        setResizable(false);
        //it make the frame visible
        setVisible(true);
    }

    @Override
    //event diclare when user type anything in text field or press any button
    public void actionPerformed(ActionEvent e) {
        //if user type the stock number get the stock detail
        String key = stockNo.getText();
        //it will make the stock detail visible

        String name = StockData.getName(key);
        //if there is no stock number then it the below message
        if (e.getSource() == check) {
            if (name == null) {
                information.setText("No such item in stock");
                //this show how output will present
                // imageFilename = "images/empty.JPG";
                imagelabel.setIcon(new ImageIcon("images/empty.JPG"));
            } else {
                //first line show the name    
                information.setText(name);
                //second line show the price
                information.append("\nPrice: " + pounds.format(StockData.getPrice(key)));
                //third line will show how much the stock left
                information.append("\nNumber in stock: " + StockData.getQuantity(key));
                String imageFilename = "images\\" + key + ".JPG";
                File imagefile = new File(imageFilename);
                if (!imagefile.exists()) {
                    imageFilename = "images/empty.JPG";
                }
                imagelabel.setIcon(new ImageIcon(imageFilename));
            }
        }
        if (e.getSource() == checkstock) {
            cat.dispose();
            try {
                cat = new Catalog();
                cat.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(CheckStock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == back) {
            Master master = new Master();
            setVisible(false);
            cat.dispose();
        }
    }

    public static void main(String[] args) {
        CheckStock checkStock = new CheckStock();
    }
}
