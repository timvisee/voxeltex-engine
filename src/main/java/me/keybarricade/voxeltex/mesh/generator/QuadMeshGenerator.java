package me.keybarricade.voxeltex.mesh.generator;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.mesh.RawMesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class QuadMeshGenerator extends AbstractMeshGenerator {

    // TODO: Add axis orientation option!

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
     * @param size Size.
     */
    public QuadMeshGenerator(Vector2f size) {
        this(size, Vector3fFactory.zero());
    }

    /**
     * Generate a quad mesh with the given size and offset.
     *
     * @param size Size.
     * @param offset Offset.
     */
    public QuadMeshGenerator(Vector2f size, Vector3f offset) {
        // Calculate half of the offset values
        Vector2f s = size.mul(0.5f, Vector2fFactory.identity());
        Vector3f o = offset.div(2.0f, Vector3fFactory.identity());

        // Generate the vertexes counterclockwise
        float[] vertexes = {
                // Left-bottom triangle
                o.x - s.x, o.y, o.z + s.y,
                o.x - s.x, o.y, o.z - s.y,
                o.x + s.x, o.y, o.z - s.y,

                // Right-top triangle
                o.x + s.x, o.y, o.z - s.y,
                o.x + s.x, o.y, o.z + s.y,
                o.x - s.x, o.y, o.z + s.y
        };

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
