package io.itch.freebrunch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.itch.freebrunch.screens.GameScreen;
import io.itch.freebrunch.screens.MainMenuScreen;
import io.itch.freebrunch.utils.AppPreferences;
import io.itch.freebrunch.utils.Constants;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGdxGame extends Game {
    public OrthographicCamera camera;
    private FitViewport viewport;

    public SpriteBatch batch;
    public BitmapFont font;
    MainMenuScreen mainMenuScreen;
    GameScreen gameScreen;
    public AppPreferences appPreferences;
    public int ScreenWidth;
    public int ScreenHeight;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Use LibGDX's default Arial font.
        font = new BitmapFont();
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        appPreferences = new AppPreferences();
        mainMenuScreen = new MainMenuScreen(this);
        Gdx.graphics.setWindowedMode(appPreferences.getResolutionWidth(), appPreferences.getResolutionHeight());
        this.setScreen(mainMenuScreen);
    }

    @Override
    public void render() {
        super.render();
        // important!
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
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }
}