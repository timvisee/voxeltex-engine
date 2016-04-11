package com.timvisee.keybarricade.game.asset;

import com.timvisee.voxeltex.resource.ResourceLoader;

public class GameAssetLoader extends ResourceLoader {

    /**
     * Relative base path.
     */
    private static final String RELATIVE_BASE_PATH = "res/game/assets/";

    /**
     * Resource loader instance.
     */
    private static GameAssetLoader instance;

    /**
     * Get a resource loader instance.
     *
     * @return Resource loader instance.
     */
    public static GameAssetLoader getInstance() {
        // Return the instance if it does exist
        if(instance != null)
            return instance;

        // Create an instance, then store and return it
        instance = new GameAssetLoader();
        return instance;
    }

    @Override
    public String getBasePath() {
        return super.getBasePath() + RELATIVE_BASE_PATH;
    }
}
