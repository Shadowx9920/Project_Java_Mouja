package com.example.GUI.Components;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoujaButton extends JButton {
    public MoujaButton(int height,int width,String color,String hovercolor) {
        super();
        setText("");
        setSize(width,height);
        setUI(new BasicButtonUI());
        setBackground(Color.decode(color));
        setBorder(null);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setBackground(Color.decode(hovercolor));
            }
            public void mouseExited(MouseEvent evt) {
                setBackground(Color.decode(color));
            }
        });
        
    }
}
