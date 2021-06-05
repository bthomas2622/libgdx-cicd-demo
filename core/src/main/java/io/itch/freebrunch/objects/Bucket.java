package io.itch.freebrunch.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import io.itch.freebrunch.utils.Constants;

public class Bucket {
    private final int height;
    private final int width;
    private float x;
    private final float y = Constants.WORLD_HEIGHT / 10f;
    private final Rectangle boundingBox;
    private final Texture bucketImage;

    public Bucket(float xCentre) {
        this.bucketImage = new Texture(Gdx.files.internal("bucket.png"));;
        this.height = bucketImage.getHeight();
        this.width = bucketImage.getWidth();
        this.x = xCentre - width / 2f;
        this.boundingBox = new Rectangle(xCentre - width / 2f, y, width, height);
    }

    public void updatePosition(float xPosition) {
        checkBounds(xPosition - width / 2f);
        boundingBox.setPosition(x, y);
    }

    public void translate(boolean left, float deltaTime) {
        int movementSpeed = 200;
        if (left) {
            x -= movementSpeed * deltaTime;
        }
        else {
            x += movementSpeed * deltaTime;
        }
        checkBounds(x);
        boundingBox.setPosition(x, y);
    }

    private void checkBounds(float xIn) {
        if (xIn <= 0) {
            x = 0;
        }
        else x = Math.min(xIn, Constants.WORLD_WIDTH - width);
    }

    public void draw(Batch batch) {
        batch.draw(bucketImage, x, y, width, height);
    }

    public Rectangle getBoundingBox() { return boundingBox; }

    public void dispose() {
        bucketImage.dispose();
    }
}
