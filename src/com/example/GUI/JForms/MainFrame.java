package com.example.GUI.JForms; 

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.imgscalr.Scalr;

import com.example.Beans.Commande;
import com.example.Beans.Product;
import com.example.Beans.Accounts.Compte;
import com.example.DataBase.DBget;
import com.example.GUI.CurrentSession;
import com.example.GUI.Components.ImageAvatar;
import com.example.GUI.Components.MoujaTextField;
import com.example.GUI.Components.ProductItemPanel;
import com.example.GUI.Components.Buttons.MoujaButton;
import com.example.GUI.Components.Notifications.Notification;
import com.example.GUI.Components.SearchBar.EventCallBack;
import com.example.GUI.Components.SearchBar.EventTextField;
import com.example.GUI.Components.SearchBar.TextFieldAnimation;
public class MainFrame extends JFrame {
    
    static LinkedList<Product> products;
    static LinkedList<Product> cartList;
    static LinkedList<ProductItemPanel> items;
    static LinkedList<ProductItemPanel> cartItems;

    LinkedList<Commande> commands = new LinkedList<Commande>();

    BufferedImage image = null;
    
    public static Color color = Color.red;

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

        //signInPuctureHolder.setBorder(new LineBorder(Color.BLACK, 2));

        authLoginTextField.setLabelText("Login");
        emailTextField.setLabelText("Email");
        loginTextField.setLabelText("Login");
        phoneNumberTextField.setLabelText("Phone Number");

        SidePanel.setBackground(color);

        productGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
        kartGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        initIcons(true);
        initProducts();
        initCart();
        initButtonListeners();

        SidePanel.setBackground(Color.BLACK);
    } 
    
    private void initVar(){
        uploadPicButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        connectButton = new MoujaButton("Sign In",30, 75, Color.white,Color.gray);
        signOutButton = new MoujaButton("Sign Out",30, 75, Color.white,Color.gray);
        createNewAcountButton = new MoujaButton("New Account",30, 75, Color.white,Color.gray);
        cancelSignUpButton = new MoujaButton("Cancel",30, 75, Color.white,Color.gray);
        homeButton = new MoujaButton("Home",30, 75, Color.white,Color.gray);
        logOutButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        modifyUserButton = new MoujaButton("",30, 30,Color.white,Color.gray);
        productsButton = new MoujaButton("Products",30, 75, Color.white,Color.gray);
        kartButton = new MoujaButton("Cart",30, 75, Color.white,Color.gray);
        settingsButton = new MoujaButton("",30, 75, Color.white,Color.gray);
        exitButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        signUpButton = new MoujaButton("Sign Up",30, 30, Color.white,Color.gray);
        viewDetailsButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        addToKartButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        payButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        removeFromKartButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        changeColorButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        
        searchBar = new TextFieldAnimation();
        emailTextField = new MoujaTextField();
        authPasswordField = new javax.swing.JPasswordField();
        phoneNumberTextField = new MoujaTextField();
        loginTextField = new MoujaTextField();
        passwordField = new javax.swing.JPasswordField();
        reTypePasswordField = new javax.swing.JPasswordField();
        authLoginTextField = new MoujaTextField();

        framePanel = new javax.swing.JPanel();
        SidePanel = new JPanel();
        imageLabel = new JLabel();
        MainPanel = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        titleSeparator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        seatchPanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        menuTabs = new javax.swing.JTabbedPane();
        acountMgmtTabs = new javax.swing.JTabbedPane();
        signUpPanel = new javax.swing.JPanel();
        uploadPictureHolder = new ImageAvatar();
        signUpDataPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        retypePasswordLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        
        
        signInPanel = new javax.swing.JPanel();
        signInPuctureHolder = new ImageAvatar();
        loginSignInLabel = new javax.swing.JLabel();
        passwordSignInLabel = new javax.swing.JLabel();
        productsPanel = new javax.swing.JPanel();
        productTablePanel = new javax.swing.JPanel();
        productScroll = new javax.swing.JScrollPane();
        productSpacerPanel = new javax.swing.JPanel();
        productsActionsPanel = new javax.swing.JPanel();
        kartPanel = new javax.swing.JPanel();
        kartTablePanel = new javax.swing.JPanel();
        kartSpacerPanel = new javax.swing.JPanel();
        kartActionsPanel = new javax.swing.JPanel();
        kartPane = new javax.swing.JScrollPane();
        loggedInPanel = new javax.swing.JPanel();
        loggedInPictureHolder = new ImageAvatar();
        loggedUserName = new javax.swing.JLabel();
        loggedEmail = new javax.swing.JLabel();
        loggedPhoneNumber = new javax.swing.JLabel();
        loggedcreationDate = new javax.swing.JLabel();
        productGrid = new javax.swing.JPanel();
        kartGrid = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
    }
    
    private static void initIcons(boolean isdark){
        if (isdark) {
            logOutButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/sign-out.png")));
            uploadPictureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/user-big.png")));
            signInPuctureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/user-big.png")));
            exitButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/cross.png")));
            homeButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/user-16.png")));
            productsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/world.png")));
            kartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/shopping-cart.png")));
            settingsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/settings-sliders.png")));
            uploadPicButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/upload.png")));
            modifyUserButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/edit.png")));
            addToKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/shopping-cart.png")));
            payButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/dollar.png")));
            removeFromKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/black_icons/bin.png")));
        }else{
            logOutButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/sign-out.png")));
            uploadPictureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/user-big.png")));
            signInPuctureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/user-big.png")));
            exitButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/cross.png")));
            homeButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/user-16.png")));
            productsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/world.png")));
            kartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/shopping-cart.png")));
            settingsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/settings-sliders.png")));
            uploadPicButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/upload.png")));
            modifyUserButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/edit.png")));
            addToKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/shopping-cart.png")));
            payButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/dollar.png")));
            removeFromKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/white_icons/bin.png")));
        }
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
                //searchBar.addActionListener(pActionListener);
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        
                        if(search.equals("")){
                            productGrid.removeAll();
                            for (int i = 0; i < products.size(); i++) {
                                items.add(new ProductItemPanel(products.get(i),color));
                                productGrid.add(items.get(i));
                            }
                            for (int i = 0; i < 4; i++) {
                                productGrid.add(new ProductItemPanel());
                            }
                            productGrid.setLayout(new GridLayout(0,3,20,20));
                        }else{
                            productGrid.removeAll();
                            for (int i = 0; i < products.size(); i++) {
                                if(products.get(i).getName().toLowerCase().contains(search.toLowerCase())){
                                    items.add(new ProductItemPanel(products.get(i),color));
                                    productGrid.add(items.get(i));
                                }
                            }
                            for (int i = 0; i < 4; i++) {
                                productGrid.add(new ProductItemPanel());
                            }
                            productGrid.setLayout(new GridLayout(0,3,20,20));
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        initProducts();
                    }
                });
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
                
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        if(search.equals("")){
                            kartGrid.removeAll();
                            for (int i = 0; i < cartList.size(); i++) {
                                cartItems.add(new ProductItemPanel(cartList.get(i),color));
                                kartGrid.add(cartItems.get(i));
                            }
                            for (int i = 0; i < 3; i++) {
                                kartGrid.add(new ProductItemPanel());
                            }
                            kartGrid.setLayout(new GridLayout(0,3,20,20));
                        }else{
                            kartGrid.removeAll();
                            for (int i = 0; i < cartList.size(); i++) {
                                if(cartList.get(i).getName().toLowerCase().contains(search.toLowerCase())){
                                    cartItems.add(new ProductItemPanel(cartList.get(i),color));
                                    kartGrid.add(cartItems.get(i));
                                }
                            }
                            for (int i = 0; i < 3; i++) {
                                kartGrid.add(new ProductItemPanel());
                            }
                            kartGrid.setLayout(new GridLayout(0,3,20,20));
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        initCart();
                    }
                });
                // searchBar.addActionListener(cActionListener);
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
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Account cannot be created");
                    notification.showNotification();
                    return;
                }
                if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(reTypePasswordField.getPassword()))) {
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Password and retypr password are not the same");
                    notification.showNotification();
                    return;
                }
                boolean accountCreated = Compte.signUp(loginTextField.getText(), String.valueOf(passwordField.getPassword()), image, emailTextField.getText(), phoneNumberTextField.getText());
                if (accountCreated) {
                    Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "User Created succesfully");
                    notification.showNotification();
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
                        try {
                            image = ImageIO.read(selFile);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        uploadPictureHolder.setImage(resize(path));
                        pack();
                    }
                }});
        connectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(authLoginTextField.getText().equals("") || String.valueOf(authPasswordField.getPassword()).equals("")){
                        Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Please Enter Your Information");
                        notification.showNotification();
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
                                connect();
                                Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "Logged In Successfully, Welcome " + CurrentSession.getUser().getUsername());
                                notification.showNotification();
                            }
                        }
                    }else{
                        Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Couldn't Log You In");
                        notification.showNotification();
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
                        image = null;
                        uploadPictureHolder.setImage(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/user-big.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                    }});
        logOutButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!CurrentSession.checkIfLogged()){
                            Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You are not logged in");
                            notification.showNotification();
                            return;
                        }
                        CurrentSession.logOut();
                        loggedEmail.setText("");
                        loggedPhoneNumber.setText("");
                        loggedUserName.setText("");
                        loggedcreationDate.setText("");
                        loggedInPictureHolder.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/user-big.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                        authLoginTextField.setText("");
                        authPasswordField.setText("");
                        menuTabs.setSelectedIndex(0);
                        Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "Logged Out Successfully");
                        notification.showNotification();
                    }});
        addToKartButton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(CurrentSession.checkIfLogged()){
                                if(CurrentSession.checkIfAdmin()){
                                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You are not allowed to add products to the cart because you are an admin");
                                    notification.showNotification();
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
                                    Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "Products added to cart succesfully");
                                    notification.showNotification();
                                }
                            }else{
                                Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You must be logged in to add products to your cart");
                                notification.showNotification();
                            }
                            SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        }});
        payButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentSession.checkIfLogged()){
                    if(CurrentSession.checkIfAdmin()){
                        Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You are not allowed to pay because you are an admin");
                        notification.showNotification();
                    }else{
                        if(cartList.size() == 0){
                            Notification notification = new Notification(MainFrame.this, Notification.Type.INFO, Notification.Location.BOTTOM_CENTER, "You must add products to your cart before you can pay");
                            notification.showNotification();
                        }else{
                            Commande.createNewCommande(cartList);
                            Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "You have paid succesfully");
                            notification.showNotification();
                            cartList.clear();
                            cartItems.clear();
                            kartGrid.removeAll();
                            initCart();
                        }
                    }
                }else{
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You must be logged in to Pay");
                    notification.showNotification();
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
            }});
        removeFromKartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentSession.checkIfLogged()){
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
                        Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "Products removed from cart succesfully");
                        notification.showNotification();
                }else{
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You must be logged in to remove products from your cart");
                    notification.showNotification();
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
            }});
        modifyUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentSession.checkIfLogged()){
                    ModifyUserFrame.startModifyUserFrame(CurrentSession.getUser(), color);
                }else{
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You must be logged in to modify users");
                    notification.showNotification();
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
            }});
        }  
    
    public static void connect(){
            loggedEmail.setText(CurrentSession.getUser().getEmail());
            loggedPhoneNumber.setText(CurrentSession.getUser().getPhoneNumber());
            loggedUserName.setText(CurrentSession.getUser().getUsername());
            loggedcreationDate.setText(CurrentSession.getUser().getDate());
            if (CurrentSession.getUser().getImage() != null) {
                loggedInPictureHolder.setImage(new javax.swing.ImageIcon(Scalr.resize(CurrentSession.getUser().getImage(), Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, loggedInPictureHolder.getWidth(), loggedInPictureHolder.getHeight())));
            }else{
                loggedInPictureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/com/example/GUI/resources/img/user.png")));
            }
            acountMgmtTabs.setSelectedIndex(2);
        }

    private static void initProducts(){
        productScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productGrid.removeAll();
        products = DBget.getAllProducts();
        items = new LinkedList<>();
        for (int i = 0; i < products.size(); i++) {
            items.add(new ProductItemPanel(products.get(i), color));
            productGrid.add(items.get(i));
        }

        for (int i = 0; i < 4; i++) {
            productGrid.add(new ProductItemPanel());
        }
        productGrid.setLayout(new GridLayout(0,3,20,20));
    }

    private static void initCart(){
        kartPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cartItems = new LinkedList<>();
        cartList = new LinkedList<>();
        kartGrid.setLayout(new GridLayout(0,3,20,20));
    }

    public static void changeColors(Color color){
        MainFrame.color = color;
        if (color != null) {
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
            addToKartButton.changeButtonColor(color, color);
            changeColorButton.changeButtonColor(color, color);
            productsButton.changeButtonColor(color, color);
            modifyUserButton.changeButtonColor(color, color);
            connectButton.changeButtonColor(color, color);
            createNewAcountButton.changeButtonColor(color, color);
            settingsButton.changeButtonColor(color, color);
            removeFromKartButton.changeButtonColor(color, color);
            payButton.changeButtonColor(color, color);
            exitButton.changeButtonColor(color, color);
            homeButton.changeButtonColor(color, color);
            kartButton.changeButtonColor(color, color);
            logOutButton.changeButtonColor(color, color);
            signOutButton.changeButtonColor(color, color);
            signUpButton.changeButtonColor(color, color);
            uploadPicButton.changeButtonColor(color, color);
            viewDetailsButton.changeButtonColor(color, color);
            cancelSignUpButton.changeButtonColor(color, color);
            uploadPicButton.changeButtonColor(color, color);
            initProducts();
            initCart();
            double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
            if (luminescence < 128) {
                initIcons(false);
            }else{
                initIcons(true);
            }
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
        getRootPane().setBorder(new LineBorder(Color.BLACK,2,true));

        framePanel.setLayout(new java.awt.BorderLayout());

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

        //MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Header.setBackground(new java.awt.Color(255, 255, 255));

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        //titleSeparator.setBackground(new java.awt.Color(0, 0, 0));

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("Mouja Store");

        //seatchPanel.setBackground(new java.awt.Color(255, 255, 255));

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
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signUpDataPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(loginTextField)
                                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        signUpDataPanelLayout.setVerticalGroup(
            signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpDataPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        //signInPanel.setBackground(new java.awt.Color(255, 255, 255));

        loginSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginSignInLabel.setText("Login :");
        loginSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        loginSignInLabel.setRequestFocusEnabled(false);

        passwordSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordSignInLabel.setText("Password :");
        passwordSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        passwordSignInLabel.setRequestFocusEnabled(false);

        authPasswordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        authLoginTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        connectButton.setText("Sign In");
        connectButton.setPreferredSize(new java.awt.Dimension(30, 30));

        createNewAcountButton.setText("New Account");
        createNewAcountButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout signInPanelLayout = new javax.swing.GroupLayout(signInPanel);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(0, 674, Short.MAX_VALUE)
                .addComponent(createNewAcountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(authLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(authPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(signInPuctureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        signInPanelLayout.setVerticalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(signInPuctureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(authLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(authPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
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

        //productsPanel.setBackground(new java.awt.Color(255, 255, 255));

        //productTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        productTablePanel.setLayout(new java.awt.BorderLayout());

        //productScroll.setBackground(new java.awt.Color(255, 255, 255));
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

        //productSpacerPanel.setBackground(new java.awt.Color(255, 255, 255));
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

        //productsActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
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

        //kartPanel.setBackground(new java.awt.Color(255, 255, 255));

        //kartTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        kartTablePanel.setLayout(new java.awt.BorderLayout());

        //kartSpacerPanel.setBackground(new java.awt.Color(255, 255, 255));
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

        //kartActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
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

        //kartPane.setBackground(new java.awt.Color(255, 255, 255));
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
    private static JPanel kartGrid;
    private static JPanel productGrid;
    private JPanel kartActionsPanel;
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
    private static JScrollPane kartPane;
    
    private static MoujaTextField loginTextField;
    private static JTabbedPane menuTabs;
    private static JPasswordField passwordField;
    private static MoujaTextField phoneNumberTextField;
    private static JScrollPane productScroll;
    private static JPasswordField reTypePasswordField;
    private static TextFieldAnimation searchBar;
    private static JSeparator titleSeparator;
}