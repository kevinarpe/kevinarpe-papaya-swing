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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.IllegalComponentStateException;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.util.Locale;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleIcon;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleStateSet;
import javax.swing.Icon;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.XMLReader;

import com.google.common.annotations.Beta;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.IntArgs;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * A small fixed size picture, typically used to decorate components, rendered from SVG (Scalable
 * Vector Graphics) data.
 * <p>
 * This class is highly experimental and many SVG icons available in open source icon themes do not
 * render correctly with this class and throw exceptions.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see Icon
 */
@NotFullyTested
@Beta
public class PSvgIcon
implements Icon, Accessible {
    
    private BufferedImage _image;
    private final int _width;
    private final int _height;
    private String _description;
    
    /**
     * Constructs an {@link Icon} from SVG data.
     * 
     * @param input
     *        source for SVG data.  If unfamiliar with this parameter type, it can accept a wide
     *        variety of input sources: {@link Document}, {@link InputStream}, {@link Reader},
     *        {@link URI}, and {@link XMLReader}.
     * @param width
     *        integral number of pixels on X-axis to render the {@link BufferedImage}
     * @param height
     *        integral number of pixels on Y-axis to render the {@link BufferedImage}
     * 
     * @throws NullPointerException
     *         if {@code input} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code width} or {@code height} is not positive
     * @throws TranscoderException
     *         if SVG fails to render as an {@link BufferedImage}
     */
    public PSvgIcon(TranscoderInput input, int width, int height)
    throws TranscoderException {
        ObjectArgs.checkNotNull(input, "input");        
        _width = IntArgs.checkPositive(width, "width");
        _height = IntArgs.checkPositive(height, "height");
        BufferedImageTranscoder x = createBufferedImageTranscoder();
        TranscoderOutput output = null;
        x.transcode(input, output);
    }
    
    /**
     * Override this method if class {@link BufferedImageTranscoder} has also been subclassed.
     * 
     * @return new SVG transcoder
     */
    protected BufferedImageTranscoder createBufferedImageTranscoder() {
        BufferedImageTranscoder x = new BufferedImageTranscoder();
        return x;
    }
    
    /**
     * For subclasses to access members.
     */
    protected BufferedImage getBufferedImage() {
        return _image;
    }
    
    /**
     * For subclasses to access members.
     */
    protected void setBufferedImage(BufferedImage image) {
        _image = ObjectArgs.checkNotNull(image, "image");
    }
    
    /**
     * It is possible to customize the behavior of this class through subclassing and overriding
     * {@link PSvgIcon#createBufferedImageTranscoder()}.
     * 
     * @author Kevin Connor ARPE (kevinarpe@gmail.com)
     * 
     * @see PSvgIcon
     */
    protected class BufferedImageTranscoder
    extends ImageTranscoder {
        
        public BufferedImageTranscoder() {
            // Ref: http://stackoverflow.com/a/6634963/257299
            TranscodingHints hints = new TranscodingHints();
            hints.put(ImageTranscoder.KEY_WIDTH, (float) PSvgIcon.this._width);
            hints.put(ImageTranscoder.KEY_HEIGHT, (float) PSvgIcon.this._height);
            DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
            hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, domImpl);
            hints.put(
                ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI,
                SVGConstants.SVG_NAMESPACE_URI);
            hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
            hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);
            setTranscodingHints(hints);
        }

        @Override
        public BufferedImage createImage(int width, int height) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            PSvgIcon.this.setBufferedImage(image);
            return image;
        }

        @Override
        public void writeImage(BufferedImage image, TranscoderOutput output)
        throws TranscoderException {
            // empty
            @SuppressWarnings("unused")
            int dummy = 1;  // debug breakpoint
        }
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        BufferedImage image = getBufferedImage();
        ImageObserver io = null;
        g.drawImage(image, x, y, io);
    }

    @Override
    public int getIconWidth() {
        return _width;
    }

    @Override
    public int getIconHeight() {
        return _height;
    }
    
    private AccessibleSvgIcon _accessibleContext;

    @Override
    public AccessibleContext getAccessibleContext() {
        if (null == _accessibleContext) {
            _accessibleContext = new AccessibleSvgIcon();
        }
        return _accessibleContext;
    }
    
    protected class AccessibleSvgIcon
    extends AccessibleContext
    implements AccessibleIcon {
        
        // AccessibleContext

        @Override
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.ICON;
        }

        @Override
        public AccessibleStateSet getAccessibleStateSet() {
            return null;
        }

        @Override
        public int getAccessibleIndexInParent() {
            return -1;
        }

        @Override
        public int getAccessibleChildrenCount() {
            return 0;
        }

        @Override
        public Accessible getAccessibleChild(int i) {
            return null;
        }

        @Override
        public Locale getLocale()
        throws IllegalComponentStateException {
            return null;
        }

        // AccessibleIcon
        
        @Override
        public String getAccessibleIconDescription() {
            return PSvgIcon.this._description;
        }

        @Override
        public void setAccessibleIconDescription(String description) {
            PSvgIcon.this._description = description;
        }

        @Override
        public int getAccessibleIconWidth() {
            int x = PSvgIcon.this.getIconWidth();
            return x;
        }

        @Override
        public int getAccessibleIconHeight() {
            int x = PSvgIcon.this.getIconHeight();
            return x;
        }
    }
}
