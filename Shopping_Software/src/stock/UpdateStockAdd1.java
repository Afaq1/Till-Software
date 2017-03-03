package stock;

import java.awt.BorderLayout;
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
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UpdateStockAdd1 extends JFrame implements ActionListener {

    JTextField StockName = new JTextField(26);
    JTextField stockNo = new JTextField(26);
    JComboBox Quantity = new JComboBox();
    JSpinner Price = new JSpinner(new SpinnerNumberModel(1.0, 0.0, 999.0, 0.5));
    JLabel stocklbl = new JLabel("Stock No:");
    JLabel namelbl = new JLabel("Stock Name:");
    JLabel quantitylbl = new JLabel("Quantity:");
    JLabel pricelbl = new JLabel("Price:");
    JLabel stockaddlbl = new JLabel("New Stock");
    JButton AddButton = new JButton("Add");
    JButton back = new JButton("Back");
    JLabel browselbl = new JLabel("Add Image");
    JButton uploadImage = new JButton("Browse");


    public UpdateStockAdd1() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 350, 390);
        setTitle("Update Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        stockaddlbl.setBounds(130, 20, 110, 30);
        stockaddlbl.setFont(new Font("", Font.BOLD, 18));
        stocklbl.setBounds(60, 80, 110, 30);
        stockNo.setBounds(190, 80, 110, 30);
        namelbl.setBounds(60, 130, 110, 30);
        StockName.setBounds(190, 130, 110, 30);
        quantitylbl.setBounds(60, 180, 110, 30);
        Quantity.setBounds(190, 180, 110, 30);
        pricelbl.setBounds(60, 230, 110, 30);
        Price.setBounds(190, 230, 110, 30);
        AddButton.setBounds(60, 310, 110, 30);
        back.setBounds(190, 310, 110, 30);
        uploadImage.setBounds(190, 270, 110, 30);
        browselbl.setBounds(60, 270, 110, 30);
        add(stockaddlbl);
        add(stocklbl);
        add(stockNo);
        add(namelbl);
        add(StockName);
        add(quantitylbl);
        add(Quantity);
        add(pricelbl);
        add(Price);
        add(AddButton);
        add(back);
        add(uploadImage);
        add(browselbl);
        
        back.addActionListener(this);
        AddButton.addActionListener(this);
        StockName.addActionListener(this);
        uploadImage.addActionListener(this);

        JPanel top = new JPanel();
        add("North", top);
        JPanel middle = new JPanel();
        add("Center", middle);
        JPanel bottom = new JPanel();
        add("South", bottom);

        setResizable(false);
        setVisible(true);

        for (int i = 1; i <= 100; i++) {
            Quantity.addItem(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePrePath = "images\\";
        String key = stockNo.getText();
        String name = StockName.getText();

        double p = (double) Price.getValue();
        int q = (int) Quantity.getSelectedItem();

        if (e.getSource() == AddButton) {
            if (name.equals("") || key.equals("")) {
                showMessageDialog(this, "One or more fields blank");
                return;
            }
            boolean ok = StockData.adding(key, name, p, q);
            stockNo.setText("");
            if (!ok) {
                JOptionPane.showMessageDialog(null, "Duplicate Key." + key);
            }
            if (ok) {

                JOptionPane.showMessageDialog(null, "You have made the update.");
                StockName.setText("");

            }
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
        if (e.getSource() == back) {
            UpdateStock Updatestock = new UpdateStock();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        UpdateStockAdd1 updateStockAdd = new UpdateStockAdd1();

    }
}
