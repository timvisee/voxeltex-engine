package me.keybarricade.voxeltex.model;

import me.keybarricade.voxeltex.mesh.RawMesh;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public List<Vector3f> vertexes = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Face> faces = new ArrayList<>();

    public Model() {

    }

    /**
     * Add a vertex.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public void addVertex(float x, float y, float z) {
        addVertex(new Vector3f(x, y, z));
    }

    /**
     * Add a vertex.
     *
     * @param vertex Vertex coordinates.
     */
    private void addVertex(Vector3f vertex) {
        this.vertexes.add(vertex);
    }

    /**
     * Add a normal.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public void addNormal(float x, float y, float z) {
        addNormal(new Vector3f(x, y, z));
    }

    /**
     * Add a normal.
     *
     * @param normal Normal coordinates.
     */
    private void addNormal(Vector3f normal) {
        this.normals.add(normal);
    }

    /**
     * Add a face.
     *
     * @param vertexIndexes Vertex indexes.
     * @param normalIndexes Normal indexes.
     */
    public void addFace(Vector3f vertexIndexes, Vector3f normalIndexes) {
        addFace(new Face(vertexIndexes, normalIndexes));
    }

    /**
     * Add a face.
     *
     * @param face The face.
     */
    private void addFace(Face face) {
        this.faces.add(face);
    }

    public RawMesh toRawMesh() {
        List<Vector3f> newVertexes = new ArrayList<>();
        List<Vector3f> newNormals = new ArrayList<>();

        for(Face face : this.faces) {
            newVertexes.add(this.vertexes.get((int) face.getVertex().x - 1));
            newVertexes.add(this.vertexes.get((int) face.getVertex().y - 1));
            newVertexes.add(this.vertexes.get((int) face.getVertex().z - 1));

            newNormals.add(this.normals.get((int) face.getNormal().x - 1));
            newNormals.add(this.normals.get((int) face.getNormal().y - 1));
            newNormals.add(this.normals.get((int) face.getNormal().z - 1));
        }


        int vertexSize = newVertexes.size() * 3;

        float[] vertexes = new float[vertexSize];

        for(int i = 0; i < newVertexes.size(); i++) {
            // Get the vertex
            Vector3f vertex = newVertexes.get(i);

            // Set the vertex coordinates in the array
            vertexes[i * 3] = vertex.x;
            vertexes[i * 3 + 1] = vertex.y;
            vertexes[i * 3 + 2] = vertex.z;
        }

        // Create and return the raw mesh
        return new RawMesh(vertexes);
    }
}
