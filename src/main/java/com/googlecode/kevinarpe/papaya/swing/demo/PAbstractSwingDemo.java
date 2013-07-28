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

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.googlecode.kevinarpe.papaya.SystemUtils;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

public abstract class PAbstractSwingDemo
implements Runnable {

    public static void demoMain(final PAbstractSwingDemo demo)
    throws Exception {
        ObjectArgs.checkNotNull(demo, "demo");
        
        boolean uimOk = false;
        if (SystemUtils.OperatingSystemCategory.UNIX.isCurrent()) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                uimOk = true;
            }
            catch (Exception e) {
                // ignore
            }
        }
        if (!uimOk) {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
            
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    demo.run();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected PAbstractSwingDemo() {
    }
    
    @Override
    public void run() {
        JFrame frame = new JFrame("Sample Window Title for Swing Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent comp = createContentPane();
        comp.setOpaque(true);  // content panes must be opaque
        frame.setContentPane(comp);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    protected abstract JComponent createContentPane();
}
