package io.itch.freebrunch;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import io.itch.freebrunch.utils.Constants;

public class AssetMgr {
    private final AssetManager manager;

    public AssetMgr() {
        manager = new AssetManager();
        manager.load(Constants.BUCKET_TEXTURE, Texture.class);
        manager.load(Constants.DROP_TEXTURE, Texture.class);
    }

    public AssetManager getManager() {
        return manager;
    }
}
