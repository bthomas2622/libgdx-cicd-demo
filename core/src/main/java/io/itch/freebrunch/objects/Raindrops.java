package io.itch.freebrunch.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import io.itch.freebrunch.utils.Constants;

import java.util.Iterator;

public class Raindrops {
    Array<Rectangle> raindrops;
    Texture dropImage;
    private final float dropHeight;
    private final float dropWidth;
    Sound dropSound;

    public Raindrops() {
        raindrops = new Array<Rectangle>();
        dropImage = new Texture(Gdx.files.internal("drop.png"));
        dropWidth = dropImage.getWidth();
        dropHeight = dropImage.getHeight();
        dropSound = Gdx.audio.newSound(Gdx.files.internal("Gulp.mp3"));
    }

    public void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.width = dropWidth;
        raindrop.height = dropHeight;
        raindrop.x = MathUtils.random(0, Constants.WORLD_WIDTH - dropWidth);
        raindrop.y = Constants.WORLD_HEIGHT + dropHeight;
        raindrops.add(raindrop);
    }

    public void Draw(Batch batch) {
        for (Rectangle raindrop : raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
    }

    public int DetectCollision(Bucket bucket, float deltaTime) {
        int dropsGathered = 0;

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the
        // value our drops counter and add a sound effect.
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * deltaTime;
            if (raindrop.y + dropHeight < 0)
                iter.remove();
            if (raindrop.overlaps(bucket.getBoundingBox())) {
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
        }

        return dropsGathered;
    }

    public void dispose() {
        dropImage.dispose();
        dropSound.dispose();
    }
}
