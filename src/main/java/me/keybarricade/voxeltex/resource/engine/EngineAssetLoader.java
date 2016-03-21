package me.keybarricade.voxeltex.resource.engine;

public class EngineAssetLoader extends EngineResourceLoader {

    /**
     * Relative base path.
     */
    private static final String RELATIVE_BASE_PATH = "assets/";

    /**
     * Resource loader instance.
     */
    private static EngineAssetLoader instance;

    /**
     * Get a resource loader instance.
     *
     * @return Resource loader instance.
     */
    public static EngineAssetLoader getInstance() {
        // Return the instance if it does exist
        if(instance != null)
            return instance;

        // Create an instance, then store and return it
        instance = new EngineAssetLoader();
        return instance;
    }

    @Override
    public String getBasePath() {
        return super.getBasePath() + RELATIVE_BASE_PATH;
    }
}

