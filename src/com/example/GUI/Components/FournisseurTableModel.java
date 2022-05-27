package com.example.GUI.Components;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.example.Beans.Fournisseur;
import com.example.DataBase.DBget;

public class FournisseurTableModel extends AbstractTableModel {
    
    private final Object[][] data = {};
    
    private final String[] columnNames = { "ID", "Name", "Creation Date"};
    
    private final Class[] columnClass = { Integer.class, String.class, String.class};

    private Object[][] rowData = new Object[][] {

    };

    public FournisseurTableModel() {
        int fournisseurCount = DBget.getFournisseurCount();
        LinkedList<Fournisseur> fournisseurs = DBget.getAllFournisseurs();
        rowData = new Object[fournisseurCount][4];
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        rowData[i][j] = fournisseurs.get(i).getId();
                        break;
                    case 1:
                        rowData[i][j] = fournisseurs.get(i).getName();
                        break;
                    case 2:
                        rowData[i][j] = fournisseurs.get(i).getDate();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        rowData[rowIndex][columnIndex] = aValue;
    }
}
