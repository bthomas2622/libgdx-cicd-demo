package io.itch.freebrunch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import io.itch.freebrunch.MyGdxGame;
import io.itch.freebrunch.utils.Constants;
import io.itch.freebrunch.utils.Util;

public class MainMenuScreen implements Screen {
    final MyGdxGame game;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;

    public MainMenuScreen(final MyGdxGame gam) {
        this.game = gam;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/NotoSans-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.BLACK;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter); // font size 12 pixels
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        ScreenUtils.clear(0, 0, 0.2f, 1);

        font.draw(game.batch, "Welcome to MyGdxGame!!!", Constants.WORLD_WIDTH / 2f - Util.getHalfFontWidth(font, "Welcome to MyGdxGame!!!"), Constants.WORLD_HEIGHT / 2f);
        font.draw(game.batch, "Tap anywhere to begin!", Constants.WORLD_WIDTH / 2f - Util.getHalfFontWidth(font, "Tap anywhere to begin!"), Constants.WORLD_HEIGHT / 4f);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        generator.dispose();
        font.dispose();
    }

}
