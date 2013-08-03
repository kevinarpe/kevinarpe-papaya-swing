package com.googlecode.kevinarpe.papaya.swing.test;

import java.io.File;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.argument.PathArgs;
import com.googlecode.kevinarpe.papaya.exception.ClassResourceNotFoundException;
import com.googlecode.kevinarpe.papaya.exception.PathException;
import com.googlecode.kevinarpe.papaya.swing.PImmutableDimension;
import com.googlecode.kevinarpe.papaya.swing.theme.PThemeIconName;

public final class PSampleIcon {
    
    private static final String THEME_NAME = "oxygen-icons-4.10.5";
    
    public static final PSampleIcon EDIT_REDO_16x16;
    public static final PSampleIcon EDIT_REDO_22x22;
    public static final PSampleIcon EDIT_REDO_32x32;
    
    public static final List<PSampleIcon> LIST;
    
    static {
        try {
            EDIT_REDO_16x16 = new PSampleIcon(
                PImmutableDimension.getSharedFromWidthAndHeight(16, 16),
                PThemeIconName.EDIT_REDO);
            EDIT_REDO_22x22 = new PSampleIcon(
                PImmutableDimension.getSharedFromWidthAndHeight(22, 22),
                PThemeIconName.EDIT_REDO);
            EDIT_REDO_32x32 = new PSampleIcon(
                PImmutableDimension.getSharedFromWidthAndHeight(32, 32),
                PThemeIconName.EDIT_REDO);
        }
        catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
        LIST = ImmutableList.of(EDIT_REDO_16x16, EDIT_REDO_22x22, EDIT_REDO_32x32);
    }
    
    public final File filePath;
    public final String resourcePathname;
    public final PImmutableDimension imageDimension;
    public final PThemeIconName themeIconName;

    private PSampleIcon(PImmutableDimension imageDimension, PThemeIconName themeIconName)
    throws PathException, ClassResourceNotFoundException {
        this.imageDimension = ObjectArgs.checkNotNull(imageDimension, "imageDimension");
        this.themeIconName = ObjectArgs.checkNotNull(themeIconName, "themeIconName");
        String resourcePathname =
            "/" + THEME_NAME
            + "/" + imageDimension.getDescription()
            + "/" + themeIconName.context.dirName
            + "/" + themeIconName.baseFileName + ".png";
        PathArgs.checkClassResourceAsStreamExists(
            PSampleIcon.class, resourcePathname, "resourcePathname");
        this.resourcePathname = resourcePathname;
        File filePath = new File("src/test/resources" + resourcePathname);
        this.filePath = PathArgs.checkFileExists(filePath, "filePath").getAbsoluteFile();
    }
}
