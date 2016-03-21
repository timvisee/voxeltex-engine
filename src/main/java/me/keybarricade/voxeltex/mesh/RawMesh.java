package me.keybarricade.voxeltex.mesh;

public class RawMesh {

    /**
     * Number of axis used in the vertex specification.
     */
    public static final int VERTEX_AXIS_COUNT = 3;

    /**
     * Number of axis used in the texture coordinate specification.
     */
    public static final int TEXTURE_AXIS_COUNT = 2;

    /**
     * Vertexes of this mesh.
     */
    private float[] vertexes = new float[0];

    /**
     * Texture coordinates of this mesh.
     */
    private float[] textures = new float[0];

    /**
     * Constructor.
     *
     * @param vertexes The vertexes data.
     */
    public RawMesh(float[] vertexes) {
        this(vertexes, new float[0]);
    }

    /**
     * Constructor.
     *
     * @param vertexes The vertexes data.
     * @param textures The textures data.
     */
    public RawMesh(float[] vertexes, float[] textures) {
        // Set the fields
        this.vertexes = vertexes;
        this.textures = textures;
    }

    /**
     * Get the number of vertexes in this this mesh.
     *
     * @return Vertex count.
     */
    public int getVertexCount() {
        return this.vertexes.length / VERTEX_AXIS_COUNT;
    }

    /**
     * Get all raw vertex coordinates of this mesh.
     *
     * @return Vertexes.
     */
    public float[] getVertexes() {
        return this.vertexes;
    }

    /**
     * Get all raw texture coordinates of this mesh.
     *
     * @return Textures.
     */
    public float[] getTextures() {
        return this.textures;
    }

    /**
     * Check whether this mesh has any texture data.
     *
     * @return True if this mash has texture data, false if not.
     */
    public boolean hasTextureData() {
        return this.textures.length > 0;
    }

    /**
     * Get the number of axis used for vertex data.
     *
     * @return Vertex axis count.
     */
    public int getVertexAxisCount() {
        return VERTEX_AXIS_COUNT;
    }

    /**
     * Get the number of axis used for texture data.
     *
     * @return Texture axis count.
     */
    public int getTextureAxisCount() {
        return TEXTURE_AXIS_COUNT;
    }
}
