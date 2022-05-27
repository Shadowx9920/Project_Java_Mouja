package com.example.GUI.Components;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

import com.example.DataBase.DBset;
import com.example.GUI.Components.Buttons.MoujaButton;
import com.example.GUI.JForms.AddFournisseurFrame;

public class FournisseurDataPanel extends javax.swing.JPanel {

    public FournisseurDataPanel(Color color) {

        initComponents();

        changeColors(color);

        addProvider.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/plus.png")));
        deleteProvider.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/bin.png")));
        modifyProvider.setIcon(new javax.swing.ImageIcon(
            getClass().getResource("/com/example/GUI/resources/black_icons/edit.png")));

        FournisseurTableModel model = new FournisseurTableModel();
        jTable1.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );

        deleteProvider.addActionListener(e -> {
            int[] rows = jTable1.getSelectedRows();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int row : rows) {
                    if (i == row) {
                        DBset.delFournisseur((int)model.getValueAt(i, 0));
                    }
                }
            }
            updateFrame();
        });

        addProvider.addActionListener(e -> {
            AddFournisseurFrame.startAddFournisseurFrame(color);
        });

        modifyProvider.addActionListener(e -> {
            AddFournisseurFrame.startAddFournisseurFrame((int)jTable1.getValueAt(jTable1.getSelectedRow(), 0),color);
        });
    }

    public void changeColors(Color color) {
        deleteProvider.changeButtonColor(color.brighter(), color.darker());
        addProvider.changeButtonColor(color.brighter(), color.darker());
        modifyProvider.changeButtonColor(color.brighter(), color.darker());
    }

    public static void updateFrame(){
        FournisseurTableModel model = new FournisseurTableModel();
        jTable1.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    }

    private void initComponents() {

        fournisseurPanel = new javax.swing.JPanel();
        fournisseurTablePanel = new javax.swing.JPanel();
        fournisseurScroll = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        FournisseurSpacerPanel = new javax.swing.JPanel();
        fournisseurActionsPanel = new javax.swing.JPanel();
        addProvider = new MoujaButton("",30,30,Color.RED,Color.WHITE);
        deleteProvider = new MoujaButton("",30,30,Color.RED,Color.WHITE);
        modifyProvider = new MoujaButton("", 30, 30, Color.RED, Color.WHITE);

        fournisseurTablePanel.setLayout(new java.awt.BorderLayout());

        fournisseurScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        fournisseurScroll.setViewportView(jTable1);

        fournisseurTablePanel.add(fournisseurScroll, java.awt.BorderLayout.CENTER);

        FournisseurSpacerPanel.setPreferredSize(new java.awt.Dimension(865, 25));

        javax.swing.GroupLayout FournisseurSpacerPanelLayout = new javax.swing.GroupLayout(FournisseurSpacerPanel);
        FournisseurSpacerPanel.setLayout(FournisseurSpacerPanelLayout);
        FournisseurSpacerPanelLayout.setHorizontalGroup(
            FournisseurSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        FournisseurSpacerPanelLayout.setVerticalGroup(
            FournisseurSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        fournisseurTablePanel.add(FournisseurSpacerPanel, java.awt.BorderLayout.PAGE_START);

        fournisseurActionsPanel.setPreferredSize(new java.awt.Dimension(865, 50));

        deleteProvider.setPreferredSize(new java.awt.Dimension(30, 30));

        addProvider.setPreferredSize(new java.awt.Dimension(30, 30));

        modifyProvider.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout fournisseurActionsPanelLayout = new javax.swing.GroupLayout(fournisseurActionsPanel);
        fournisseurActionsPanel.setLayout(fournisseurActionsPanelLayout);
        fournisseurActionsPanelLayout.setHorizontalGroup(
            fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fournisseurActionsPanelLayout.createSequentialGroup()
                .addContainerGap(753, Short.MAX_VALUE)
                .addComponent(addProvider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyProvider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteProvider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fournisseurActionsPanelLayout.setVerticalGroup(
            fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fournisseurActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteProvider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProvider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyProvider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        fournisseurTablePanel.add(fournisseurActionsPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout fournisseurPanelLayout = new javax.swing.GroupLayout(fournisseurPanel);
        fournisseurPanel.setLayout(fournisseurPanelLayout);
        fournisseurPanelLayout.setHorizontalGroup(
            fournisseurPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fournisseurTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        fournisseurPanelLayout.setVerticalGroup(
            fournisseurPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fournisseurPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(fournisseurTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(fournisseurPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(fournisseurPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JPanel FournisseurSpacerPanel;
    private static MoujaButton addProvider;
    private static MoujaButton deleteProvider;
    private javax.swing.JPanel fournisseurActionsPanel;
    private static javax.swing.JPanel fournisseurPanel;
    private javax.swing.JScrollPane fournisseurScroll;
    private javax.swing.JPanel fournisseurTablePanel;
    private static javax.swing.JTable jTable1; 
    private static MoujaButton modifyProvider;            
}