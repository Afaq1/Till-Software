package stock;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UpdateStockEdit1 extends JFrame implements ActionListener {

    JTextField stockName = new JTextField(26);
    JTextField stockNo = new JTextField(19);
    JComboBox Quantity = new JComboBox();
    JSpinner Price = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999.0, 0.5));
    JButton check = new JButton("Search");
    JButton Update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton back = new JButton("Back");
    JLabel stocklbl = new JLabel("Enter Stock No:");
    JLabel namelbl = new JLabel("Stock Name:");
    JLabel quantitylbl = new JLabel("New Quantity:");
    JLabel pricelbl = new JLabel("New Price:");
    JLabel stockeditlbl = new JLabel("Stock Edit");
    JButton uploadImage = new JButton("Browse");
     JLabel browselbl = new JLabel("Add a new Image");

    public UpdateStockEdit1() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 450, 340);
        setTitle("Update Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        stockeditlbl.setBounds(200, 20, 110, 30);
        stockeditlbl.setFont(new Font("", Font.BOLD, 18));
        stocklbl.setBounds(40, 70, 110, 30);
        stockNo.setBounds(160, 70, 110, 30);
        check.setBounds(300, 70, 110, 30);
        namelbl.setBounds(40, 110, 110, 30);
        stockName.setBounds(160, 110, 250, 30);
        quantitylbl.setBounds(40, 150, 110, 30);
        Quantity.setBounds(160, 150, 70, 30);
        pricelbl.setBounds(260, 150, 110, 30);
        Price.setBounds(340, 150, 70, 30);
        Update.setBounds(40, 250, 110, 30);
        delete.setBounds(170, 250, 110, 30);
        back.setBounds(300, 250, 110, 30);
        browselbl.setBounds(130, 200, 110, 30);
        uploadImage.setBounds(230, 200, 110, 30);
        add( browselbl);
        add( uploadImage);
        add(stockeditlbl);
        add(stocklbl);
        add(stockNo);
        add(check);
        add(namelbl);
        add(stockName);
        add(quantitylbl);
        add(Quantity);
        add(pricelbl);
        add(Price);
        add(Update);
        add(delete);
        add(back);

        Update.addActionListener(this);
        delete.addActionListener(this);
        back.addActionListener(this);
        check.addActionListener(this);
         uploadImage.addActionListener(this);
        JPanel top = new JPanel();
        add("North", top);
        JPanel middle = new JPanel();
        add("Center", middle);
        JPanel bottom = new JPanel();
        add("South", bottom);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePrePath = "images\\";
        String key = stockNo.getText();
        String name = StockData.getName(key);
        if (e.getSource() == check) {
            if (name == null) {
                JOptionPane.showMessageDialog(null, " No such item in stock ");
            } else {
                JOptionPane.showMessageDialog(null, "Name: " + StockData.getName(key)
                        + "\nPrice: " + StockData.getPrice(key)
                        + "\nQuantity: " + StockData.getQuantity(key));
                stockName.setText(name);
                for (int i = 0; i <= 100; i++) {
                    Quantity.addItem(new Integer(i));
                }
                for (double j = 0; j <= StockData.getPrice(key); j++) {
                    Price.setValue(new Double(j));
                }
            }
        }

        if (e.getSource() == delete) {
            if (name==null){
                JOptionPane.showMessageDialog(null, "Check your data.");
            }else{
            int dialogbutton = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog((Component) e.getSource(), "Do you want to Delete", "Delete",dialogbutton );
            if (result == JOptionPane.YES_OPTION) {
                StockData.delete(key);
                JOptionPane.showMessageDialog(null, "Item has been deleted");
                Quantity.removeAllItems();
                int p = 0;
                Price.setValue(p);
                stockName.setText("");
                stockNo.setText("");
            } else if (result == JOptionPane.NO_OPTION) {
                System.out.println("Do nothing");
            }
        }}

        if (e.getSource() == Update) {
            if (name==null){
                JOptionPane.showMessageDialog(null, "Check your data.");
            }else{
            String newname = stockName.getText();
            StockData.updatename(key, newname);
            int qu = (int) Quantity.getSelectedItem();
            StockData.updateQuantity(key, qu);
            StockData.updateprice(key, -(StockData.getPrice(key)));
            double pr = (double) Price.getValue();
            StockData.updateprice(key, pr);
            JOptionPane.showMessageDialog(null, "You have made the update.");
            Quantity.removeAllItems();
            int p = 0;
            Price.setValue(p);
            stockName.setText("");
            stockNo.setText("");
        }}
        if (e.getSource() == back) {
            UpdateStock Updatestock = new UpdateStock();
            setVisible(false);
        }
         if (e.getSource() == uploadImage) {

            JOptionPane.showMessageDialog(null, "Image size should be 100*90");

            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "gif", "png");
            file.setFileFilter(filter);
            file.setAcceptAllFileFilterUsed(false);
            file.setDialogTitle("Choose Image");
            String fileRename = "images\\" + key + ".JPG";
            File image = new File(fileRename);
            if (image.exists()) {
                image.delete();
            }
            if (file.showOpenDialog(uploadImage) == JFileChooser.APPROVE_OPTION) {

                File selectedFile = file.getSelectedFile();
                selectedFile.renameTo(image);
                Path pathImage = Paths.get(selectedFile.getAbsolutePath());
                Path pathImageNew = Paths.get(filePrePath);
                try {
                    Files.move(pathImage, pathImageNew.resolve(pathImage.getFileName()), StandardCopyOption.ATOMIC_MOVE);
                    System.out.println(pathImage);
                    System.out.println(pathImageNew);
                } catch (Exception ex) {
                    System.out.println("Image Upload");
                }
            } else if (file.showOpenDialog(uploadImage) == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Image was not selected, however, item will still be added");
            }

        }
    }
    public static void main(String[] args) {
        UpdateStockEdit1 UpdateStockEdit = new UpdateStockEdit1();
    }
}
