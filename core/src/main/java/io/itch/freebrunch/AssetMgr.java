package io.itch.freebrunch;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import io.itch.freebrunch.utils.Constants;

public class AssetMgr {
    private final AssetManager manager;

    public AssetMgr() {
        manager = new AssetManager();
        manager.load(Constants.BUCKET_TEXTURE, Texture.class);
        manager.load(Constants.DROP_TEXTURE, Texture.class);
        manager.load(Constants.DROP_SOUND, Sound.class);
        manager.load(Constants.GAME_MUSIC, Music.class);

        // Font
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter gameFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        gameFont.fontFileName = Constants.GAME_FONT;
        gameFont.fontParameters.size = 50;
        gameFont.fontParameters.borderWidth = 5;
        gameFont.fontParameters.borderColor = Color.BLACK;
        gameFont.fontParameters.color = Color.WHITE;
        manager.load(Constants.GAME_FONT, BitmapFont.class, gameFont);
    }

    public AssetManager getManager() {
        return manager;
    }
}
