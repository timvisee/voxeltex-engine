package me.keybarricade.voxeltex;

public class VoxelTex {

    /**
     * Engine name.
     */
    public static final String ENGINE_NAME = "VoxelTex";

    /**
     * Engine version name.
     */
    public static final String ENGINE_VERSION_NAME = "0.1";

    /**
     * Engine version code.
     */
    public static final int ENGINE_VERSION_CODE = 1;

    /**
     * Get the full engine name string, including the version number.
     *
     * @return Engine name string.
     */
    public static String getEngineNameFull() {
        return ENGINE_NAME + " v" + ENGINE_VERSION_NAME;
    }
}
