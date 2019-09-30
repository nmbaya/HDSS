package org.aphrc.myapplication.Utilities.SearchSpinner.iconify.fonts;

import org.aphrc.myapplication.Utilities.SearchSpinner.iconify.Icon;
import org.aphrc.myapplication.Utilities.SearchSpinner.iconify.IconFontDescriptor;

public class MaterialModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-material.ttf";
    }

    @Override
    public Icon[] characters() {
        return MaterialIcons.values();
    }
}
