package com.googlecode.kevinarpe.papaya.swing;

/*
 * #%L
 * This file is part of Papaya Swing.
 * %%
 * Copyright (C) 2013 Kevin Connor ARPE (kevinarpe@gmail.com)
 * %%
 * Papaya Swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GPL Classpath Exception:
 * This project is subject to the "Classpath" exception as provided in
 * the LICENSE file that accompanied this code.
 * 
 * Papaya Swing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Papaya Swing.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SwingSample {

    public static final void main(String[] argArr)
    throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createGui();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    protected static void createGui()
    throws Exception {
        JFrame frame = new JFrame("Sample Window Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel parentPanel = new JPanel();
        BorderLayout parentLayout = new BorderLayout(10, 10);
        parentPanel.setLayout(parentLayout);
        
//        JButton button1 = new JButton("Left");
        //LÅBEL
//        JButton button1 = new JButton("LÅBEL");
//        JButton button1 = new JButton("Låbel");
//        button1.setMnemonic(KeyEvent.VK_A);
//        button1.setDisplayedMnemonicIndex(1);
        JButton button1 = new JButton("<html><b>L</b>åbel");
        button1.setMnemonic(KeyEvent.VK_A);
        button1.setDisplayedMnemonicIndex(13);
        JButton button2 = new JButton("Right");
        
        PSwingDebug.setEnabled(PJSplitPaneProportional.class, true);
        boolean isContinuousLayoutOnResize = true;
        PJSplitPaneProportional splitPane =
            new PJSplitPaneProportional(
                JSplitPane.HORIZONTAL_SPLIT, isContinuousLayoutOnResize, button1, button2);
//        splitPane.setDoubleBuffered(true);
        splitPane.setDividerSize(2 * splitPane.getDividerSize());
        splitPane.setDividerLocation(0.33);
        parentPanel.add(splitPane, BorderLayout.CENTER);
        
        JTextField textField = new JTextField("This is a test.");
        parentPanel.add(textField, BorderLayout.PAGE_END);
        PSwingUtilities.requestFocusAfterNextShow(textField);
        
        parentPanel.setOpaque(true); //content panes must be opaque
        frame.setContentPane(parentPanel);
        
//        System.out.println(KeyStroke.getKeyStroke('A').getKeyCode());
//        System.out.println(KeyStroke.getKeyStroke("A").getKeyCode());
//        System.out.println(KeyStroke.getKeyStroke("Ä").getKeyCode());
//        System.exit(0);

        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
