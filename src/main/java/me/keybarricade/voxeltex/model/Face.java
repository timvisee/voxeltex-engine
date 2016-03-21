package me.keybarricade.voxeltex.model;

import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Vector3f;

public class Face {

    public Vector3f vertex = Vector3fFactory.identity();
    public Vector3f normal = Vector3fFactory.identity();

    /**
     * Constructor.
     *
     * @param vertex
     * @param normal
     */
    public Face(Vector3f vertex, Vector3f normal) {
        this.vertex = vertex;
        this.normal = normal;
    }

    public Vector3f getVertex() {
        return vertex;
    }

    public void setVertex(Vector3f vertex) {
        this.vertex = vertex;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
}
