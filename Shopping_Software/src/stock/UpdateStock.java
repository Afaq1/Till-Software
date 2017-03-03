package stock;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UpdateStock extends JFrame implements ActionListener {

    JButton AddButton = new JButton("Add");
    JButton EditButton = new JButton("Edit");
    JButton BackButton = new JButton("Main Menu");
    JLabel updatestock = new JLabel("Update Stock");

    public UpdateStock() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 310, 200);
        setTitle("Update Stock");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        updatestock.setBounds(100, 20, 140, 30);
        updatestock.setFont(new Font("", Font.BOLD, 18));
        AddButton.setBounds(30, 70, 110, 30);
        EditButton.setBounds(160, 70, 110, 30);
        BackButton.setBounds(100, 110, 110, 30);
        add(EditButton);
        add(AddButton);
        add(updatestock);
        add(BackButton);
        AddButton.addActionListener(this);
        EditButton.addActionListener(this);
        BackButton.addActionListener(this);
      
        JPanel top = new JPanel();
        JPanel middle = new JPanel();
        add("North", top);
        add("Center", middle);
        JPanel bottom = new JPanel();
        add("South", bottom);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AddButton) {
            UpdateStockAdd1 updatestockadd1 = new UpdateStockAdd1();
            setVisible(false);
        } else if (e.getSource() == EditButton) {
            UpdateStockEdit1 UpdateStockEdit = new UpdateStockEdit1();
            setVisible(false);
        } else if (e.getSource() == BackButton) {
            Master master = new Master();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        UpdateStock UpdateStock = new UpdateStock();
    }

}
