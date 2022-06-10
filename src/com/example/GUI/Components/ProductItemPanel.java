package com.example.GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.imgscalr.Scalr;

import com.example.Beans.Product;
import com.example.GUI.CurrentSession;
import com.example.GUI.Components.Buttons.MoujaButton;
import com.example.GUI.JForms.ModifyProductFrame;
import com.example.GUI.JForms.ProductDataFrame;

public class ProductItemPanel extends JPanel{

    public boolean selected = false;
    public Product product;
    public static Color color;
    public Color bgColor = getBackground();

    public ProductItemPanel(){
        info = new JPanel();
        initComponents();
        productImage.setVisible(false);
        productPrice.setVisible(false);
        viewDetailsButton.setVisible(false);
        modifyButton.setVisible(false);
    }
    
    public ProductItemPanel(Product product,Color color) {

        this.product = product;
        
        productBriefPanel = new ProductBrief(product);

        info = new JPanel();
        info.setLayout(new BorderLayout());
        info.add(productBriefPanel);

        initComponents();

        changeColors(color);

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
                    Color c = getBackground().darker().darker();
                    setBackground(c);
                    productBriefPanel.setBackground(c);
                    setBorder(BorderFactory.createLoweredBevelBorder());
                } else {
                    setBackground(bgColor);
                    productBriefPanel.setBackground(bgColor);
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

        if (product.getProductPicture() == null) {
            productImage.setIcon(new ImageIcon(ProductItemPanel.class.getResource("/com/example/GUI/resources/black_icons/product.png")));
        }else{
            productImage.setIcon(new ImageIcon(Scalr.resize(product.getProductPicture(), Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 160, 160)));
        }

        productImage.setVerticalAlignment(SwingConstants.CENTER);
        productImage.setHorizontalAlignment(SwingConstants.CENTER);

        productPrice.setVerticalAlignment(SwingConstants.CENTER);
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);

        modifyButton.addActionListener(e -> {
            ModifyProductFrame.startModifyProductFrame(this.product,color);
        });

        
        productPrice.setText(product.getPrice().toString() + " $");
        productPrice.setHorizontalAlignment(SwingConstants.RIGHT);

        info.setVisible(true);
        viewDetailsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CurrentSession.checkIfLogged()) {
                    ProductDataFrame.startProductDataFrame(product, color);
                }else{
                    JOptionPane.showMessageDialog(null, "You Are Not Logged in.");
                }
            }});
    }

    public static void initIcons(boolean isDark){
        if(isDark){
            viewDetailsButton.setIcon(new ImageIcon(ProductItemPanel.class.getResource("/com/example/GUI/resources/black_icons/icons8_info_16px_1.png")));
            modifyButton.setIcon(new ImageIcon(ProductItemPanel.class.getResource("/com/example/GUI/resources/black_icons/edit.png")));
        }else{
            viewDetailsButton.setIcon(new ImageIcon(ProductItemPanel.class.getResource("/com/example/GUI/resources/white_icons/icons8_info_16px_1.png")));
            modifyButton.setIcon(new ImageIcon(ProductItemPanel.class.getResource("/com/example/GUI/resources/white_icons/edit.png")));
        }
    }
    
    public static void changeColors(Color color){
        ProductItemPanel.color = color;
        viewDetailsButton.changeButtonColor(color, color);
        modifyButton.changeButtonColor(color, color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }

    private void initComponents() {
        viewDetailsButton = new MoujaButton("", 30, 30, Color.RED, Color.gray);
        modifyButton = new MoujaButton("", 30, 30, Color.RED, Color.gray);

        productImage = new javax.swing.JLabel();
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(productImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(info)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }                      

    private static javax.swing.JLabel productImage;
    private javax.swing.JLabel productPrice;
    private static MoujaButton viewDetailsButton;
    private static MoujaButton modifyButton;
    private javax.swing.JPanel info;
    private ProductBrief productBriefPanel;
}
