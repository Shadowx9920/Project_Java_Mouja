package com.example.GUI.Components;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.Beans.Accounts.User;

import java.awt.Color;

public class UserPanel extends JPanel {

    public Boolean selected = false;
    public User user;

    public UserPanel(User user) {
        initComponents();

        this.user = user;

        this.userName.setText(user.getUsername());
        this.userPassword.setText(user.getPassword());
        this.userEmail.setText(user.getEmail());
        this.userPhoneNumber.setText(user.getPhoneNumber());
        this.userImage.setIcon(new ImageIcon(user.getImage()));

        userName.setVerticalAlignment(SwingConstants.CENTER);
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userPassword.setVerticalAlignment(SwingConstants.CENTER);
        userPassword.setHorizontalAlignment(SwingConstants.CENTER);
        userEmail.setVerticalAlignment(SwingConstants.CENTER);
        userEmail.setHorizontalAlignment(SwingConstants.CENTER);
        userPhoneNumber.setVerticalAlignment(SwingConstants.CENTER);
        userPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);

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

        setBorder(BorderFactory.createRaisedBevelBorder());

        userImage.setVerticalAlignment(SwingConstants.CENTER);
        userImage.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void initComponents() {

        userImage = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        selectedCheckBox = new javax.swing.JCheckBox();
        userPassword = new javax.swing.JLabel();
        userEmail = new javax.swing.JLabel();
        userPhoneNumber = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectedCheckBox))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(userImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(selectedCheckBox)
                .addGap(8, 8, 8)
                .addComponent(userImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }       
    
    private javax.swing.JLabel userEmail;
    private javax.swing.JLabel userImage;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel userPassword;
    private javax.swing.JLabel userPhoneNumber;
    private javax.swing.JCheckBox selectedCheckBox;
    
}
