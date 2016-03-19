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
        // Calculate half of the offset values
        Vector2f halfSize = size.mul(0.5f, Vector2fFactory.identity());

        // Define the vertexes array
        float[] vertexes = new float[0];

        // Generate the vertexes for the quad based on the specified orientation
        switch(orientation) {
            case ORIENTATION_X:
                vertexes = new float[]{
                        // Left-bottom triangle
                        offset.x, offset.y - halfSize.x, offset.z + halfSize.y,
                        offset.x, offset.y - halfSize.x, offset.z - halfSize.y,
                        offset.x, offset.y + halfSize.x, offset.z - halfSize.y,

                        // Right-top triangle
                        offset.x, offset.y + halfSize.x, offset.z - halfSize.y,
                        offset.x, offset.y + halfSize.x, offset.z + halfSize.y,
                        offset.x, offset.y - halfSize.x, offset.z + halfSize.y
                };
                break;

            case ORIENTATION_Y:
                vertexes = new float[]{
                        // Left-bottom triangle
                        offset.x - halfSize.x, offset.y, offset.z + halfSize.y,
                        offset.x - halfSize.x, offset.y, offset.z - halfSize.y,
                        offset.x + halfSize.x, offset.y, offset.z - halfSize.y,

                        // Right-top triangle
                        offset.x + halfSize.x, offset.y, offset.z - halfSize.y,
                        offset.x + halfSize.x, offset.y, offset.z + halfSize.y,
                        offset.x - halfSize.x, offset.y, offset.z + halfSize.y
                };
                break;

            case ORIENTATION_Z:
                vertexes = new float[]{
                        // Left-bottom triangle
                        offset.x - halfSize.x, offset.y + halfSize.y, offset.z,
                        offset.x - halfSize.x, offset.y - halfSize.y, offset.z,
                        offset.x + halfSize.x, offset.y - halfSize.y, offset.z,

                        // Right-top triangle
                        offset.x + halfSize.x, offset.y - halfSize.y, offset.z,
                        offset.x + halfSize.x, offset.y + halfSize.y, offset.z,
                        offset.x - halfSize.x, offset.y + halfSize.y, offset.z
                };
                break;
        }

        // Generate the texture coordinates in the same order
        float[] textures = {
                // Left-bottom triangle
                0, 1,
                0, 0,
                1, 0,

                // Right-top triangle
                1, 0,
                1, 1,
                0, 1
        };

        // Create and store the raw mesh
        this.raw = new RawMesh(vertexes, textures);
    }

    @Override
    public RawMesh getRawMesh() {
        return this.raw;
    }
}
