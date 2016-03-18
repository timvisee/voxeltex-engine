package me.keybarricade.voxeltex.math.vector;

import org.joml.Vector2f;

public class Vector2fFactory {

    /**
     * Set the given vector to it's identity.
     *
     * @param dest Vector to set.
     *
     * @return The vector.
     */
    public static Vector2f identity(Vector2f dest) {
        return dest.zero();
    }

    /**
     * Create a new identity vector.
     *
     * @return Vector.
     */
    public static Vector2f identity() {
        return zero();
    }

    /**
     * Create a zero vector instance.
     *
     * @return Zero vector instance.
     */
    public static Vector2f zero() {
        return new Vector2f(0.0f);
    }

    /**
     * Create a one vector instance.
     *
     * @return One vector instance.
     */
    public static Vector2f one() {
        return new Vector2f(1.0f);
    }
}
