package me.keybarricade.voxeltex.math.vector;

import org.joml.Vector3f;

public class Vector3fFactory {

    /**
     * Set the given vector to it's identity.
     *
     * @param dest Vector to set.
     *
     * @return The vector.
     */
    public static Vector3f identity(Vector3f dest) {
        return dest.set(0);
    }

    /**
     * Create a new identity vector.
     *
     * @return Vector.
     */
    public static Vector3f identity() {
        return zero();
    }

    /**
     * Create a zero vector instance.
     *
     * @return Zero vector instance.
     */
    public static Vector3f zero() {
        return new Vector3f(0.0f);
    }

    /**
     * Create a one vector instance.
     *
     * @return One vector instance.
     */
    public static Vector3f one() {
        return new Vector3f(1.0f);
    }
}
