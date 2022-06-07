package com.example.GUI.JForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;

import com.example.DataBase.DBmodify;
import com.example.DataBase.DBset;
import com.example.GUI.Components.CustomJFrame;
import com.example.GUI.Components.FournisseurDataPanel;
import com.example.GUI.Components.Buttons.MoujaButton;
import com.example.GUI.Components.Notifications.Notification;

public class AddFournisseurFrame extends CustomJFrame {

    public  AddFournisseurFrame(int ID) {
        super();
        initComponents();

        config();

        addButton.addActionListener(e -> {

            String name = jTextField1.getText();
            
            if(name.equals("")) {
                Notification notification = new Notification(AddFournisseurFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Please Enter Valid Information");
                notification.showNotification();
                return;
            }

            DBmodify.modifyFournisseur(ID, name);
            System.out.println("modifed");
            FournisseurDataPanel.updateFournisseurTable();
        });

        addButton.setText("Modify");
    }

    public AddFournisseurFrame() {
        super();
        initComponents();

        config();

        addButton.addActionListener(e -> {
            String name = jTextField1.getText();
            System.out.println(name);
            if(name.equals("")) {
                Notification notification = new Notification(AddFournisseurFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Please Enter Valid Information");
                notification.showNotification();
                return;
            }
            DBset.addFournisseur(name);
            System.out.println("Added");
            FournisseurDataPanel.updateFournisseurTable();
            //AdminControlFrame.updateFrame();
        });
    }

    public void config(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        exitButton.addActionListener(e -> {
            this.dispose();
        });
        cancelButton.addActionListener(e -> {
            this.dispose();
        });
    }

    public static void changeColors(Color color) {
        exitButton.changeButtonColor(color.brighter(), color.darker());
        cancelButton.changeButtonColor(color.brighter(), color.darker());
        addButton.changeButtonColor(color.brighter(), color.darker());
        jTextField1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                jTextField1.setBorder(new LineBorder(color,1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jTextField1.setBorder(new LineBorder(Color.GRAY,1));
            }
            
        });
    }
    public static void initIcons(boolean isDark){
        if (isDark) {
            exitButton.setIcon(new javax.swing.ImageIcon(
            AddFournisseurFrame.class.getResource("/com/example/GUI/resources/black_icons/cross.png")));
        }else{
            exitButton.setIcon(new javax.swing.ImageIcon(
            AddFournisseurFrame.class.getResource("/com/example/GUI/resources/white_icons/cross.png")));
        }
    }
    private void initComponents() {

        exitButton = new MoujaButton("",30,30,Color.RED,Color.WHITE);
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        cancelButton = new MoujaButton("Cancel",30,50,Color.RED,Color.WHITE);
        addButton = new MoujaButton("Add",30,30,Color.RED,Color.WHITE);
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setUndecorated(true);

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel1.setText("Enter Name :");

        cancelButton.setPreferredSize(new java.awt.Dimension(50, 30));

        addButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Provider :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }
    
    public static void startAddFournisseurFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFournisseurFrame().setVisible(true);
                changeColors(color);
                double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
                if (luminescence < 128) {
                    initIcons(false);
                }else{
                    initIcons(true);
                }
            }
        });
    }

    public static void startAddFournisseurFrame(int fournisseurID,Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFournisseurFrame(fournisseurID).setVisible(true);
                changeColors(color);
            }
        });
    }
    
    private static MoujaButton exitButton;
    private static MoujaButton cancelButton;
    private static MoujaButton addButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JTextField jTextField1;                  
}
