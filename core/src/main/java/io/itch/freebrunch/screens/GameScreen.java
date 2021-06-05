package io.itch.freebrunch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import io.itch.freebrunch.MyGdxGame;
import io.itch.freebrunch.objects.Bucket;
import io.itch.freebrunch.objects.Raindrops;
import io.itch.freebrunch.utils.Constants;

public class GameScreen implements Screen {

    final MyGdxGame game;

    Music rainMusic;
    long lastDropTime;
    int dropsGathered;
    private Bucket bucket;
    private Raindrops raindrops;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font;


    public GameScreen(final MyGdxGame gam) {
        this.game = gam;

        // load the drop sound effect and the rain background "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("Boba_Theme.mp3"));
        rainMusic.setLooping(true);

        bucket = new Bucket(Constants.WORLD_WIDTH / 2);
        raindrops = new Raindrops();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/NotoSans-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.BLACK;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter); // font size 12 pixels
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        raindrops.spawnRaindrop();
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // begin a new batch and draw the bucket and
        // all drops
        font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, Constants.WORLD_HEIGHT - font.getCapHeight());
        bucket.draw(game.batch);
        raindrops.Draw(game.batch);
        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            bucket.updatePosition(touchPos.x);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            bucket.translate(true, Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            bucket.translate(false, Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit();

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        dropsGathered += raindrops.DetectCollision(bucket, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        rainMusic.play();
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
        rainMusic.dispose();
        bucket.dispose();
        raindrops.dispose();
        generator.dispose();
        font.dispose();
    }

}