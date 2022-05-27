package com.example.GUI.Components;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.Beans.Product;

public class ProductBrief extends JPanel{

    public ProductBrief(Product product) {
        initComponents();
        productName.setText(product.getName());
        productName.setVerticalAlignment(SwingConstants.CENTER);
        productName.setHorizontalAlignment(SwingConstants.CENTER);
    }                        
    private void initComponents() {

        productName = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
    }

    private javax.swing.JLabel productName;
}
