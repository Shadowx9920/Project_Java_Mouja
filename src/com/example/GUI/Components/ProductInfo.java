package com.example.GUI.Components;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.Beans.Product;

public class ProductInfo extends JPanel{

    public ProductInfo(Product product){
        initComponents();
        productDescription.setText("");
        productDescription.setText(product.getDescription());
        productDescription.setVerticalAlignment(SwingConstants.CENTER);
        productDescription.setHorizontalAlignment(SwingConstants.CENTER);

        productProvider.setText(product.getFournisseur().name);
        productProvider.setVerticalAlignment(SwingConstants.CENTER);
        productProvider.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initComponents() {

        productProvider = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productDescription = new javax.swing.JLabel();

        //setMinimumSize(new java.awt.Dimension(100, 100));
        //setPreferredSize(new java.awt.Dimension(20, 133));

        jLabel2.setText("Provider :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(productDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(productProvider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
        );
    }
    
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel productDescription;
    private javax.swing.JLabel productProvider;
}
