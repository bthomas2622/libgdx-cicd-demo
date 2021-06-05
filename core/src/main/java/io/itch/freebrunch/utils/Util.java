package io.itch.freebrunch.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Util {
    public static int getHalfFontWidth(BitmapFont font, String text) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
        return (int) (glyphLayout.width / 2);
    }
}
