package com.example.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.Dimension;
import java.awt.Toolkit;

public class UserScreen extends javax.swing.JFrame {

    public UserScreen() {
        initComponents();
        logOutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Authenticate.startAuthentification();
                dispose();
            }});
        JButton[] btn = {mainButton,accountButton,dataButton,optionsButton,logOutButton};
        for (JButton jButton : btn) {
            if(jButton != logOutButton)jButton.setBackground(new java.awt.Color(15, 15, 15));
            jButton.setUI(new BasicButtonUI());
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jButton.setBackground(Color.GRAY);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (jButton == logOutButton) {
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
        JLabel homeLabel = new JLabel(new ImageIcon(getClass().getResource("/com/example/GUI/resources/home-7-32.png")));
        logoPanel.setPreferredSize(new Dimension(logoPanel.getWidth(),logoPanel.getHeight()));
        logoPanel.setLayout(new java.awt.BorderLayout());
        logoPanel.add(homeLabel,java.awt.BorderLayout.CENTER);
        mainButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/icons8-squared-menu-24.png")));
        accountButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/user-2-24.png")));
        dataButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/database-24.png")));
        optionsButton.setIcon(new ImageIcon(getClass().getResource("/com/example/GUI/resources/settings-10-24.png")));
    }

    private void initComponents() {

        sidePanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        mainButton = new javax.swing.JButton();
        accountButton = new javax.swing.JButton();
        dataButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        logOutButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        mainPanel.add(header, java.awt.BorderLayout.PAGE_START);

        footer.setBackground(new java.awt.Color(51, 51, 51));
        footer.setMaximumSize(new java.awt.Dimension(612, 100));
        footer.setMinimumSize(new java.awt.Dimension(612, 100));
        footer.setLayout(new java.awt.BorderLayout());

        logOutButton.setBackground(new java.awt.Color(51, 51, 51));
        logOutButton.setForeground(new java.awt.Color(51, 51, 51));
        logOutButton.setPreferredSize(new java.awt.Dimension(50, 50));
        footer.add(logOutButton, java.awt.BorderLayout.LINE_END);

        mainPanel.add(footer, java.awt.BorderLayout.PAGE_END);

        contentPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        mainPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }
    public static void startUserScreen() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserScreen().setVisible(true);
            }
        });
    }

    private javax.swing.JButton accountButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton dataButton;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JButton logOutButton;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JButton mainButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton optionsButton;
    private javax.swing.JPanel sidePanel;
}
