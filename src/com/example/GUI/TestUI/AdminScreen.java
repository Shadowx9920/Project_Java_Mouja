package com.example.GUI.TestUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTableUI;

import com.example.GUI.DBmanagement;

import java.awt.Dimension;
import java.awt.Toolkit;

public class AdminScreen extends javax.swing.JFrame {

    public AdminScreen() {
        initComponents();
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }});
        logOutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Authenticate.startAuthentification();
                dispose();
            }});

        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp.createSignUpPage();
            }});
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DBmanagement.removeSelectedUserRows(jTable1);
            }});

        JButton[] btn = {mainButton,accountButton,dataButton,optionsButton,logOutButton,addButton,deleteButton,modifyButton,exitButton};
        for (JButton jButton : btn) {

            boolean isMainPanel = jButton == logOutButton || jButton == exitButton || jButton == addButton || jButton == deleteButton || jButton == modifyButton;
            
            jButton.setUI(new BasicButtonUI());
            if(isMainPanel) { 
                jButton.setBackground(new java.awt.Color(51, 51, 51));
            }
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {
                    jButton.setBackground(Color.GRAY);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (isMainPanel) {
                        jButton.setBackground(new java.awt.Color(51, 51, 51));
                    } else {
                        jButton.setBackground(new java.awt.Color(15, 15, 15));
                    } 
                }
            });
        }
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        JLabel homeLabel = new JLabel(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/home-4-48.png")));
        logoPanel.setPreferredSize(new Dimension(logoPanel.getWidth(),(int)(logoPanel.getHeight()*1.5)));
        logoPanel.setLayout(new java.awt.BorderLayout());
        logoPanel.add(homeLabel,java.awt.BorderLayout.CENTER);
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/example/GUI/resources/img/x-mark-24.png")));
        mainButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/icons8-squared-menu-24.png")));
        accountButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/user-2-24.png")));
        dataButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/database-24.png")));
        optionsButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/img/settings-10-24.png")));
    }
    
    private void initComponents() {
        jTable1 = new javax.swing.JTable(DBmanagement.getUsersTableModel());
        jTable1.setUI(new BasicTableUI());
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
        jTable1.setRowHeight(25);
        jTable1.enableInputMethods(false);
        sidePanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        mainButton = new javax.swing.JButton();
        accountButton = new javax.swing.JButton();
        dataButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        contentTitle = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        logOutButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidePanel.setBackground(new java.awt.Color(0, 0, 0));
        sidePanel.setMaximumSize(new java.awt.Dimension(100, 443));
        sidePanel.setMinimumSize(new java.awt.Dimension(100, 443));
        sidePanel.setPreferredSize(new java.awt.Dimension(90, 443));
        sidePanel.setLayout(new java.awt.BorderLayout());

        logoPanel.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        sidePanel.add(logoPanel, java.awt.BorderLayout.PAGE_START);

        buttonsPanel.setBackground(new java.awt.Color(15, 15, 15));
        buttonsPanel.setLayout(new java.awt.GridLayout(8, 1));

        mainButton.setBackground(new java.awt.Color(15, 15, 15));
        mainButton.setForeground(new java.awt.Color(15, 15, 15));
        buttonsPanel.add(mainButton);

        accountButton.setBackground(new java.awt.Color(15, 15, 15));
        accountButton.setForeground(new java.awt.Color(15, 15, 15));
        buttonsPanel.add(accountButton);

        dataButton.setBackground(new java.awt.Color(15, 15, 15));
        dataButton.setForeground(new java.awt.Color(15, 15, 15));
        buttonsPanel.add(dataButton);

        optionsButton.setBackground(new java.awt.Color(15, 15, 15));
        optionsButton.setForeground(new java.awt.Color(15, 15, 15));
        buttonsPanel.add(optionsButton);

        sidePanel.add(buttonsPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(sidePanel, java.awt.BorderLayout.LINE_START);

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainPanel.setLayout(new java.awt.BorderLayout());

        header.setBackground(new java.awt.Color(51, 51, 51));

        contentTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        contentTitle.setForeground(new java.awt.Color(255, 255, 255));
        contentTitle.setText("jLabel1");

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(contentTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 583, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(contentTitle)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        mainPanel.add(header, java.awt.BorderLayout.PAGE_START);

        footer.setBackground(new java.awt.Color(51, 51, 51));
        footer.setMaximumSize(new java.awt.Dimension(612, 100));
        footer.setMinimumSize(new java.awt.Dimension(612, 100));
        footer.setPreferredSize(new java.awt.Dimension(622, 100));

        logOutButton.setBackground(new java.awt.Color(51, 51, 51));
        logOutButton.setForeground(new java.awt.Color(51, 51, 51));
        logOutButton.setPreferredSize(new java.awt.Dimension(30, 30));

        addButton.setText("Add");

        modifyButton.setText("Modify");

        deleteButton.setText("Delete");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addContainerGap(530, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(modifyButton)
                    .addComponent(deleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.add(footer, java.awt.BorderLayout.PAGE_END);

        contentPanel.setBackground(new java.awt.Color(51, 51, 51));
        contentPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(jTable1);

        contentPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        mainPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }
    public static void startAdminScreen() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminScreen().setVisible(true);
            }
        });
    }

    private javax.swing.JButton accountButton;
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel contentTitle;
    private javax.swing.JButton dataButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logOutButton;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JButton mainButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton modifyButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JPanel sidePanel;
}
