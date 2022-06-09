package com.example.GUI.JForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.example.Beans.Commande;
import com.example.Beans.Product;
import com.example.DataBase.DBget;
import com.example.GUI.CurrentSession;
import com.example.GUI.Components.Buttons.MoujaButton;

public class CommandesHistoryFrame extends JFrame{
    
    public CommandesHistoryFrame() {
        initComponents();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        exitButton.addActionListener(e -> {
            dispose();
        });

        Vector<Object> columnNames = new Vector<Object>();
        columnNames.add("User");
        columnNames.add("Product");
        columnNames.add("Commande ID");
        columnNames.add("price");
        columnNames.add("Buying Date");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        LinkedList<Commande> commands = DBget.getAllCommandes();
        for (Commande commande : commands) {
            LinkedList<Product> products = DBget.getAllCommandeProducts(commande.getId());
            for (Product product : products) {
                Vector<Object> row = new Vector<Object>();
                row.add(commande.getBuyer().getUsername());
                row.add(product.getName());
                row.add(commande.getId());
                row.add(product.getPrice());
                row.add(commande.getDate());
                data.add(row);
            }
        }

        DefaultTableModel model = new DefaultTableModel(data,columnNames);
        historyTable.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        historyTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        historyTable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
    }

    public static void changeColors(Color color){
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

        exitButton = new MoujaButton("",30,30,Color.WHITE,Color.BLUE);
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();

        setUndecorated(true);
        getRootPane().setBorder(new LineBorder(Color.BLACK,2,true));
        setIconImage(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/app-logo.png")).getImage());

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Commandes History :");

        jScrollPane1.setViewportView(historyTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
        );

        pack();
    }

    public static void startCommandesHistoryFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CommandesHistoryFrame().setVisible(true);
                changeColors(color);
            }
        });
    }

    private static MoujaButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable historyTable;
}
