package com.example.GUI.Components;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.Beans.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.awt.image.BufferedImage;

public class ProductItemPanel extends JPanel{
    public boolean selected = false;
    public Product product;
    public ProductItemPanel(Product product) {
        this.product = product;
        initComponents();

        setBorder(BorderFactory.createRaisedBevelBorder());

        productImage.setVerticalAlignment(SwingConstants.CENTER);
        productImage.setHorizontalAlignment(SwingConstants.CENTER);
        productName.setVerticalAlignment(SwingConstants.CENTER);
        productName.setHorizontalAlignment(SwingConstants.CENTER);
        productPrice.setVerticalAlignment(SwingConstants.CENTER);
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);

        selectedCheckBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedCheckBox.isSelected()){
                    selected = true;
                }
                if (!selectedCheckBox.isSelected()) {
                    selected = false;
                }
            }
        });

        productName.setText(product.getName());

        if (product.getProductPicture() == null) {
            BufferedImage bi = new BufferedImage(35, 20, BufferedImage.TYPE_INT_RGB);
            productImage.setIcon(new javax.swing.ImageIcon(bi));
        }else{
            productImage.setIcon(new ImageIcon(product.getProductPicture()));
        }
        productPrice.setText(product.getPrice().toString() + " $");
        productPrice.setHorizontalAlignment(SwingConstants.RIGHT);
    }                         
    private void initComponents() {

        productImage = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        productPrice = new javax.swing.JLabel();
        selectedCheckBox = new javax.swing.JCheckBox();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectedCheckBox))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(selectedCheckBox)
                .addGap(8, 8, 8)
                .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }                      

    private javax.swing.JLabel productImage;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPrice;
    private javax.swing.JCheckBox selectedCheckBox;
}
