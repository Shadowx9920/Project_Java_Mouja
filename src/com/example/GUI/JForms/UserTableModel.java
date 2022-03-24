package com.example.GUI.JForms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.Accounts.User;

public class UserTableModel extends AbstractTableModel {

    private LinkedList<User> users;

    public UserTableModel(List<User> users) {

        this.users = new LinkedList<User>(users);

    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = Integer.toString(user.getId());
                break;
            case 1:
                value = user.getUsername();
                break;
            case 2:
                value = user.getPassword();
                break;
            case 3:
                value = user.getEmail();
                break;
            case 4:
                value = user.getPhoneNumber();
                break;
            case 5:
                value = user.getDate();
                break;
        }

        return value;

    }



    /* Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //....
    }
    */

    public User getUserAt(int row) {
        return users.get(row);
    }

}