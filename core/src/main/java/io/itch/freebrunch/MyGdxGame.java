package io.itch.freebrunch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.itch.freebrunch.screens.MainMenuScreen;
import io.itch.freebrunch.utils.AppPreferences;
import io.itch.freebrunch.utils.Constants;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGdxGame extends Game {
    public OrthographicCamera camera;
    private FitViewport viewport;

    public SpriteBatch batch;
    MainMenuScreen mainMenuScreen;
    public AppPreferences appPreferences;
    public AssetMgr assetMgr;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        appPreferences = new AppPreferences();
        assetMgr = new AssetMgr();
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
        batch.dispose();
        assetMgr.getManager().dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }
}