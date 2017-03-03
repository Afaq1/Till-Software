package stock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.derby.drda.NetworkServerControl;

public class Catalog extends JDialog implements ActionListener {

    DefaultTableModel model = new DefaultTableModel();
    Catalog cat;
    JTable table = new JTable(model) {
        @Override
        public boolean isCellEditable(int row, int column) {
            switch (column) {
                case 0:
                    return false;
                case 1:
                    return false;
                case 2:
                    return false;
                case 3:
                    return false;
                case 4:
                    return false;
                case 5:
                    return false;
                default:
                    return true;
            }
        }
    };
    JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private static Connection connection;
    private static Statement stmt;
    private String key;
    private String productName;
    private int quantity;
    private double price;
    JButton refresh = new JButton("Refresh");
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");

    static {
        try {
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String sourceURL = "jdbc:derby://localhost:1527/"
                    + new File("UserDB").getAbsolutePath() + ";";
            connection = DriverManager.getConnection(sourceURL, "use", "use");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        } catch (SQLException sqle) {
            System.out.println(sqle);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Catalog() throws SQLException {

        add(scroll);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        model.addColumn("Item Number");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Price");
        setSize(500, 200);
        setTitle("Catalog");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(false);
        setResizable(false);
        try {
            Object[] rowInfo = new Object[4];
            for (int i = 0; i < getData().size(); i++) {
                rowInfo[0] = getData().get(i).getID();
                rowInfo[1] = getData().get(i).getProductName();
                rowInfo[2] = getData().get(i).getQuantity();
                rowInfo[3] = pounds.format(getData().get(i).getPrice());
                model.addRow(rowInfo);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        table.setModel(model);
        JPanel bottom = new JPanel();
        add("South", bottom);
        //bottom.add(refresh);
        refresh.addActionListener(this);
    }

    public String getID() {
        return this.key;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.price;
    }

    private Catalog(String key, String name, int quantity, double price) {
        this.key = key;
        this.productName = name;
        this.quantity = quantity;
        this.price = price;
    }

    public final ArrayList<Catalog> getData() {
        ArrayList<Catalog> cat = new ArrayList<>();
        ResultSet res;
        Catalog catalog;
        try {
            res = stmt.executeQuery("SELECT STOCKKEY, STOCKNAME, STOCKPRICE, STOCKQUANTITY FROM STOCK ORDER BY STOCKKEY");
            while (res.next()) {
                catalog = new Catalog(res.getString(1), res.getString(2), res.getInt(4), res.getDouble(3));
                cat.add(catalog);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            this.dispose();
            try {
                cat = new Catalog();
                //Catalog d = new Catalog();
                cat.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
