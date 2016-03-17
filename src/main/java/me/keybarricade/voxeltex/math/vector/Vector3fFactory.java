package me.keybarricade.voxeltex.math.vector;

import org.joml.Vector3f;

public class Vector3fFactory {

    /**
     * Create a zero vector instance.
     *
     * @return Zero vector instance.
     */
    public static Vector3f zero() {
        return new Vector3f(0.0f, 0.0f, 0.0f);
    }

    /**
     * Create a one vector instance.
     *
     * @return One vector instance.
     */
    public static Vector3f one() {
        return new Vector3f(1.0f, 1.0f, 1.0f);
    }
}
