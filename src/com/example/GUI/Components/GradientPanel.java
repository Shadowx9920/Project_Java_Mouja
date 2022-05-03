package com.example.GUI.Components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GradientPanel extends JPanel {

    Color color = Color.BLACK;

    public void changeColors(Color color){
        this.color = color;
        SwingUtilities.updateComponentTreeUI(GradientPanel.this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color1 = this.color;
        Color color2 = Color.WHITE;
        GradientPaint gp = new GradientPaint(200, 200, color1, w, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}