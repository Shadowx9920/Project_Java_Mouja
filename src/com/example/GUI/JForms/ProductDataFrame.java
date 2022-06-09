package com.example.GUI.JForms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.imgscalr.Scalr;

import com.example.Beans.Commande;
import com.example.Beans.Product;
import com.example.DataBase.DBget;
import com.example.GUI.CurrentSession;
import com.example.GUI.Components.ImageAvatar;
import com.example.GUI.Components.Buttons.MoujaButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;

public class ProductDataFrame extends JFrame{

    public Product product;

    public ProductDataFrame(Product product) {
        this.product = product;
        initComponents();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        exitButton.addActionListener(e -> {
            dispose();
        });

        productName.setText("Name :     " + product.getName());
        ProductProvider.setText("Fournisseur :     " +product.getFournisseur().getName());
        productPrice.setText("Price :     " +product.getPrice() + "$");
        productQuantity.setText("Quantity :     " );
        productDescription.setLineWrap(true);
        productDescription.setText("Description :     " +product.getDescription());
        productDescription.setEditable(false);
        if (product.getProductPicture() == null) {
            productPicture.setImage(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/product.png")));
        }else{
            productPicture.setImage(new ImageIcon(Scalr.resize(product.getProductPicture(), Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 160, 160)));
        }

        if(CurrentSession.checkIfAdmin()){
            setAdminHistory();
        }else{
            setUserHistory();
        }
    }

    public void setAdminHistory(){
        Vector<Object> columnNames = new Vector<Object>();
        columnNames.add("User");
        columnNames.add("Product");
        columnNames.add("Commande ID");
        columnNames.add("price");
        columnNames.add("Buying Date");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        LinkedList<Commande> Commands = DBget.getAllCommandes();
        for (Commande commande : Commands) {
            LinkedList<Product> products = DBget.getAllCommandeProducts(commande.getId());
            for (Product p : products) {
                if (p.getId() == product.getId()) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(commande.getBuyer().getUsername());
                    row.add(p.getName());
                    row.add(commande.getId());
                    row.add(p.getPrice());
                    row.add(commande.getDate());
                    data.add(row);
                }
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        historyTable.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        historyTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
    }

    public void setUserHistory(){
        Vector<Object> columnNames = new Vector<Object>();
        columnNames.add("Product");
        columnNames.add("Commande ID");
        columnNames.add("price");
        columnNames.add("Buying Date");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        LinkedList<Commande> Commands = DBget.getAllUserCommandes(CurrentSession.getUser().getId());
        for (Commande commande : Commands) {
            LinkedList<Product> products = DBget.getAllCommandeProducts(commande.getId());
            for (Product p : products) {
                if (p.getId() == product.getId()) {
                    Vector<Object> row = new Vector<Object>();
                    row.add(p.getName());
                    row.add(commande.getId());
                    row.add(p.getPrice());
                    row.add(commande.getDate());
                    data.add(row);
                }
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        historyTable.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        historyTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
    }

    public static void changeColors(Color color){
        productPicture.setGradientColor1(color);
        productPicture.setGradientColor2(color);
        exitButton.changeButtonColor(color, color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }

    public static void initIcons(boolean isDark){
        if(isDark){
            exitButton.setIcon(new ImageIcon(ProductDataFrame.class.getResource("/com/example/GUI/resources/black_icons/cross.png")));
        }else{
            exitButton.setIcon(new ImageIcon(ProductDataFrame.class.getResource("/com/example/GUI/resources/white_icons/cross.png")));
        }
    }

    private void initComponents() {

        exitButton = new MoujaButton("",30,30,Color.RED,Color.WHITE);
        productPicture = new ImageAvatar();
        productName = new javax.swing.JLabel();
        productPrice = new javax.swing.JLabel();
        ProductProvider = new javax.swing.JLabel();
        productQuantity = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productDescription = new javax.swing.JTextArea();
        historyScroll = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setUndecorated(true);
        getRootPane().setBorder(new LineBorder(Color.BLACK,2,true));

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        productDescription.setColumns(20);
        productDescription.setRows(5);
        jScrollPane1.setViewportView(productDescription);
        historyScroll.setViewportView(historyTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("History :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Info :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(historyScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(productPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ProductProvider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(productPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(productQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(productDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ProductProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(productPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historyScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
        );

        pack();
    }
    
    public static void startProductDataFrame(Product product,Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductDataFrame(product).setVisible(true);
                changeColors(color);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel ProductProvider;
    private static MoujaButton exitButton;
    private javax.swing.JScrollPane historyScroll;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea productDescription;
    private javax.swing.JLabel productName;
    private static ImageAvatar productPicture;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productQuantity;
}
