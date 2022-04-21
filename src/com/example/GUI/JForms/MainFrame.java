package com.example.GUI.JForms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Graphics;
import java.awt.Image;

import com.example.GUI.CurrentSession;
import com.example.GUI.DBmanagement;
import com.example.GUI.Components.MoujaButton;
import com.example.GUI.Components.SharedPrefs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        searchBar.setVisible(false);
        acountMgmtTabs.setSelectedIndex(1);
        initIcons();
        setColors(Color.decode(SharedPrefs.accentColor));
        initButtonListeners();
    } 
    private void initIcons(){
        logOutButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/sign-out.png")));
        uploadPictureHolder.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/com/example/GUI/resources/img/user.png")));
        uploadPictureHolder.setVerticalAlignment(SwingConstants.CENTER);
        uploadPictureHolder.setHorizontalAlignment(SwingConstants.CENTER);
        signInPuctureHolder.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/user.png")));
        signInPuctureHolder.setVerticalAlignment(SwingConstants.CENTER);
        signInPuctureHolder.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/cross.png")));
        homeButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/user-16.png")));
        productsButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/world.png")));
        kartButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/shopping-cart.png")));
        settingsButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/settings-sliders.png")));
        uploadPicButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/upload.png")));
        modifyUserButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/edit.png")));
        changeColorButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/palette.png")));
    }  
    private void setColors(Color color) {
        SidePanel.setBackground(color);
    }
    private void initButtonListeners(){
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        homeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(0);
                searchBar.setVisible(false);
            }});
        productsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(1);
                searchBar.setVisible(true);
            }});
        kartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(2);
                searchBar.setVisible(true);
            }});
        settingsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(3);
                searchBar.setVisible(false);
            }});
            ProductTable.setModel(DBmanagement.getProductTableModel());
            signUpButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (loginTextField.getText() == "" || String.valueOf(passwordField.getPassword()) == "") {
                        showMessageDialog(null, "Account can not be created");
                        return;
                    }
                    if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(reTypePasswordField.getPassword()))) {
                        reTypePasswordField.setBorder(new LineBorder(Color.RED, 2));
                        return;
                    }
                    Icon icon = uploadPictureHolder.getIcon();
                    BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                    Graphics g = image.getGraphics();
                    icon.paintIcon(new JLabel(), g, 0, 0);
                    g.dispose();
                    boolean accountCreated = DBmanagement.signUp(loginTextField.getText(), String.valueOf(passwordField.getPassword()), image, emailTextField.getText(), phoneNumberTextField.getText());
                    if (accountCreated) {
                        JOptionPane.showMessageDialog(null, "User Created Successfully", "Result", JOptionPane.PLAIN_MESSAGE);
                        acountMgmtTabs.setSelectedIndex(1);
                    } else {
                        showMessageDialog(null, "Account can not be created");
                    }
                }});
            uploadPicButton.addActionListener(new ActionListener(){
                public ImageIcon resize(String imgPath) {
                    ImageIcon path = new ImageIcon(imgPath);
                    Image img = path.getImage();
                    Image newImg = img.getScaledInstance(imageLabel.getWidth() * 2, imageLabel.getHeight() * 2,
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
                connectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(authLoginTextField.getText().equals("") || String.valueOf(authPasswordField.getPassword()).equals("")){
                            authLoginTextField.setBorder(new LineBorder(Color.RED, 2));
                            authPasswordField.setBorder(new LineBorder(Color.RED, 2));
                            return;
                        }
                        boolean logged = CurrentSession.setSession(authLoginTextField.getText(),String.valueOf(authPasswordField.getPassword()));
                        if (logged){
                            if(CurrentSession.checkIfLogged()){
                                if(CurrentSession.checkIfAdmin()){
                                    System.out.println("Admin Logged");
                                }else{
                                    loggedEmail.setText(CurrentSession.getUser().getEmail());
                                    loggedPhoneNumber.setText(CurrentSession.getUser().getPhoneNumber());
                                    loggedUserName.setText(CurrentSession.getUser().getUsername());
                                    loggedcreationDate.setText(CurrentSession.getUser().getDate());
                                    loggedInPictureHolder.setIcon(new javax.swing.ImageIcon(CurrentSession.getUser().getImage()));
                                    acountMgmtTabs.setSelectedIndex(2);
                                }
                            }
                        }else{
                            authLoginTextField.setBorder(new LineBorder(Color.RED, 2));
                            authPasswordField.setBorder(new LineBorder(Color.RED, 2));
                        }
                    }});
                createNewAcountButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        acountMgmtTabs.setSelectedIndex(0);
                    }});
                cancelSignUpButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        acountMgmtTabs.setSelectedIndex(1);
                    }});
                logOutButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CurrentSession.logOut();
                        loggedEmail.setText("");
                        loggedPhoneNumber.setText("");
                        loggedUserName.setText("");
                        loggedcreationDate.setText("");
                        loggedInPictureHolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/GUI/resources/img/user.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                    }});
                changeColorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = JColorChooser.showDialog(null, "Choose Background Color", Color.WHITE);
                        if (color != null) {
                            setColors(color);
                            addToKartButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            changeColorButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            productsButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            modifyUserButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            connectButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            createNewAcountButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            settingsButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            removeFromKartButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            payButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            exitButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            homeButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            kartButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            logOutButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            signOutButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            signUpButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            uploadPicButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            viewDetailsButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            cancelSignUpButton.changeButtonColor(color.brighter().brighter(), color.darker());
                            uploadPicButton.changeButtonColor(color.brighter().brighter(), color.darker());
                        }
                    }});
    }                    
    private void initComponents() {

        initVar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        framePanel.setBackground(new java.awt.Color(255, 255, 255));
        framePanel.setLayout(new java.awt.BorderLayout());

        SidePanel.setBackground(new java.awt.Color(153, 0, 153));

        homeButton.setPreferredSize(new java.awt.Dimension(73, 30));

        productsButton.setPreferredSize(new java.awt.Dimension(73, 30));

        kartButton.setPreferredSize(new java.awt.Dimension(73, 30));

        settingsButton.setPreferredSize(new java.awt.Dimension(73, 30));

        imageLabel.setPreferredSize(new java.awt.Dimension(60, 60));

        logOutButton.setPreferredSize(new java.awt.Dimension(30, 30));

        loggedInPanel.setBackground(Color.WHITE);

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(settingsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kartButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SidePanelLayout.createSequentialGroup()
                        .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SidePanelLayout.setVerticalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        framePanel.add(SidePanel, java.awt.BorderLayout.LINE_START);

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        titleSeparator.setBackground(new java.awt.Color(0, 0, 0));

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("Mouja Store");

        seatchPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout seatchPanelLayout = new javax.swing.GroupLayout(seatchPanel);
        seatchPanel.setLayout(seatchPanelLayout);
        seatchPanelLayout.setHorizontalGroup(
            seatchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        seatchPanelLayout.setVerticalGroup(
            seatchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seatchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(seatchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(titleSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addComponent(seatchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(titleLabel))
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleSeparator)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(seatchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        MainPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 140));

        menuTabs.setBackground(new java.awt.Color(255, 255, 255));

        signUpPanel.setBackground(new java.awt.Color(255, 255, 255));

        signUpButton.setText("Sign Up");
        signUpButton.setPreferredSize(new java.awt.Dimension(69, 30));

        uploadPictureHolder.setBackground(new java.awt.Color(0, 0, 0));

        signUpDataPanel.setBackground(new java.awt.Color(255, 255, 255));

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginLabel.setText("Login :");

        retypePasswordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        retypePasswordLabel.setText("Re-Type Password :");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phoneLabel.setText("Phone Number :");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordLabel.setText("Password :");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        emailLabel.setText("Email :");

        uploadPicButton.setPreferredSize(new java.awt.Dimension(30, 30));

        passwordField.setPreferredSize(new java.awt.Dimension(112, 22));

        reTypePasswordField.setPreferredSize(new java.awt.Dimension(112, 22));

        javax.swing.GroupLayout signUpDataPanelLayout = new javax.swing.GroupLayout(signUpDataPanel);
        signUpDataPanel.setLayout(signUpDataPanelLayout);
        signUpDataPanelLayout.setHorizontalGroup(
            signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(retypePasswordLabel)
                    .addComponent(phoneLabel)
                    .addComponent(emailLabel)
                    .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(phoneNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reTypePasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        signUpDataPanelLayout.setVerticalGroup(
            signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpDataPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpDataPanelLayout.createSequentialGroup()
                        .addComponent(retypePasswordLabel)
                        .addGap(18, 18, 18)
                        .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneLabel)
                            .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        cancelSignUpButton.setText("Cancel");
        cancelSignUpButton.setPreferredSize(new java.awt.Dimension(69, 23));

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signUpDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelSignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        signUpPanelLayout.setVerticalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(signUpDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(signUpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelSignUpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab2", signUpPanel);

        signInPanel.setBackground(new java.awt.Color(255, 255, 255));

        loginSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginSignInLabel.setText("Login :");
        loginSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        loginSignInLabel.setRequestFocusEnabled(false);

        passwordSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordSignInLabel.setText("Password :");
        passwordSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        passwordSignInLabel.setRequestFocusEnabled(false);

        authPasswordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        authLoginTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        connectButton.setText("Sign In");
        connectButton.setPreferredSize(new java.awt.Dimension(30, 30));

        createNewAcountButton.setText("New Account");
        createNewAcountButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout signInPanelLayout = new javax.swing.GroupLayout(signInPanel);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(createNewAcountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginSignInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordSignInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(authPasswordField)
                            .addComponent(authLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(signInPuctureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        signInPanelLayout.setVerticalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(signInPuctureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addComponent(loginSignInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(passwordSignInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addComponent(authLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(authPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createNewAcountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab2", signInPanel);

        loggedInName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loggedInName.setText("User Name :");

        loggedInName1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loggedInName1.setText("Email :");

        loggedInName2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loggedInName2.setText("Phone Number :");

        loggedInName3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loggedInName3.setText("Creation Date :");

        modifyUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        loggedUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedPhoneNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedcreationDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setText("Logged in as :");

        javax.swing.GroupLayout loggedInPanelLayout = new javax.swing.GroupLayout(loggedInPanel);
        loggedInPanel.setLayout(loggedInPanelLayout);
        loggedInPanelLayout.setHorizontalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loggedInPanelLayout.createSequentialGroup()
                        .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loggedInPanelLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loggedInName)
                                    .addComponent(loggedInName1)
                                    .addComponent(loggedInName2)
                                    .addComponent(loggedInName3)))
                            .addGroup(loggedInPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)))
                        .addGap(191, 191, 191)
                        .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loggedcreationDate)
                            .addComponent(loggedPhoneNumber)
                            .addComponent(loggedEmail)
                            .addComponent(loggedUserName)
                            .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 324, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        loggedInPanelLayout.setVerticalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(37, 37, 37)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loggedInName)
                    .addComponent(loggedUserName))
                .addGap(18, 18, 18)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loggedInName1)
                    .addComponent(loggedEmail))
                .addGap(18, 18, 18)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loggedInName2)
                    .addComponent(loggedPhoneNumber))
                .addGap(18, 18, 18)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loggedInName3)
                    .addComponent(loggedcreationDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab3", loggedInPanel);

        menuTabs.addTab("tab5", acountMgmtTabs);

        productsPanel.setBackground(new java.awt.Color(255, 255, 255));

        productTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        productTablePanel.setLayout(new java.awt.BorderLayout());

        productScroll.setBackground(new java.awt.Color(255, 255, 255));
        productScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        productScroll.setViewportView(ProductTable);

        productTablePanel.add(productScroll, java.awt.BorderLayout.CENTER);

        productSpacerPanel.setBackground(new java.awt.Color(255, 255, 255));
        productSpacerPanel.setPreferredSize(new java.awt.Dimension(865, 25));

        javax.swing.GroupLayout productSpacerPanelLayout = new javax.swing.GroupLayout(productSpacerPanel);
        productSpacerPanel.setLayout(productSpacerPanelLayout);
        productSpacerPanelLayout.setHorizontalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        productSpacerPanelLayout.setVerticalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        productTablePanel.add(productSpacerPanel, java.awt.BorderLayout.PAGE_START);

        productsActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        productsActionsPanel.setPreferredSize(new java.awt.Dimension(865, 50));

        viewDetailsButton.setPreferredSize(new java.awt.Dimension(30, 30));

        addToKartButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout productsActionsPanelLayout = new javax.swing.GroupLayout(productsActionsPanel);
        productsActionsPanel.setLayout(productsActionsPanelLayout);
        productsActionsPanelLayout.setHorizontalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsActionsPanelLayout.createSequentialGroup()
                .addContainerGap(789, Short.MAX_VALUE)
                .addComponent(addToKartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        productsActionsPanelLayout.setVerticalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewDetailsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addToKartButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        productTablePanel.add(productsActionsPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout productsPanelLayout = new javax.swing.GroupLayout(productsPanel);
        productsPanel.setLayout(productsPanelLayout);
        productsPanelLayout.setHorizontalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanelLayout.setVerticalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(productTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab1", productsPanel);

        kartPanel.setBackground(new java.awt.Color(255, 255, 255));

        kartTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        kartTablePanel.setLayout(new java.awt.BorderLayout());

        kartSpacerPanel.setBackground(new java.awt.Color(255, 255, 255));
        kartSpacerPanel.setPreferredSize(new java.awt.Dimension(895, 25));

        javax.swing.GroupLayout kartSpacerPanelLayout = new javax.swing.GroupLayout(kartSpacerPanel);
        kartSpacerPanel.setLayout(kartSpacerPanelLayout);
        kartSpacerPanelLayout.setHorizontalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        kartSpacerPanelLayout.setVerticalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        kartTablePanel.add(kartSpacerPanel, java.awt.BorderLayout.PAGE_START);

        kartActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        kartActionsPanel.setPreferredSize(new java.awt.Dimension(895, 50));

        payButton.setPreferredSize(new java.awt.Dimension(30, 30));

        removeFromKartButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout kartActionsPanelLayout = new javax.swing.GroupLayout(kartActionsPanel);
        kartActionsPanel.setLayout(kartActionsPanelLayout);
        kartActionsPanelLayout.setHorizontalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartActionsPanelLayout.createSequentialGroup()
                .addContainerGap(789, Short.MAX_VALUE)
                .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeFromKartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kartActionsPanelLayout.setVerticalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kartActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeFromKartButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        kartTablePanel.add(kartActionsPanel, java.awt.BorderLayout.PAGE_END);

        kartPane.setBackground(new java.awt.Color(255, 255, 255));
        kartPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        kartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        kartPane.setViewportView(kartTable);

        kartTablePanel.add(kartPane, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout kartPanelLayout = new javax.swing.GroupLayout(kartPanel);
        kartPanel.setLayout(kartPanelLayout);
        kartPanelLayout.setHorizontalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kartTablePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        kartPanelLayout.setVerticalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(kartTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab3", kartPanel);

        settingsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Settings :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Change Color :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Change Theme :");

        colorModeComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        colorModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dark Mode", "Light Mode" }));

        changeColorButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(179, 179, 179)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changeColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colorModeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(410, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(changeColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(colorModeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(246, Short.MAX_VALUE))
        );

        menuTabs.addTab("tab4", settingsPanel);

        MainPanel.add(menuTabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 870, 520));

        framePanel.add(MainPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    public static void startMainFrame() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    private void initVar(){
        connectButton = new MoujaButton("Sign In",30, 75, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        signOutButton = new MoujaButton("Sign Out",30, 75, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        signOutButton.setVisible(false);
        createNewAcountButton = new MoujaButton("Create New Account",30, 75, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        cancelSignUpButton = new MoujaButton("Cancel",30, 75, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        framePanel = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        homeButton = new MoujaButton("Home",30, 75, SharedPrefs.sideButtonColor, SharedPrefs.sideHoverColor);
        productsButton = new MoujaButton("Products",30, 75, SharedPrefs.sideButtonColor, SharedPrefs.sideHoverColor);
        kartButton = new MoujaButton("Cart",30, 75, SharedPrefs.sideButtonColor, SharedPrefs.sideHoverColor);
        settingsButton = new MoujaButton("Settings",30, 75, SharedPrefs.sideButtonColor, SharedPrefs.sideHoverColor);
        imageLabel = new javax.swing.JLabel();
        logOutButton = new MoujaButton("",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        MainPanel = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        exitButton = new MoujaButton("",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        titleSeparator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        seatchPanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        menuTabs = new javax.swing.JTabbedPane();
        acountMgmtTabs = new javax.swing.JTabbedPane();
        signUpPanel = new javax.swing.JPanel();
        signUpButton = new MoujaButton("Sign Up",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        uploadPictureHolder = new javax.swing.JLabel();
        signUpDataPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        retypePasswordLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        phoneNumberTextField = new javax.swing.JTextField();
        loginTextField = new javax.swing.JTextField();
        uploadPicButton = new MoujaButton("",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        passwordField = new javax.swing.JPasswordField();
        reTypePasswordField = new javax.swing.JPasswordField();
        signInPanel = new javax.swing.JPanel();
        signInPuctureHolder = new javax.swing.JLabel();
        loginSignInLabel = new javax.swing.JLabel();
        passwordSignInLabel = new javax.swing.JLabel();
        authPasswordField = new javax.swing.JPasswordField();
        authLoginTextField = new javax.swing.JTextField();
        productsPanel = new javax.swing.JPanel();
        productTablePanel = new javax.swing.JPanel();
        productScroll = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        productSpacerPanel = new javax.swing.JPanel();
        productsActionsPanel = new javax.swing.JPanel();
        viewDetailsButton = new MoujaButton("",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        addToKartButton = new MoujaButton("",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        kartPanel = new javax.swing.JPanel();
        kartTablePanel = new javax.swing.JPanel();
        kartSpacerPanel = new javax.swing.JPanel();
        kartActionsPanel = new javax.swing.JPanel();
        payButton = new MoujaButton("Pay",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        removeFromKartButton = new MoujaButton("Remove",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        kartPane = new javax.swing.JScrollPane();
        kartTable = new javax.swing.JTable();
        settingsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        colorModeComboBox = new javax.swing.JComboBox<>();
        changeColorButton = new MoujaButton("",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        loggedInPanel = new javax.swing.JPanel();
        loggedInPictureHolder = new javax.swing.JLabel();
        loggedInName = new javax.swing.JLabel();
        loggedInName1 = new javax.swing.JLabel();
        loggedInName2 = new javax.swing.JLabel();
        loggedInName3 = new javax.swing.JLabel();
        modifyUserButton = new MoujaButton("",30, 30, SharedPrefs.mainButtonColor, SharedPrefs.mainHoverColor);
        loggedUserName = new javax.swing.JLabel();
        loggedEmail = new javax.swing.JLabel();
        loggedPhoneNumber = new javax.swing.JLabel();
        loggedcreationDate = new javax.swing.JLabel();
    }
                        
    private MoujaButton addToKartButton;
    private MoujaButton changeColorButton;
    private MoujaButton productsButton;
    private MoujaButton modifyUserButton;
    private MoujaButton connectButton;
    private MoujaButton createNewAcountButton;
    private MoujaButton settingsButton;
    private MoujaButton removeFromKartButton;
    private MoujaButton payButton;
    private MoujaButton exitButton;
    private MoujaButton homeButton;
    private MoujaButton kartButton;
    private MoujaButton logOutButton;
    private MoujaButton signOutButton;
    private MoujaButton signUpButton;
    private MoujaButton uploadPicButton;
    private MoujaButton viewDetailsButton;
    private MoujaButton cancelSignUpButton;

    private javax.swing.JPanel Header;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTable ProductTable;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JTabbedPane acountMgmtTabs;
    private javax.swing.JTextField authLoginTextField;
    private javax.swing.JPasswordField authPasswordField;
    private javax.swing.JComboBox<String> colorModeComboBox;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel framePanel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel kartActionsPanel;
    private javax.swing.JScrollPane kartPane;
    private javax.swing.JPanel kartPanel;
    private javax.swing.JPanel kartSpacerPanel;
    private javax.swing.JTable kartTable;
    private javax.swing.JPanel kartTablePanel;
    private javax.swing.JLabel loggedEmail;
    private javax.swing.JLabel loggedInName;
    private javax.swing.JLabel loggedInName1;
    private javax.swing.JLabel loggedInName2;
    private javax.swing.JLabel loggedInName3;
    private javax.swing.JPanel loggedInPanel;
    private javax.swing.JLabel loggedInPictureHolder;
    private javax.swing.JLabel loggedPhoneNumber;
    private javax.swing.JLabel loggedUserName;
    private javax.swing.JLabel loggedcreationDate;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel loginSignInLabel;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JTabbedPane menuTabs;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordSignInLabel;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JScrollPane productScroll;
    private javax.swing.JPanel productSpacerPanel;
    private javax.swing.JPanel productTablePanel;
    private javax.swing.JPanel productsActionsPanel;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JPasswordField reTypePasswordField;
    private javax.swing.JLabel retypePasswordLabel;
    private javax.swing.JTextField searchBar;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JPanel seatchPanel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JPanel signInPanel;
    private javax.swing.JLabel signInPuctureHolder;
    private javax.swing.JPanel signUpDataPanel;
    private javax.swing.JPanel signUpPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JSeparator titleSeparator;
    private javax.swing.JLabel uploadPictureHolder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;                  
}