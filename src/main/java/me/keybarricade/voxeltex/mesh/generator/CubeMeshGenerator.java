package me.keybarricade.voxeltex.mesh.generator;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.mesh.RawMesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class CubeMeshGenerator extends AbstractMeshGenerator {

    /**
     * The default size of the cube.
     */
    private static final float DEFAULT_SIZE = 1.0f;

    /**
     * Raw mesh instance containing the generated mesh.
     */
    private RawMesh raw;

    /**
     * Generate a cube mesh with the default size.
     */
    public CubeMeshGenerator() {
        this(DEFAULT_SIZE);
    }

    /**
     * Generate a cube mesh with the given size.
     *
     * @param size Size on both axis.
     */
    public CubeMeshGenerator(float size) {
        this(new Vector3f(size));
    }

    /**
     * Generate a cube mesh with the given size.
     *
     * @param size Size.
     */
    public CubeMeshGenerator(Vector3f size) {
        this(size, Vector3fFactory.zero());
    }

    /**
     * Generate a cube mesh with the given size and offset.
     *
     * @param size Size.
     * @param offset Offset.
     */
    public CubeMeshGenerator(Vector3f size, Vector3f offset) {
        // Create a list to put quad meshes in for each cube face
        List<RawMesh> quadMeshes = new ArrayList<>();

        // Create a quad size and offset vector
        Vector2f quadSize = Vector2fFactory.identity();
        Vector3f quadOffset = Vector3fFactory.identity();

        // Generate a quad face for each axis, twice
        for(int i = -1; i < 2; i += 2) {
            // Determine the quad size and offset for the X axis orientation
            quadSize.set(size.y, size.z);
            quadOffset.set(0f).add(offset).add(size.x / 2.0f * i, 0f, 0f);

            // Generate the quad for this position and add the raw mesh to the list
            QuadMeshGenerator quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_X, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());

            // Determine the quad size and offset for the Y axis orientation
            quadSize.set(size.x, size.z);
            quadOffset.set(0f).add(offset).add(0f, size.y / 2.0f * i, 0f);

            // Generate the quad for this position and add the raw mesh to the list
            quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_Y, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());

            // Determine the quad size and offset for the Z axis orientation
            quadSize.set(size.x, size.y);
            quadOffset.set(0f).add(offset).add(0f, 0f, size.z / 2.0f * i);

            // Generate the quad for this position and add the raw mesh to the list
            quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_Z, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());
        }

        // Calculate the number of vertexes for each mesh, and all of them combined
        final int vertexCountSingle = quadMeshes.get(0).getVertexCount() * RawMesh.VERTEX_AXIS_COUNT; // TODO: GET THIS FROM SOMEWHERE!
        final int vertexCountAll = vertexCountSingle * quadMeshes.size();

        // Define the vertexes array
        float[] vertexes = new float[vertexCountAll];

        // Copy all vertexes from the raw meshes into the new vertexes array
        for(int i = 0; i < quadMeshes.size(); i++)
            // Get the raw mesh and copy
            System.arraycopy(quadMeshes.get(i).getVertexes(), 0, vertexes, i * vertexCountSingle, vertexCountSingle);

        // Generate the texture coordinates in the same order
        float[] textures = new float[12 * 6];
        for(int i = 0; i < 6; i++) {
            float[] quadTextures = QuadMeshGenerator.generateTextures();
            System.arraycopy(quadTextures, 0, textures, i * 12, 12);
        }

        // Create and store the raw mesh
        this.raw = new RawMesh(vertexes, textures);
    }

    @Override
    public RawMesh getRawMesh() {
        return this.raw;
    }
}
