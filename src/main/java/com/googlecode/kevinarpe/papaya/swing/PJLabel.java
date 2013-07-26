package com.googlecode.kevinarpe.papaya.swing;

import javax.swing.Icon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PJLabel
extends JLabel {

    public PJLabel() {
        super();
    }

    public PJLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public PJLabel(Icon image) {
        super(image);
    }

    public PJLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    public PJLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public PJLabel(String text) {
        super(text);
    }
    
    private String _originalText;

    @Override
    public void setText(String text) {
        PJComponentTextParser x = new PJComponentTextParser(text);
        _originalText = text;
        super.setText(x.textAfterParse);
        setDisplayedMnemonic(x.mnemonicKeyCode);
        setDisplayedMnemonicIndex(x.mnemonicIndex);
    }
    
    public String getOriginalText() {
        return _originalText;
    }
}
