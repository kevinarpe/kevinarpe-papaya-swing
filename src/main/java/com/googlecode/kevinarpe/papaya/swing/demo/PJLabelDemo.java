package com.googlecode.kevinarpe.papaya.swing.demo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.googlecode.kevinarpe.papaya.swing.PSwingUtils;
import com.googlecode.kevinarpe.papaya.swing.widget.PJLabel;

public class PJLabelDemo
extends PAbstractSwingDemo {
    
    public static final void main(String[] argArr)
    throws Exception {
        PAbstractSwingDemo.demoMain(new PJLabelDemo());
    }

    @Override
    protected JComponent createContentPane() {
        JPanel parentPanel = new JPanel();
        BorderLayout parentLayout = new BorderLayout(10, 10);
        parentPanel.setLayout(parentLayout);
        
        JPanel childPanel = new JPanel();
        BorderLayout childLayout = new BorderLayout(10, 10);
        childPanel.setLayout(childLayout);
        parentPanel.add(childPanel, BorderLayout.PAGE_START);
        
        JButton filler = new JButton("Filler -- Initial Focus");
        parentPanel.add(filler, BorderLayout.CENTER);
        PSwingUtils.requestFocusAfterNextShow(filler);
        
        PJLabel label = new PJLabel("Sample\n&&\n&Example:");
        childPanel.add(label, BorderLayout.LINE_START);
        
        JTextField textField = new JTextField("Some sample text");
        childPanel.add(textField, BorderLayout.CENTER);
        label.setLabelFor(textField);

        return parentPanel;
    }
}
