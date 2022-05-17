package com.example.GUI.JForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import static javax.swing.JOptionPane.showMessageDialog;

import com.example.GUI.DBmanagement;
import com.example.GUI.Components.MoujaButton;

public class AddProductFrame extends javax.swing.JFrame {

    public AddProductFrame() {
        initComponents();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        uploadPictureHolder.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/product.png")));

        exitButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/cross.png")));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        
        uploadPicButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/upload.png")));
        uploadPicButton.addActionListener(new ActionListener(){
            public ImageIcon resize(String imgPath) {
                ImageIcon path = new ImageIcon(imgPath);
                Image img = path.getImage();
                Image newImg = img.getScaledInstance(uploadPictureHolder.getWidth() * 2, uploadPictureHolder.getHeight() * 2,
                        Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newImg);
                return image;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
                file.addChoosableFileFilter(filter);
                int res = file.showSaveDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File selFile = file.getSelectedFile();
                    String path = selFile.getAbsolutePath();
                    uploadPictureHolder.setIcon(resize(path));
                    pack();
                }
            }});

            addButton.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String name = nameTextField.getText();
                    String price = priceTextField.getText();
                    String description = descriptionTextArea.getText();
                    String Fournisseur = fournisseurTextField.getText();
                    
                    Icon icon = uploadPictureHolder.getIcon();
                    BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                    Graphics g = image.getGraphics();
                    icon.paintIcon(new JLabel(), g, 0, 0);
                    g.dispose();

                    try {
                        DBmanagement.addProduct(Fournisseur,name,description,image, Double.parseDouble(price));
                    } catch (Exception e) {
                        showMessageDialog(null, "Please Provide Valid Data");
                    }
                    dispose();
                    AdminControlFrame.initProducts();
                }
            });
    }

    private static void changeColors(Color color){
        addButton.changeButtonColor(color.brighter(), color.darker());
        exitButton.changeButtonColor(color.brighter(), color.darker());
        uploadPicButton.changeButtonColor(color.brighter(), color.darker());
        cancelButton.changeButtonColor(color.brighter(), color.darker());
    }
    
    private void initComponents() {

        signUpPanel = new javax.swing.JPanel();
        addButton = new MoujaButton("Add",30, 30, Color.white,Color.gray);
        uploadPictureHolder = new javax.swing.JLabel();
        dataPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        fournisseurLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        uploadPicButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        fournisseurTextField = new javax.swing.JTextField();
        priceTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        cancelButton = new MoujaButton("Cancel",30, 30, Color.white,Color.gray);
        exitButton = new MoujaButton("",30, 30, Color.white,Color.gray);

        setUndecorated(true);
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        signUpPanel.setBackground(new java.awt.Color(255, 255, 255));

        addButton.setText("Sign Up");
        addButton.setPreferredSize(new java.awt.Dimension(69, 30));

        uploadPictureHolder.setBackground(new java.awt.Color(0, 0, 0));

        dataPanel.setBackground(new java.awt.Color(255, 255, 255));

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameLabel.setText("Name :");

        priceLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        priceLabel.setText("Price :");

        fournisseurLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fournisseurLabel.setText("Provider/Fournisseur :");

        descriptionLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        descriptionLabel.setText("Description :");

        uploadPicButton.setPreferredSize(new java.awt.Dimension(30, 30));

        fournisseurTextField.setPreferredSize(new java.awt.Dimension(112, 22));

        priceTextField.setPreferredSize(new java.awt.Dimension(112, 22));

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fournisseurLabel)
                    .addComponent(priceLabel)
                    .addComponent(descriptionLabel)
                    .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(fournisseurTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(priceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fournisseurLabel)
                    .addComponent(fournisseurTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addComponent(priceLabel)
                        .addGap(18, 18, 18)
                        .addComponent(descriptionLabel))
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );

        cancelButton.setText("Cancel");
        cancelButton.setPreferredSize(new java.awt.Dimension(69, 23));

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        signUpPanelLayout.setVerticalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 875, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }

    public static void startAddProductFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProductFrame().setVisible(true);
                changeColors(color);
            }
        });
    }

    private static MoujaButton addButton;
    private static MoujaButton cancelButton;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private static MoujaButton exitButton;
    private javax.swing.JLabel fournisseurLabel;
    private javax.swing.JTextField fournisseurTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JPanel signUpPanel;
    private static MoujaButton uploadPicButton;
    private javax.swing.JLabel uploadPictureHolder;                   
}

