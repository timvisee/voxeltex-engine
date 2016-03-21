package me.keybarricade.voxeltex.mesh.generator;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.mesh.RawMesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class QuadMeshGenerator extends AbstractMeshGenerator {

    /**
     * Quad orientation on the X axis.
     */
    public static final int ORIENTATION_X = 1;

    /**
     * Quad orientation on the Y axis.
     */
    public static final int ORIENTATION_Y = 2;

    /**
     * Quad orientation on the Z axis.
     */
    public static final int ORIENTATION_Z = 3;

    /**
     * Default quad orientation.
     */
    private static final int DEFAULT_ORIENTATION = ORIENTATION_Y;

    /**
     * The default size of the quad.
     */
    private static final float DEFAULT_SIZE = 1.0f;

    /**
     * Raw mesh instance containing the generated mesh.
     */
    private RawMesh raw;

    /**
     * Generate a quad mesh with the default size.
     */
    public QuadMeshGenerator() {
        this(DEFAULT_SIZE);
    }

    /**
     * Generate a quad mesh with the default size.
     *
     * @param orientation Quad orientation.
     */
    public QuadMeshGenerator(int orientation) {
        this(orientation, DEFAULT_SIZE);
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param size Size on both axis.
     */
    public QuadMeshGenerator(float size) {
        this(new Vector2f(size));
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param orientation Quad orientation.
     * @param size Size on both axis.
     */
    public QuadMeshGenerator(int orientation, float size) {
        this(orientation, new Vector2f(size));
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param size Size.
     */
    public QuadMeshGenerator(Vector2f size) {
        this(size, Vector3fFactory.zero());
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param orientation Quad orientation.
     * @param size Size.
     */
    public QuadMeshGenerator(int orientation, Vector2f size) {
        this(orientation, size, Vector3fFactory.zero());
    }

    /**
     * Generate a quad mesh with the given size and offset.
     *
     * @param size Size.
     * @param offset Offset.
     */
    public QuadMeshGenerator(Vector2f size, Vector3f offset) {
        this(DEFAULT_ORIENTATION, size, offset);
    }

    /**
     * Generate a quad mesh with the given size and offset.
     *
     * @param orientation Quad orientation.
     * @param size Size.
     * @param offset Offset.
     */
    public QuadMeshGenerator(int orientation, Vector2f size, Vector3f offset) {
        // Generate and store the quad vertexes
        float[] vertexes = generateVertexes(orientation, size, offset);

        // Generate and store the quad vertexes
        float[] normals = generateNormals(orientation);

        // Generate the texture coordinates in the same order
        float[] textures = generateTextures();

        // Create and store the raw mesh
        this.raw = new RawMesh(vertexes, normals, textures);
    }

    @Override
    public RawMesh getRawMesh() {
        return this.raw;
    }

    /**
     * Generate the vertexes for a quad.
     *
     * @param orientation Quad orientation.
     * @param size Quad size.
     * @param offset Quad offset.
     *
     * @return Quad vertexes.
     */
    public static float[] generateVertexes(int orientation, Vector2f size, Vector3f offset) {
        // Calculate half of the offset values
        Vector2f halfSize = size.mul(0.5f, Vector2fFactory.identity());

        // Generate the vertexes for the quad based on the specified orientation and return them
        switch (orientation) {
            case ORIENTATION_X:
                return new float[]{
                        // Left-bottom triangle
                        offset.x, offset.y - halfSize.x, offset.z + halfSize.y,
                        offset.x, offset.y - halfSize.x, offset.z - halfSize.y,
                        offset.x, offset.y + halfSize.x, offset.z - halfSize.y,

                        // Right-top triangle
                        offset.x, offset.y + halfSize.x, offset.z - halfSize.y,
                        offset.x, offset.y + halfSize.x, offset.z + halfSize.y,
                        offset.x, offset.y - halfSize.x, offset.z + halfSize.y
                };

            case ORIENTATION_Y:
                return new float[]{
                        // Left-bottom triangle
                        offset.x - halfSize.x, offset.y, offset.z + halfSize.y,
                        offset.x - halfSize.x, offset.y, offset.z - halfSize.y,
                        offset.x + halfSize.x, offset.y, offset.z - halfSize.y,

                        // Right-top triangle
                        offset.x + halfSize.x, offset.y, offset.z - halfSize.y,
                        offset.x + halfSize.x, offset.y, offset.z + halfSize.y,
                        offset.x - halfSize.x, offset.y, offset.z + halfSize.y
                };

            case ORIENTATION_Z:
                return new float[]{
                        // Left-bottom triangle
                        offset.x - halfSize.x, offset.y + halfSize.y, offset.z,
                        offset.x - halfSize.x, offset.y - halfSize.y, offset.z,
                        offset.x + halfSize.x, offset.y - halfSize.y, offset.z,

                        // Right-top triangle
                        offset.x + halfSize.x, offset.y - halfSize.y, offset.z,
                        offset.x + halfSize.x, offset.y + halfSize.y, offset.z,
                        offset.x - halfSize.x, offset.y + halfSize.y, offset.z
                };
        }

        // Invalid orientation, throw exception
        throw new RuntimeException("Invalid quad orientation");
    }

    /**
     * Generate the normals for a quad.
     *
     * @param orientation Quad orientation.
     *
     * @return Quad normals.
     */
    public static float[] generateNormals(int orientation) {
        // Generate the vertexes for the quad based on the specified orientation and return them
        switch (orientation) {
            case ORIENTATION_X:
                return new float[]{
                        // Left-bottom triangle
                        1, 0, 0,
                        1, 0, 0,
                        1, 0, 0,

                        // Right-top triangle
                        1, 0, 0,
                        1, 0, 0,
                        1, 0, 0
                };

            case ORIENTATION_Y:
                return new float[]{
                        // Left-bottom triangle
                        0, 1, 0,
                        0, 1, 0,
                        0, 1, 0,

                        // Right-top triangle
                        0, 1, 0,
                        0, 1, 0,
                        0, 1, 0
                };

            case ORIENTATION_Z:
                return new float[]{
                        // Left-bottom triangle
                        0, 0, 1,
                        0, 0, 1,
                        0, 0, 1,

                        // Right-top triangle
                        0, 0, 1,
                        0, 0, 1,
                        0, 0, 1
                };
        }

        // Invalid orientation, throw exception
        throw new RuntimeException("Invalid quad orientation");
    }

    /**
     * Generate the texture coordinates for a quad mesh.
     *
     * @return Textures.
     */
    public static float[] generateTextures() {
        return new float[]{
                // Left-bottom triangle
                0, 1,
                0, 0,
                1, 0,

                // Right-top triangle
                1, 0,
                1, 1,
                0, 1
        };
    }
}
