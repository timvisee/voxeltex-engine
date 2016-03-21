package me.keybarricade.voxeltex.model;

import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Vector3f;

public class Face {

    /**
     * Face vertex index.
     */
    public Vector3f vertex = Vector3fFactory.identity();

    /**
     * Face normal index.
     */
    public Vector3f normal = Vector3fFactory.identity();

    /**
     * Constructor.
     *
     * @param vertex Face vertex index.
     * @param normal Face normal index.
     */
    public Face(Vector3f vertex, Vector3f normal) {
        this.vertex = vertex;
        this.normal = normal;
    }

    /**
     * Get the vertex index.
     *
     * @return Vertex index.
     */
    public Vector3f getVertex() {
        return vertex;
    }

    /**
     * Set the vertex index.
     *
     * @param vertex Vertex index.
     */
    public void setVertex(Vector3f vertex) {
        this.vertex = vertex;
    }

    /**
     * Get the normal index.
     *
     * @return Normal index.
     */
    public Vector3f getNormal() {
        return normal;
    }

    /**
     * Set the normal index.
     *
     * @param normal Normal index.
     */
    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
}
