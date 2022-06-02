package com.example.GUI.JForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.example.Beans.Product;
import com.example.Beans.Accounts.User;
import com.example.DataBase.DBget;
import com.example.GUI.CurrentSession;
import com.example.GUI.DBmanagement;
import com.example.GUI.Components.FournisseurDataPanel;
import com.example.GUI.Components.ImageAvatar;
import com.example.GUI.Components.MoujaTextField;
import com.example.GUI.Components.ProductItemPanel;
import com.example.GUI.Components.UserPanel;
import com.example.GUI.Components.Buttons.MoujaButton;
import com.example.GUI.Components.SearchBar.EventCallBack;
import com.example.GUI.Components.SearchBar.EventTextField;
import com.example.GUI.Components.SearchBar.TextFieldAnimation;

import org.imgscalr.Scalr;

public class AdminControlFrame extends JFrame {

    public static LinkedList<User> users;
    public static LinkedList<Product> productList;
    public static LinkedList<UserPanel> userPanels;
    public static LinkedList<ProductItemPanel> productPanels;

    public static Color color = Color.red;

    public static FournisseurDataPanel fournisseurDataPanel = new FournisseurDataPanel(color);

    public AdminControlFrame() {
        initLayout();

        connect();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        searchBar.setVisible(false);

        userScroll.getVerticalScrollBar().setUnitIncrement(16);
        productScroll.getVerticalScrollBar().setUnitIncrement(16);

        loggedEmail.setVerticalAlignment(SwingConstants.CENTER);
        loggedEmail.setHorizontalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setVerticalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        loggedUserName.setVerticalAlignment(SwingConstants.CENTER);
        loggedUserName.setHorizontalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setVerticalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setHorizontalAlignment(SwingConstants.CENTER);

        usersGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
        productsGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        menuTabs.add("", fournisseurDataPanel);

        initIcons();
        initUsers();
        initProducts();
        initButtonListeners();

    } 

    private void initVar(){
        connectButton = new MoujaButton("Sign In",30, 75, Color.white,Color.gray);
        signOutButton = new MoujaButton("Sign Out",30, 75, Color.white,Color.gray);
        createNewAcountButton = new MoujaButton("Create New Account",30, 75, Color.white,Color.gray);
        cancelSignUpButton = new MoujaButton("Cancel",30, 75, Color.white,Color.gray);
        homeButton = new MoujaButton("Home",30, 75, Color.white,Color.gray);
        logOutButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        fournisseursButton = new MoujaButton("Fournisseurs",30, 75, Color.white,Color.gray);

        framePanel = new javax.swing.JPanel();
        SidePanel = new JPanel();
        usersButton = new MoujaButton("Users",30, 75, Color.white,Color.gray);
        productsButton = new MoujaButton("Products",30, 75, Color.white,Color.gray);
        settingsButton = new MoujaButton("",30, 75, Color.white,Color.gray);
        imageLabel = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        exitButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        titleSeparator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        seatchPanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        searchBar = new TextFieldAnimation();
        menuTabs = new javax.swing.JTabbedPane();
        acountMgmtTabs = new javax.swing.JTabbedPane();
        signUpPanel = new javax.swing.JPanel();
        signUpButton = new MoujaButton("Sign Up",30, 30, Color.white,Color.gray);
        uploadPictureHolder = new ImageAvatar();
        signUpDataPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        retypePasswordLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new MoujaTextField();
        phoneNumberTextField = new MoujaTextField();
        loginTextField = new MoujaTextField();
        uploadPicButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        passwordField = new javax.swing.JPasswordField();
        reTypePasswordField = new javax.swing.JPasswordField();
        signInPanel = new javax.swing.JPanel();
        signInPuctureHolder = new ImageAvatar();
        loginSignInLabel = new javax.swing.JLabel();
        passwordSignInLabel = new javax.swing.JLabel();
        authPasswordField = new javax.swing.JPasswordField();
        authLoginTextField = new MoujaTextField();
        usersPanel = new javax.swing.JPanel();
        usersTablePanel = new javax.swing.JPanel();
        userScroll = new javax.swing.JScrollPane();
        userSpacerPanel = new javax.swing.JPanel();
        usersActionsPanel = new javax.swing.JPanel();
        removeUserButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        addUserButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        productPanel = new javax.swing.JPanel();
        productTablePanel = new javax.swing.JPanel();
        productSpacerPanel = new javax.swing.JPanel();
        productActionsPanel = new javax.swing.JPanel();
        addProductButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        removeProductButton = new MoujaButton("",30, 60, Color.white,Color.gray);
        productScroll = new javax.swing.JScrollPane();
        //changeColorButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        loggedInPanel = new javax.swing.JPanel();
        loggedInPictureHolder = new ImageAvatar();
        modifyUserButton = new MoujaButton("",30, 30,Color.white,Color.gray);
        loggedUserName = new javax.swing.JLabel();
        loggedEmail = new javax.swing.JLabel();
        loggedPhoneNumber = new javax.swing.JLabel();
        loggedcreationDate = new javax.swing.JLabel();
        usersGrid = new javax.swing.JPanel();
        productsGrid = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
    }
    
    private void initIcons(){
        logOutButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/sign-out.png")));
        uploadPictureHolder.setImage(new javax.swing.ImageIcon(
                getClass().getResource("/com/example/GUI/resources/img/user.png")));
        signInPuctureHolder.setImage(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/user.png")));
        exitButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/cross.png")));
        homeButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/user-16.png")));
        usersButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/group.png")));
        productsButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/img/world.png")));
        settingsButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/settings-sliders.png")));
        fournisseursButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/supplier.png")));
        uploadPicButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/upload.png")));
        modifyUserButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/edit.png")));
        removeProductButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/bin.png")));
        addUserButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/add-user.png")));
        removeUserButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/bin.png")));
        addProductButton.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/plus.png")));
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
        usersButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(1);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        if(search.equals("")){
                            usersGrid.removeAll();
                            for (int i = 0; i < users.size(); i++) {
                                userPanels.add(new UserPanel(users.get(i),AdminControlFrame.color));
                                usersGrid.add(userPanels.get(i));
                            }
                            for (int i = 0; i < 3; i++) {
                                usersGrid.add(new UserPanel());
                            }
                            usersGrid.setLayout(new GridLayout(0,3,20,20));
                        }else{
                            usersGrid.removeAll();
                            for (int i = 0; i < users.size(); i++) {
                                if(users.get(i).getUsername().toLowerCase().contains(search.toLowerCase())){
                                    userPanels.add(new UserPanel(users.get(i),AdminControlFrame.color));
                                    usersGrid.add(userPanels.get(i));
                                }
                            }
                            for (int i = 0; i < 3; i++) {
                                usersGrid.add(new UserPanel());
                            }
                            usersGrid.setLayout(new GridLayout(0,3,20,20));
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        initUsers();
                    }
                });
                searchBar.setVisible(true);
            }});
        productsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(2);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        if(search.equals("")){
                            productsGrid.removeAll();
                            for (int i = 0; i < productList.size(); i++) {
                                productPanels.add(new ProductItemPanel(productList.get(i),AdminControlFrame.color));
                                productsGrid.add(productPanels.get(i));
                            }
                            for (int i = 0; i < 3; i++) {
                                productsGrid.add(new ProductItemPanel());
                            }
                            productsGrid.setLayout(new GridLayout(0,3,20,20));
                        }else{
                            productsGrid.removeAll();
                            for (int i = 0; i < productList.size(); i++) {
                                if(productList.get(i).getName().toLowerCase().contains(search.toLowerCase())){
                                    productPanels.add(new ProductItemPanel(productList.get(i),AdminControlFrame.color));
                                    productsGrid.add(productPanels.get(i));
                                }
                            }
                            for (int i = 0; i < 3; i++) {
                                productsGrid.add(new ProductItemPanel());
                            }
                            productsGrid.setLayout(new GridLayout(0,3,20,20));
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        initProducts();
                    }
                });
                searchBar.setVisible(true);
            }});

        fournisseursButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBar.setVisible(true);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        FournisseurDataPanel.searchFunction(search);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        FournisseurDataPanel.searchFunction("");
                    }
                    
                });
                menuTabs.setSelectedIndex(3);
            }});
        settingsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame.createSettingsPage(AdminControlFrame.color);
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
                                loggedInPictureHolder.setImage(new javax.swing.ImageIcon(CurrentSession.getUser().getImage()));
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
                        loggedInPictureHolder.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/example/GUI/resources/img/user.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                        MainFrame.startMainFrame(AdminControlFrame.color);
                        dispose();
                    }});

        removeUserButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LinkedList<UserPanel> toRemove = new LinkedList<>();
                            for (UserPanel userPanel : userPanels) {
                                if (userPanel.selected) {
                                    DBmanagement.removeUserByID(userPanel.user.getId());
                                    toRemove.add(userPanel);
                                    SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                                }
                            }
                            userPanels.removeAll(toRemove);
                            initUsers();
                        }});

        removeProductButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LinkedList<ProductItemPanel> toRemove = new LinkedList<>();
                            for (ProductItemPanel productPanel : productPanels) {
                                if (productPanel.selected) {
                                    DBmanagement.removeProductByID(productPanel.product.getId());
                                    toRemove.add(productPanel);
                                }
                            }
                            productPanels.removeAll(toRemove);
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                            initProducts();
                        }});

        addUserButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AddUserFrame.startAddUserFrame(AdminControlFrame.color);
                        }});

        addProductButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AddProductFrame.startAddProductFrame(AdminControlFrame.color);
                        }});
    
        modifyUserButton.addActionListener(e -> {
            ModifyUserFrame.startModifyUserFrame(CurrentSession.getAdmin(), AdminControlFrame.color);
        });
    }                    
    
    public static void initUsers(){
        userScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        usersGrid.removeAll();
        users = DBget.getAllUsers();
        userPanels = new LinkedList<>();
        for (int i = 0; i < users.size(); i++) {
            userPanels.add(new UserPanel(users.get(i),AdminControlFrame.color));
            usersGrid.add(userPanels.get(i));
        }

        for (int i = 0; i < 3; i++) {
            usersGrid.add(new UserPanel());
        }
        usersGrid.setLayout(new GridLayout(0,3,20,20));
    }

    public static void initProducts(){
        productScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productsGrid.removeAll();
        productList = DBget.getAllProducts();
        productPanels = new LinkedList<ProductItemPanel>();
        for (int i = 0; i < productList.size(); i++) {
            productPanels.add(new ProductItemPanel(productList.get(i),AdminControlFrame.color));
            productsGrid.add(productPanels.get(i));
        }
        for (int i = 0; i < 3; i++) {
            productsGrid.add(new ProductItemPanel());
        }
        productsGrid.setLayout(new GridLayout(0,3,20,20));
    }

    public static void connect(){
        loggedEmail.setText(CurrentSession.getAdmin().getEmail());
        loggedPhoneNumber.setText(CurrentSession.getAdmin().getPhoneNumber());
        loggedUserName.setText(CurrentSession.getAdmin().getUsername());
        loggedcreationDate.setText(CurrentSession.getAdmin().getDate());
        if (CurrentSession.getAdmin().getImage() != null) {
            loggedInPictureHolder.setImage(new javax.swing.ImageIcon(Scalr.resize(CurrentSession.getAdmin().getImage(), Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, loggedInPictureHolder.getWidth(), loggedInPictureHolder.getHeight())));
        }else{
            loggedInPictureHolder.setImage(new ImageIcon(AdminControlFrame.class.getResource("/com/example/GUI/resources/img/user.png")));
        }
        acountMgmtTabs.setSelectedIndex(2);
    }

    public static void changeColors(Color color){
        AdminControlFrame.color = color;
        if (color != null) {
            fournisseurDataPanel.changeColors(color);
            loggedInPictureHolder.setGradientColor1(color);
            loggedInPictureHolder.setGradientColor2(color);
            signInPuctureHolder.setGradientColor1(color);
            signInPuctureHolder.setGradientColor2(color);
            uploadPictureHolder.setGradientColor1(color);
            uploadPictureHolder.setGradientColor2(color);
            searchBar.setAnimationColor(color);
            searchBar.setCaretColor(color);
            authLoginTextField.setLineColor(color);
            authPasswordField.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e) {
                    authPasswordField.setBorder(BorderFactory.createLineBorder(color));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    authPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                }});
            passwordField.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e) {
                    passwordField.setBorder(BorderFactory.createLineBorder(color));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                }});
            reTypePasswordField.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e) {
                    reTypePasswordField.setBorder(BorderFactory.createLineBorder(color));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    reTypePasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                }});
            emailTextField.setLineColor(color);
            loginTextField.setLineColor(color);
            phoneNumberTextField.setLineColor(color);
            SidePanel.setBackground(color);
            addUserButton.changeButtonColor(color.brighter(), color.darker());
            //changeColorButton.changeButtonColor(color.brighter(), color.darker());
            usersButton.changeButtonColor(color.brighter(), color.darker());
            modifyUserButton.changeButtonColor(color.brighter(), color.darker());
            connectButton.changeButtonColor(color.brighter(), color.darker());
            createNewAcountButton.changeButtonColor(color.brighter(), color.darker());
            settingsButton.changeButtonColor(color.brighter(), color.darker());
            removeProductButton.changeButtonColor(color.brighter(), color.darker());
            addProductButton.changeButtonColor(color.brighter(), color.darker());
            exitButton.changeButtonColor(color.brighter(), color.darker());
            homeButton.changeButtonColor(color.brighter(), color.darker());
            productsButton.changeButtonColor(color.brighter(), color.darker());
            logOutButton.changeButtonColor(color.brighter(), color.darker());
            signOutButton.changeButtonColor(color.brighter(), color.darker());
            signUpButton.changeButtonColor(color.brighter(), color.darker());
            uploadPicButton.changeButtonColor(color.brighter(), color.darker());
            removeUserButton.changeButtonColor(color.brighter(), color.darker());
            cancelSignUpButton.changeButtonColor(color.brighter(), color.darker());
            uploadPicButton.changeButtonColor(color.brighter(), color.darker());
            fournisseursButton.changeButtonColor(color.brighter(), color.darker());
            initUsers();
            initProducts();
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

        framePanel.setLayout(new java.awt.BorderLayout());

        homeButton.setPreferredSize(new java.awt.Dimension(73, 30));

        usersButton.setPreferredSize(new java.awt.Dimension(73, 30));

        productsButton.setPreferredSize(new java.awt.Dimension(73, 30));

        imageLabel.setPreferredSize(new java.awt.Dimension(60, 60));

        logOutButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usersButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addComponent(fournisseursButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fournisseursButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        framePanel.add(SidePanel, java.awt.BorderLayout.LINE_START);

        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("Mouja Store");

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

        jLabel1.setText("Email :");

        jLabel2.setText("Name :");

        jLabel3.setText("Phone Number :");

        jLabel4.setText("Creation Date :");

        javax.swing.GroupLayout loggedInPanelLayout = new javax.swing.GroupLayout(loggedInPanel);
        loggedInPanel.setLayout(loggedInPanelLayout);
        loggedInPanelLayout.setHorizontalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addContainerGap(820, Short.MAX_VALUE)
                .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loggedInPanelLayout.createSequentialGroup()
                        .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(104, 104, 104))
                    .addGroup(loggedInPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(loggedUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loggedEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loggedPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loggedcreationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(456, 456, 456))
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loggedInPanelLayout.setVerticalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loggedEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedcreationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab3", loggedInPanel);

        menuTabs.addTab("tab5", acountMgmtTabs);

        usersTablePanel.setLayout(new java.awt.BorderLayout());
        userScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout productGridLayout = new javax.swing.GroupLayout(usersGrid);
        usersGrid.setLayout(productGridLayout);
        productGridLayout.setHorizontalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        productGridLayout.setVerticalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        userScroll.setViewportView(usersGrid);

        usersTablePanel.add(userScroll, java.awt.BorderLayout.CENTER);

        userSpacerPanel.setPreferredSize(new java.awt.Dimension(865, 25));

        javax.swing.GroupLayout productSpacerPanelLayout = new javax.swing.GroupLayout(userSpacerPanel);
        userSpacerPanel.setLayout(productSpacerPanelLayout);
        productSpacerPanelLayout.setHorizontalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        productSpacerPanelLayout.setVerticalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        usersTablePanel.add(userSpacerPanel, java.awt.BorderLayout.PAGE_START);

        usersActionsPanel.setPreferredSize(new java.awt.Dimension(865, 50));

        removeUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        addUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout productsActionsPanelLayout = new javax.swing.GroupLayout(usersActionsPanel);
        usersActionsPanel.setLayout(productsActionsPanelLayout);
        productsActionsPanelLayout.setHorizontalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsActionsPanelLayout.createSequentialGroup()
                .addContainerGap(786, Short.MAX_VALUE)
                .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        productsActionsPanelLayout.setVerticalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        usersTablePanel.add(usersActionsPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usersTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usersPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(usersTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab1", usersPanel);

        productTablePanel.setLayout(new java.awt.BorderLayout());

        productSpacerPanel.setPreferredSize(new java.awt.Dimension(895, 25));

        javax.swing.GroupLayout kartSpacerPanelLayout = new javax.swing.GroupLayout(productSpacerPanel);
        productSpacerPanel.setLayout(kartSpacerPanelLayout);
        kartSpacerPanelLayout.setHorizontalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        kartSpacerPanelLayout.setVerticalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        productTablePanel.add(productSpacerPanel, java.awt.BorderLayout.PAGE_START);

        productActionsPanel.setPreferredSize(new java.awt.Dimension(895, 50));

        addProductButton.setPreferredSize(new java.awt.Dimension(30, 30));

        removeProductButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout kartActionsPanelLayout = new javax.swing.GroupLayout(productActionsPanel);
        productActionsPanel.setLayout(kartActionsPanelLayout);
        kartActionsPanelLayout.setHorizontalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartActionsPanelLayout.createSequentialGroup()
                .addContainerGap(786, Short.MAX_VALUE)
                .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kartActionsPanelLayout.setVerticalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kartActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeProductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        productTablePanel.add(productActionsPanel, java.awt.BorderLayout.PAGE_END);

        productScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout kartGridLayout = new javax.swing.GroupLayout(productsGrid);
        productsGrid.setLayout(kartGridLayout);
        kartGridLayout.setHorizontalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        kartGridLayout.setVerticalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        productScroll.setViewportView(productsGrid);

        productTablePanel.add(productScroll, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout kartPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(kartPanelLayout);
        kartPanelLayout.setHorizontalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productTablePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        kartPanelLayout.setVerticalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartPanelLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(productTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab3", productPanel);

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
    
    public static void startAdminControlFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminControlFrame().setVisible(true);
                changeColors(color);
            }
        });
    }

    private static MoujaButton addUserButton;
    private static MoujaButton usersButton;
    private static MoujaButton modifyUserButton;
    private static MoujaButton connectButton;
    private static MoujaButton createNewAcountButton;
    private static MoujaButton settingsButton;
    private static MoujaButton removeProductButton;
    private static MoujaButton addProductButton;
    private static MoujaButton exitButton;
    private static MoujaButton homeButton;
    private static MoujaButton productsButton;
    private static MoujaButton logOutButton;
    private static MoujaButton signOutButton;
    private static MoujaButton signUpButton;
    private static MoujaButton uploadPicButton;
    private static MoujaButton removeUserButton;
    private static MoujaButton cancelSignUpButton;
    private static MoujaButton fournisseursButton;

    private JPanel Header;
    private static JPanel MainPanel;
    private JPanel framePanel;
    private static JPanel SidePanel;
    private JPanel productPanel;
    private JPanel productSpacerPanel;
    private JPanel productTablePanel;
    private JPanel userSpacerPanel;
    private JPanel usersTablePanel;
    private JPanel usersActionsPanel;
    private JPanel usersPanel;
    private JPanel seatchPanel;
    private JPanel signInPanel;
    private JPanel signUpDataPanel;
    private JPanel signUpPanel;
    private static JPanel productsGrid;
    private static JPanel usersGrid;
    private JPanel productActionsPanel;
    private JPanel loggedInPanel;

    private JLabel emailLabel;
    private JLabel imageLabel;
    private static JLabel loggedEmail;
    private static ImageAvatar loggedInPictureHolder;
    private static JLabel loggedPhoneNumber;
    private static JLabel loggedUserName;
    private static JLabel loggedcreationDate;
    private JLabel loginLabel;
    private JLabel loginSignInLabel;
    private JLabel passwordLabel;
    private JLabel passwordSignInLabel;
    private JLabel phoneLabel;
    private JLabel retypePasswordLabel;
    private JLabel searchIcon;
    private static ImageAvatar signInPuctureHolder;
    private JLabel titleLabel;
    private static ImageAvatar uploadPictureHolder;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;

    private static JTabbedPane acountMgmtTabs;
    private static MoujaTextField authLoginTextField;
    private static JPasswordField authPasswordField;
    private static MoujaTextField emailTextField;
    private static JScrollPane productScroll;
    
    private static MoujaTextField loginTextField;
    private JTabbedPane menuTabs;
    private static JPasswordField passwordField;
    private static MoujaTextField phoneNumberTextField;
    private static JScrollPane userScroll;
    private static JPasswordField reTypePasswordField;
    private static TextFieldAnimation searchBar;
    private JSeparator titleSeparator;
}
