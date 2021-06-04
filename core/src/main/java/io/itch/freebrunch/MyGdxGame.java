package io.itch.freebrunch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.itch.freebrunch.screens.GameScreen;
import io.itch.freebrunch.screens.MainMenuScreen;
import io.itch.freebrunch.utils.AppPreferences;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    MainMenuScreen mainMenuScreen;
    GameScreen gameScreen;
    public AppPreferences appPreferences;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Use LibGDX's default Arial font.
        font = new BitmapFont();
        appPreferences = new AppPreferences();
        mainMenuScreen = new MainMenuScreen(this);
        Gdx.graphics.setWindowedMode(appPreferences.getViewportWidth(), appPreferences.getViewportHeight());
        this.setScreen(mainMenuScreen);
    }

    @Override
    public void render() {
        super.render(); // important!
    }

    @Override
    public void dispose() {
        mainMenuScreen.dispose();
        if (gameScreen != null) gameScreen.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}