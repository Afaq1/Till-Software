package stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.event.ActionEvent;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

public class PurchaseItem1 extends JFrame implements ActionListener {

    JTextField calculatetxt = new JTextField(16);
    JTextField stockNo = new JTextField(16);
    TextArea information = new TextArea(3, 30);
    TextArea purchasetxt = new TextArea(3, 30);
    JButton purchasebtn = new JButton("  Purchase   ");
    JButton backbtn = new JButton("Main Menu");
    JButton addbtn = new JButton("Add to Basket");
    JButton checkbtn = new JButton("Check");
    JButton resetbtn = new JButton("Reset");
    JLabel informationlbl = new JLabel("Product Information");
    JLabel stocklbl = new JLabel("Stock number: ");
    JLabel quantitylbl = new JLabel("Quantity: ");
    JLabel calculatelbl = new JLabel("Calculate");
    JLabel orlbl = new JLabel("Or");
    JLabel mainlbl = new JLabel("Purchase");
    JLabel sublbl = new JLabel("Check Stock");
    JLabel sub2lbl = new JLabel("Basket");
    JComboBox quantitycombo = new JComboBox();

    boolean initialStockBool = true;
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");

    double totalcost = 0.0;

    DecimalFormat twodigits = new DecimalFormat("00");
    ArrayList<String> itembasket = new ArrayList<>();
    ArrayList<String> quantitybasket = new ArrayList<>();

    public PurchaseItem1() {

        setLayout(new BorderLayout());
        setBounds(100, 100, 670, 440);
        setTitle("Purchase");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainlbl.setBounds(250, 20, 110, 30);
        mainlbl.setFont(new Font("", Font.BOLD, 18));
        sublbl.setBounds(20, 60, 110, 30);
        sublbl.setFont(new Font("", Font.BOLD, 14));
        stocklbl.setBounds(20, 100, 120, 30);
        stockNo.setBounds(110, 100, 90, 30);
        checkbtn.setBounds(210, 100, 90, 30);
        informationlbl.setBounds(110, 140, 120, 30);
        information.setBounds(20, 170, 280, 90);
        quantitylbl.setBounds(20, 280, 120, 30);
        quantitycombo.setBounds(80, 280, 60, 30);
        calculatelbl.setBounds(150, 280, 60, 30);
        calculatetxt.setBounds(210, 280, 90, 30);
        backbtn.setBounds(20, 320, 110, 30);
        addbtn.setBounds(185, 320, 115, 30);
        sub2lbl.setBounds(380, 60, 110, 30);
        sub2lbl.setFont(new Font("", Font.BOLD, 14));
        purchasetxt.setBounds(380, 100, 240, 200);
        purchasebtn.setBounds(380, 320, 110, 30);
        resetbtn.setBounds(516, 320, 110, 30);

        resetbtn.addActionListener(this);
        backbtn.addActionListener(this);
        quantitycombo.addActionListener(this);
        checkbtn.addActionListener(this);
        addbtn.addActionListener(this);
        purchasebtn.addActionListener(this);

        calculatetxt.setEditable(false);
        purchasetxt.setEditable(false);
        purchasetxt.setBackground(Color.white);
        information.setEditable(false);
        information.setBackground(Color.white);

        add(resetbtn);
        add(stocklbl);
        add(stockNo);
        add(information);
        add(quantitycombo);
        add(checkbtn);
        add(sublbl);
        add(calculatelbl);
        add(informationlbl);
        add(calculatetxt);
        add(backbtn);
        add(quantitylbl);
        add(addbtn);
        add(sub2lbl);
        add(purchasetxt);
        add(purchasebtn);
        add(mainlbl);

        JPanel top = new JPanel();
        add("North", top);
        JPanel middle = new JPanel();
        add("Center", middle);
        setResizable(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        GregorianCalendar now = new GregorianCalendar();
        int thisminute = now.get(Calendar.MINUTE);
        int thishour = now.get(Calendar.HOUR);
        int thisday = now.get(Calendar.DATE);
        int thismonth = now.get(Calendar.MONTH) + 1;
        int thisyear = now.get(Calendar.YEAR);

        String key = stockNo.getText();
        String name = StockData.getName(key);

        int Value = quantitycombo.getSelectedIndex();

        double price = StockData.getPrice(key);

        Double Total = price * Value;
        String message = ("" + twodigits.format(thishour) + ":" + twodigits.format(thisminute) + " - " + twodigits.format(thisday) + "/" + twodigits.format(thismonth) + "/" + twodigits.format(thisyear));
        if (name == null) {
            information.setText("No such item in stock");
        } else if (e.getSource() == checkbtn) {
            information.setText(name);
            quantitycombo.removeAllItems();
            //purchasetxt.setText("");
            information.append("\nPrice: " + pounds.format(StockData.getPrice(key)));
            information.append("\nNumber in stock: " + StockData.getQuantity(key));
            for (int j = 0; j <= StockData.getQuantity(key); j++) {
                quantitycombo.addItem(new Integer(j));

            }
        }
        if (e.getSource() == quantitycombo) {
            calculatetxt.setText(pounds.format(Total));

        }
        if (e.getSource() == addbtn) {

            if (name == null) {
                information.setText("No such item in stock");
            }
            if (Total == 0) {
                JOptionPane.showMessageDialog(null, "No quantity selected!");
            } else if (Value <= StockData.getQuantity(key)) {
                totalcost += Total;
                StockData.updateQuantity(key, -Value);

                /* for (int a = 0; a < quantitybasket.size(); a++) {
                    purchasetxt.append("\n" + "Quantity:" + StockData.getQuantity(quantitybasket.get(a)));
                    //+ StockData.getQuantity(quantitybasket.get(a)));
                }
                for (int i = 0; i < itembasket.size(); i++) {
                    purchasetxt.append("\n" + "Name:" + StockData.getName(itembasket.get(i)));
                }*/
                purchasetxt.append("\n" + Value + "x" + name + "     " + " Sub total: " + pounds.format(Total));
                // purchasetxt.setText("\nTotal Cost is: " + totalcost);
                quantitycombo.removeAllItems();
                information.setText("Items have been added to your basket");
                stockNo.setText("");
                calculatetxt.setText("");
            } else {
                information.append("\nToo many item requested - number\n in stock: "
                        + StockData.getQuantity(key));
            }
        }
        if (e.getSource() == resetbtn) {
            //StockData.updateQuantity(key, +Value);
            information.setText("No Item left in Basket");
            stockNo.setText("");
            information.setText("");
            quantitycombo.removeAllItems();
            calculatetxt.setText("");
            totalcost = 0;

        }

        if (e.getSource() == purchasebtn) {

            if (totalcost == 0) {
                JOptionPane.showMessageDialog(null, "No item in basket!");
            } else {
                int dialogbutton = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(this,
                        "      " + message + "\n     Thank You For Shopping" + "\n" + purchasetxt.getText()
                        + "\n****************************************" + "\nThe total is" + pounds.format(totalcost)
                        + "." + "\n" + "\nDo you want to proceed? ", "Confirmation Purchase", dialogbutton);

                if (result == JOptionPane.YES_OPTION) {
                    JFrame frame = new JFrame("JOptionPane showMessageDialog Purchase done!");

                    JOptionPane.showMessageDialog(frame, "Purchase done!");

                    stockNo.setText("");
                    purchasetxt.setText("");
                    quantitycombo.removeAllItems();
                    calculatetxt.setText("");
                    totalcost = 0;
                    information.setText("No Item left in Basket");

                } else if (result == JOptionPane.NO_OPTION) {
                    System.out.println("Do nothing");
                }
            }

        }

        if (e.getSource() == backbtn) {
            if (totalcost != 0) {
                JOptionPane.showMessageDialog(null, "Some Items in Basket!");
            } else {
                Master master = new Master();
                setVisible(false);
            }
        }
    }

    public static void main(String[] args) {
        PurchaseItem1 purchase = new PurchaseItem1();

    }

}
