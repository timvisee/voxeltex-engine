package me.keybarricade.voxeltex.resource.engine;

import me.keybarricade.voxeltex.resource.ResourceLoader;

public class EngineResourceLoader extends ResourceLoader {

    /**
     * Relative base path.
     */
    private static final String RELATIVE_BASE_PATH = "res/voxeltex/";

    /**
     * Resource loader instance.
     */
    private static EngineResourceLoader instance;

    /**
     * Get a resource loader instance.
     *
     * @return Resource loader instance.
     */
    public static EngineResourceLoader getInstance() {
        // Return the instance if it does exist
        if(instance != null)
            return instance;

        // Create an instance, then store and return it
        instance = new EngineResourceLoader();
        return instance;
    }

    @Override
    public String getBasePath() {
        return super.getBasePath() + RELATIVE_BASE_PATH;
    }
}

