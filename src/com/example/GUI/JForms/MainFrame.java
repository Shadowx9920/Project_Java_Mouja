package com.example.GUI.JForms; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.GridLayout;

import com.example.Beans.Commande;
import com.example.Beans.Product;
import com.example.DataBase.DBget;
import com.example.GUI.CurrentSession;
import com.example.GUI.DBmanagement;
import com.example.GUI.Components.GradientPanel;
import com.example.GUI.Components.MoujaButton;
import com.example.GUI.Components.ProductItemPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
public class MainFrame extends JFrame {
    
    LinkedList<Product> products;
    LinkedList<Product> cartList;
    LinkedList<ProductItemPanel> items;
    LinkedList<ProductItemPanel> cartItems;

    LinkedList<Commande> commands = new LinkedList<Commande>();
    
    public static Color color = Color.red;

    ActionListener pActionListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String search = searchBar.getText();
                if(search.equals("")){
                    productGrid.removeAll();
                    for (int i = 0; i < products.size(); i++) {
                        items.add(new ProductItemPanel(products.get(i)));
                        productGrid.add(items.get(i));
                    }
                    productGrid.setLayout(new GridLayout(0,3,20,20));
                }else{
                    productGrid.removeAll();
                    for (int i = 0; i < products.size(); i++) {
                        if(products.get(i).getName().toLowerCase().contains(search.toLowerCase())){
                            items.add(new ProductItemPanel(products.get(i)));
                            productGrid.add(items.get(i));
                        }
                    }
                    productGrid.setLayout(new GridLayout(0,3,20,20));
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
        }};

    ActionListener cActionListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String search = searchBar.getText();
                if(search.equals("")){
                    kartGrid.removeAll();
                    for (int i = 0; i < cartList.size(); i++) {
                        cartItems.add(new ProductItemPanel(cartList.get(i)));
                        kartGrid.add(cartItems.get(i));
                    }
                    kartGrid.setLayout(new GridLayout(0,3,20,20));
                }else{
                    kartGrid.removeAll();
                    for (int i = 0; i < cartList.size(); i++) {
                        if(cartList.get(i).getName().toLowerCase().contains(search.toLowerCase())){
                            cartItems.add(new ProductItemPanel(cartList.get(i)));
                            kartGrid.add(cartItems.get(i));
                        }
                    }
                    kartGrid.setLayout(new GridLayout(0,3,20,20));
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
        }};


    public MainFrame() {
        initLayout();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        searchBar.setVisible(false);
        acountMgmtTabs.setSelectedIndex(1);

        productScroll.getVerticalScrollBar().setUnitIncrement(16);
        kartPane.getVerticalScrollBar().setUnitIncrement(16);

        loggedEmail.setVerticalAlignment(SwingConstants.CENTER);
        loggedEmail.setHorizontalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setVerticalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        loggedUserName.setVerticalAlignment(SwingConstants.CENTER);
        loggedUserName.setHorizontalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setVerticalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setHorizontalAlignment(SwingConstants.CENTER);

        initIcons();
        initProducts();
        initCart();
        initButtonListeners();

        SidePanel.setBackground(Color.BLACK);
    } 

    private void initVar(){
        connectButton = new MoujaButton("Sign In",30, 75, Color.white,Color.gray);
        signOutButton = new MoujaButton("Sign Out",30, 75, Color.white,Color.gray);
        createNewAcountButton = new MoujaButton("Create New Account",30, 75, Color.white,Color.gray);
        cancelSignUpButton = new MoujaButton("Cancel",30, 75, Color.white,Color.gray);
        homeButton = new MoujaButton("Home",30, 75, Color.white,Color.gray);
        logOutButton = new MoujaButton("",30, 30, Color.white,Color.gray);

        framePanel = new javax.swing.JPanel();
        SidePanel = new JPanel();
        productsButton = new MoujaButton("Products",30, 75, Color.white,Color.gray);
        kartButton = new MoujaButton("Cart",30, 75, Color.white,Color.gray);
        settingsButton = new MoujaButton("",30, 75, Color.white,Color.gray);
        imageLabel = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        exitButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        titleSeparator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        seatchPanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        menuTabs = new javax.swing.JTabbedPane();
        acountMgmtTabs = new javax.swing.JTabbedPane();
        signUpPanel = new javax.swing.JPanel();
        signUpButton = new MoujaButton("Sign Up",30, 30, Color.white,Color.gray);
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
        uploadPicButton = new MoujaButton("",30, 30, Color.white,Color.gray);
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
        productSpacerPanel = new javax.swing.JPanel();
        productsActionsPanel = new javax.swing.JPanel();
        viewDetailsButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        addToKartButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        kartPanel = new javax.swing.JPanel();
        kartTablePanel = new javax.swing.JPanel();
        kartSpacerPanel = new javax.swing.JPanel();
        kartActionsPanel = new javax.swing.JPanel();
        payButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        removeFromKartButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        kartPane = new javax.swing.JScrollPane();
        changeColorButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        loggedInPanel = new javax.swing.JPanel();
        loggedInPictureHolder = new javax.swing.JLabel();
        modifyUserButton = new MoujaButton("",30, 30,Color.white,Color.gray);
        loggedUserName = new javax.swing.JLabel();
        loggedEmail = new javax.swing.JLabel();
        loggedPhoneNumber = new javax.swing.JLabel();
        loggedcreationDate = new javax.swing.JLabel();
        productGrid = new javax.swing.JPanel();
        kartGrid = new javax.swing.JPanel();
    }
    
    private void initIcons(){
        logOutButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/sign-out.png")));
        uploadPictureHolder.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/user.png")));
        uploadPictureHolder.setVerticalAlignment(SwingConstants.CENTER);
        uploadPictureHolder.setHorizontalAlignment(SwingConstants.CENTER);
        signInPuctureHolder.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/user.png")));
        signInPuctureHolder.setVerticalAlignment(SwingConstants.CENTER);
        signInPuctureHolder.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/cross.png")));
        homeButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/user-16.png")));
        productsButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/world.png")));
        kartButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/shopping-cart.png")));
        settingsButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/settings-sliders.png")));
        uploadPicButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/upload.png")));
        modifyUserButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/edit.png")));
        addToKartButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/shopping-cart.png")));
        payButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/dollar.png")));
        removeFromKartButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/bin.png")));
    }  
    
    private void initButtonListeners(){
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
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
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addActionListener(pActionListener);
                searchBar.setVisible(true);
            }});
        kartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(2);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addActionListener(cActionListener);
                searchBar.setVisible(true);
            }});
        settingsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame.createSettingsPage(MainFrame.color);
            }});
        signUpButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginTextField.getText().equals("") || String.valueOf(passwordField.getPassword()).equals("")) {
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
                                MainFrame.this.dispose();
                                if (CurrentSession.getAdmin() != null) {
                                    AdminControlFrame.startAdminControlFrame(MainFrame.color);
                                    System.out.println("An Admin is now Logged");
                                }
                            }else{
                                loggedEmail.setText(CurrentSession.getUser().getEmail());
                                loggedPhoneNumber.setText(CurrentSession.getUser().getPhoneNumber());
                                loggedUserName.setText(CurrentSession.getUser().getUsername());
                                loggedcreationDate.setText(CurrentSession.getUser().getDate());
                                if (CurrentSession.getUser().getImage() != null) {
                                    loggedInPictureHolder.setIcon(new javax.swing.ImageIcon(CurrentSession.getUser().getImage()));
                                }else{
                                    BufferedImage bi = new BufferedImage(35, 20, BufferedImage.TYPE_INT_RGB);
                                    loggedInPictureHolder.setIcon(new javax.swing.ImageIcon(bi));
                                }
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
                        if(!CurrentSession.checkIfLogged()){
                            JOptionPane.showMessageDialog(null, "You are not logged in", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        CurrentSession.logOut();
                        loggedEmail.setText("");
                        loggedPhoneNumber.setText("");
                        loggedUserName.setText("");
                        loggedcreationDate.setText("");
                        loggedInPictureHolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/GUI/resources/img/user.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                    }});

                    addToKartButton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(CurrentSession.checkIfLogged()){
                                if(CurrentSession.checkIfAdmin()){
                                    JOptionPane.showMessageDialog(null, "You are not allowed to add products to the cart because you are an admin", "Error", JOptionPane.ERROR_MESSAGE);
                                }else{
                                    for (int i = 0; i < items.size(); i++) {
                                        if (items.get(i).selected) {
                                            cartList.add(items.get(i).product);
                                            cartItems.add(items.get(i));
                                        }
                                    }
                                    for (int i = 0; i < cartItems.size(); i++) {
                                        kartGrid.add(cartItems.get(i));
                                    }
                                    kartGrid.setLayout((new GridLayout(0,3,20,20)));
                                    initProducts();
                                    JOptionPane.showMessageDialog(null, "Products added to cart succesfully", "Succes", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "You must be logged in to add products to your cart", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        }});

        payButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentSession.checkIfLogged()){
                    if(CurrentSession.checkIfAdmin()){
                        JOptionPane.showMessageDialog(null, "You are not allowed to pay because you are an admin", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(cartList.size() == 0){
                            JOptionPane.showMessageDialog(null, "You must add products to your cart before you can pay", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            DBmanagement.createNewCommande(cartList);
                            JOptionPane.showMessageDialog(null, "You have paid succesfully", "Succes", JOptionPane.INFORMATION_MESSAGE);
                            cartList.clear();
                            cartItems.clear();
                            kartGrid.removeAll();
                            initCart();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "You must be logged in to pay", "Error", JOptionPane.ERROR_MESSAGE);
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
            }});
        removeFromKartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentSession.checkIfLogged()){
                    if(CurrentSession.checkIfAdmin()){
                        JOptionPane.showMessageDialog(null, "You are not allowed to remove products from the cart because you are an admin", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        for (int i = 0; i < cartItems.size(); i++) {
                            if (cartItems.get(i).selected) {
                                cartList.remove(cartItems.get(i).product);
                                cartItems.remove(cartItems.get(i));
                            }
                        }
                        kartGrid.removeAll();
                        for (int i = 0; i < cartItems.size(); i++) {
                            kartGrid.add(cartItems.get(i));
                        }
                        kartGrid.setLayout((new GridLayout(0,3,20,20)));
                        initProducts();
                        JOptionPane.showMessageDialog(null, "Products removed from cart succesfully", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "You must be logged in to remove products from your cart", "Error", JOptionPane.ERROR_MESSAGE);
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
            }});
    }  
    
    private void initProducts(){
        productScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productGrid.removeAll();
        products = DBget.getAllProducts();
        items = new LinkedList<>();
        for (int i = 0; i < products.size(); i++) {
            items.add(new ProductItemPanel(products.get(i)));
            productGrid.add(items.get(i));
        }
        productGrid.setLayout(new GridLayout(0,3,20,20));
    }

    private void initCart(){
        kartPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cartItems = new LinkedList<>();
        cartList = new LinkedList<>();
        kartGrid.setLayout(new GridLayout(0,3,20,20));
    }

    public static void changeColors(Color color){
        MainFrame.color = color;
        if (color != null) {
            SidePanel.setBackground(color);
            addToKartButton.changeButtonColor(color.brighter(), color.darker());
            changeColorButton.changeButtonColor(color.brighter(), color.darker());
            productsButton.changeButtonColor(color.brighter(), color.darker());
            modifyUserButton.changeButtonColor(color.brighter(), color.darker());
            connectButton.changeButtonColor(color.brighter(), color.darker());
            createNewAcountButton.changeButtonColor(color.brighter(), color.darker());
            settingsButton.changeButtonColor(color.brighter(), color.darker());
            removeFromKartButton.changeButtonColor(color.brighter(), color.darker());
            payButton.changeButtonColor(color.brighter(), color.darker());
            exitButton.changeButtonColor(color.brighter(), color.darker());
            homeButton.changeButtonColor(color.brighter(), color.darker());
            kartButton.changeButtonColor(color.brighter(), color.darker());
            logOutButton.changeButtonColor(color.brighter(), color.darker());
            signOutButton.changeButtonColor(color.brighter(), color.darker());
            signUpButton.changeButtonColor(color.brighter(), color.darker());
            uploadPicButton.changeButtonColor(color.brighter(), color.darker());
            viewDetailsButton.changeButtonColor(color.brighter(), color.darker());
            cancelSignUpButton.changeButtonColor(color.brighter(), color.darker());
            uploadPicButton.changeButtonColor(color.brighter(), color.darker());
        }
    }

    public static void updateFrame(){
        SwingUtilities.updateComponentTreeUI(SwingUtilities.getRootPane(MainPanel));
    }

    private void initLayout() {

        initVar();

        setUndecorated(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        framePanel.setBackground(new java.awt.Color(255, 255, 255));
        framePanel.setLayout(new java.awt.BorderLayout());

        SidePanel.setBackground(new java.awt.Color(153, 0, 153));

        homeButton.setPreferredSize(new java.awt.Dimension(73, 30));

        productsButton.setPreferredSize(new java.awt.Dimension(73, 30));

        kartButton.setPreferredSize(new java.awt.Dimension(73, 30));

        imageLabel.setPreferredSize(new java.awt.Dimension(60, 60));

        logOutButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addGap(157, 157, 157)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
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

        settingsButton.setPreferredSize(new java.awt.Dimension(30, 30));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                        .addComponent(seatchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                        .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(titleLabel))
                    .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleSeparator)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(seatchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        MainPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 160));

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
                .addContainerGap(223, Short.MAX_VALUE))
        );
        signUpDataPanelLayout.setVerticalGroup(
            signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpDataPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
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
                    .addComponent(signUpDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(0, 238, Short.MAX_VALUE)))
                .addContainerGap())
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

        modifyUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        loggedUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedPhoneNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedcreationDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout loggedInPanelLayout = new javax.swing.GroupLayout(loggedInPanel);
        loggedInPanel.setLayout(loggedInPanelLayout);
        loggedInPanelLayout.setHorizontalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(loggedUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loggedEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loggedPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(loggedcreationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        loggedInPanelLayout.setVerticalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loggedUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loggedEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loggedInPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(loggedInPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(loggedPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loggedcreationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(61, Short.MAX_VALUE))))
        );

        acountMgmtTabs.addTab("tab3", loggedInPanel);

        menuTabs.addTab("tab5", acountMgmtTabs);

        productsPanel.setBackground(new java.awt.Color(255, 255, 255));

        productTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        productTablePanel.setLayout(new java.awt.BorderLayout());

        productScroll.setBackground(new java.awt.Color(255, 255, 255));
        productScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout productGridLayout = new javax.swing.GroupLayout(productGrid);
        productGrid.setLayout(productGridLayout);
        productGridLayout.setHorizontalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        productGridLayout.setVerticalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        productScroll.setViewportView(productGrid);

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

        addToKartButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout productsActionsPanelLayout = new javax.swing.GroupLayout(productsActionsPanel);
        productsActionsPanel.setLayout(productsActionsPanelLayout);
        productsActionsPanelLayout.setHorizontalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsActionsPanelLayout.createSequentialGroup()
                .addContainerGap(825, Short.MAX_VALUE)
                .addComponent(addToKartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        productsActionsPanelLayout.setVerticalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(addToKartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(786, Short.MAX_VALUE)
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

        javax.swing.GroupLayout kartGridLayout = new javax.swing.GroupLayout(kartGrid);
        kartGrid.setLayout(kartGridLayout);
        kartGridLayout.setHorizontalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        kartGridLayout.setVerticalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        kartPane.setViewportView(kartGrid);

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
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(kartTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab3", kartPanel);

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
    
    public static void startMainFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
                changeColors(color);
            }
        });
    }
    
    private static MoujaButton addToKartButton;
    private static MoujaButton changeColorButton;
    private static MoujaButton productsButton;
    private static MoujaButton modifyUserButton;
    private static MoujaButton connectButton;
    private static MoujaButton createNewAcountButton;
    private static MoujaButton settingsButton;
    private static MoujaButton removeFromKartButton;
    private static MoujaButton payButton;
    private static MoujaButton exitButton;
    private static MoujaButton homeButton;
    private static MoujaButton kartButton;
    private static MoujaButton logOutButton;
    private static MoujaButton signOutButton;
    private static MoujaButton signUpButton;
    private static MoujaButton uploadPicButton;
    private static MoujaButton viewDetailsButton;
    private static MoujaButton cancelSignUpButton;

    private JPanel Header;
    private static JPanel MainPanel;
    private JPanel framePanel;
    private static JPanel SidePanel;
    private JPanel kartPanel;
    private JPanel kartSpacerPanel;
    private JPanel kartTablePanel;
    private JPanel productSpacerPanel;
    private JPanel productTablePanel;
    private JPanel productsActionsPanel;
    private JPanel productsPanel;
    private JPanel seatchPanel;
    private JPanel signInPanel;
    private JPanel signUpDataPanel;
    private JPanel signUpPanel;
    private JPanel kartGrid;
    private JPanel productGrid;
    private JPanel kartActionsPanel;
    private JPanel loggedInPanel;

    private JLabel emailLabel;
    private JLabel imageLabel;
    private JLabel loggedEmail;
    private JLabel loggedInPictureHolder;
    private JLabel loggedPhoneNumber;
    private JLabel loggedUserName;
    private JLabel loggedcreationDate;
    private JLabel loginLabel;
    private JLabel loginSignInLabel;
    private JLabel passwordLabel;
    private JLabel passwordSignInLabel;
    private JLabel phoneLabel;
    private JLabel retypePasswordLabel;
    private JLabel searchIcon;
    private JLabel signInPuctureHolder;
    private JLabel titleLabel;
    private JLabel uploadPictureHolder;

    private JTabbedPane acountMgmtTabs;
    private JTextField authLoginTextField;
    private JPasswordField authPasswordField;
    private JTextField emailTextField;
    private JScrollPane kartPane;
    
    private JTextField loginTextField;
    private JTabbedPane menuTabs;
    private JPasswordField passwordField;
    private JTextField phoneNumberTextField;
    private JScrollPane productScroll;
    private JPasswordField reTypePasswordField;
    private JTextField searchBar;
    private JSeparator titleSeparator;
}