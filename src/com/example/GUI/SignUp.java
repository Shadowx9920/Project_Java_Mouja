package com.example.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SignUp extends javax.swing.JFrame {
        BufferedImage image = null;

        public SignUp() {
                initComponents();
        }

        private void initComponents() {

                try {
                        image = ImageIO.read(new File("./src/com/example/GUI/resources/user.png"));
                } catch (IOException e1) {
                        e1.printStackTrace();
                }

                jButton1 = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                loginText = new javax.swing.JTextField();
                passwordText = new javax.swing.JPasswordField();
                ImagePanel = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel1 = new javax.swing.JLabel();
                ConfirmButton = new javax.swing.JButton();
                CancelButton = new javax.swing.JButton();

                jButton1.setText("Submit Picture");
                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Sign Up");
                setResizable(false);
                setBackground(new java.awt.Color(21, 25, 28));

                jPanel1.setBackground(new java.awt.Color(21, 25, 28));

                loginText.setFont(new java.awt.Font("Tahoma", 0, 14));

                passwordText.setFont(new java.awt.Font("Tahoma", 0, 14));

                javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
                ImagePanel.setLayout(ImagePanelLayout);
                ImagePanel.addMouseListener(new MouseInputListener() {
                        public ImageIcon resize(String imgPath) {
                                ImageIcon path = new ImageIcon(imgPath);
                                Image img = path.getImage();
                                Image newImg = img.getScaledInstance(jLabel3.getWidth() * 2, jLabel3.getHeight() * 2,
                                                Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                return image;
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                                JFileChooser file = new JFileChooser();
                                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
                                file.addChoosableFileFilter(filter);
                                int res = file.showSaveDialog(null);
                                if (res == JFileChooser.APPROVE_OPTION) {
                                        File selFile = file.getSelectedFile();
                                        String path = selFile.getAbsolutePath();
                                        jLabel3.setIcon(resize(path));
                                        ImagePanel.setBackground(getBackground());
                                        ImagePanel.repaint();
                                        ImagePanel.revalidate();
                                        pack();
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
                        }

                        @Override
                        public void mouseDragged(MouseEvent e) {
                        }

                        @Override
                        public void mouseMoved(MouseEvent e) {
                        }
                });
                ImagePanelLayout.setHorizontalGroup(
                                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(ImagePanelLayout.createSequentialGroup()
                                                                .addGap(46, 46, 46)
                                                                .addComponent(jLabel3)
                                                                .addContainerGap(50, Short.MAX_VALUE)));
                ImagePanelLayout.setVerticalGroup(
                                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(ImagePanelLayout.createSequentialGroup()
                                                                .addGap(52, 52, 52)
                                                                .addComponent(jLabel3)
                                                                .addContainerGap(64, Short.MAX_VALUE)));

                jLabel3.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/com/example/GUI/resources/user.png")));

                jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setText("Password :");

                jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("Login :");

                ConfirmButton.setText("Confirm");
                ConfirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                if (loginText.getText() == "" || passwordText.getText() == "") {
                                        return;
                                }
                                Icon icon = jLabel3.getIcon();
                                image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                                                BufferedImage.TYPE_INT_ARGB);
                                Graphics g = image.getGraphics();
                                icon.paintIcon(new JLabel(), g, 0, 0);
                                g.dispose();
                                if (DBmanagement.signUp(loginText.getText(), passwordText.getText(), image))
                                        dispose();
                        }
                });

                CancelButton.setText("Cancel");
                CancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                dispose();
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(ConfirmButton)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(CancelButton))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(ImagePanel,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel2)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                33,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(passwordText,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                150,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel1)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(loginText,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                150,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(ImagePanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(42, 42, 42)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(loginText,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(passwordText,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                47,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(CancelButton)
                                                                                .addComponent(ConfirmButton))
                                                                .addContainerGap()));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE));

                pack();
        }

        public static void createSignUpPage() {
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                }
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new SignUp().setVisible(true);
                        }
                });
        }

        private javax.swing.JButton CancelButton;
        private javax.swing.JButton ConfirmButton;
        private javax.swing.JPanel ImagePanel;
        private javax.swing.JButton jButton1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JTextField loginText;
        private javax.swing.JPasswordField passwordText;
}
