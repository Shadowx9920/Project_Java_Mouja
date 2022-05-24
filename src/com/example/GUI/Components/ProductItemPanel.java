package com.example.GUI.Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.example.Beans.Product;
import com.example.GUI.CurrentSession;
import com.example.GUI.Components.Buttons.MoujaButton;
import com.example.GUI.JForms.ModifyProductFrame;

import org.imgscalr.Scalr;

public class ProductItemPanel extends PanelRound{
    public boolean selected = false;
    public Product product;
    public static Color color;
    public ProductItemPanel(){
        initComponents();
        setRoundBottomLeft(100);
        setRoundBottomRight(100);
        setRoundTopLeft(100);
        setRoundTopRight(100);
        productImage.setVisible(false);
        productName.setVisible(false);
        productPrice.setVisible(false);
        viewDetailsButton.setVisible(false);
        modifyButton.setVisible(false);
    }
    public ProductItemPanel(Product product,Color color) {
        this.product = product;
        initComponents();

        changeColors(color);

        setRoundBottomLeft(100);
        setRoundBottomRight(100);
        setRoundTopLeft(100);
        setRoundTopRight(100);

        setBorder(BorderFactory.createRaisedBevelBorder());

        if(!CurrentSession.checkIfLogged()){
            modifyButton.setVisible(false);
        }
        if (!CurrentSession.checkIfAdmin()) {
            modifyButton.setVisible(false);
        }

        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                selected = !selected;
                if(selected) {
                    setBorder(BorderFactory.createLoweredBevelBorder());
                } else {
                    setBorder(BorderFactory.createRaisedBevelBorder());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }});

        productImage.setVerticalAlignment(SwingConstants.CENTER);
        productImage.setHorizontalAlignment(SwingConstants.CENTER);
        productName.setVerticalAlignment(SwingConstants.CENTER);
        productName.setHorizontalAlignment(SwingConstants.CENTER);
        productPrice.setVerticalAlignment(SwingConstants.CENTER);
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);

        viewDetailsButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/icons8_info_16px_1.png")));

        viewDetailsButton.addActionListener(e -> {
        });

        modifyButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/edit.png")));
        modifyButton.addActionListener(e -> {
            ModifyProductFrame.startModifyProductFrame(this.product,color);
        });

        productName.setText(product.getName());

        if (product.getProductPicture() == null) {
            this.productImage.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/product.png")));
        }else{
            this.productImage.setIcon(new ImageIcon(Scalr.resize(product.getProductPicture(), Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 160, 160)));
        }
        productPrice.setText(product.getPrice().toString() + " $");
        productPrice.setHorizontalAlignment(SwingConstants.RIGHT);

        viewDetailsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }});
    }
    
    public static void changeColors(Color color){
        ProductItemPanel.color = color;
        viewDetailsButton.changeButtonColor(color, Color.GRAY);
        modifyButton.changeButtonColor(color, Color.GRAY);
    }

    private void initComponents() {
        viewDetailsButton = new MoujaButton("", 30, 30, Color.RED, Color.gray);
        modifyButton = new MoujaButton("", 30, 30, Color.RED, Color.gray);

        productImage = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();
        productPrice = new javax.swing.JLabel();

        modifyButton.setMaximumSize(new java.awt.Dimension(30, 30));
        modifyButton.setMinimumSize(new java.awt.Dimension(30, 30));
        modifyButton.setPreferredSize(new java.awt.Dimension(30, 30));

        viewDetailsButton.setMaximumSize(new java.awt.Dimension(30, 30));
        viewDetailsButton.setMinimumSize(new java.awt.Dimension(30, 30));
        viewDetailsButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(productImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }                      

    private javax.swing.JLabel productImage;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel productPrice;
    private static MoujaButton viewDetailsButton;
    private static MoujaButton modifyButton;
}
