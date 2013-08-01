package com.googlecode.kevinarpe.papaya.swing.demo;

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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;

import com.googlecode.kevinarpe.papaya.ZipUtils;
import com.googlecode.kevinarpe.papaya.swing.PSwingUtils;
import com.googlecode.kevinarpe.papaya.swing.PSvgIcon;
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
        try {
//            final String filePath = "/home/kca/saveme/oxygen-icons-4.10.5/scalable/actions/window-close.svgz";
            final String filePath = "/home/kca/saveme/oxygen-icons-4.10.5/scalable/actions/zoom-fit-best.svgz";
            InputStream in = ZipUtils.createUnzipInputStream(new File(filePath));
            TranscoderInput trin = new TranscoderInput(in);
            PSvgIcon icon = new PSvgIcon(trin, 64, 64);
            label.setIcon(icon);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        catch (TranscoderException e) {
            e.printStackTrace();
            System.exit(1);
        }
        childPanel.add(label, BorderLayout.LINE_START);
        
        JTextField textField = new JTextField("Some sample text");
        childPanel.add(textField, BorderLayout.CENTER);
        label.setLabelFor(textField);

        return parentPanel;
    }
}
