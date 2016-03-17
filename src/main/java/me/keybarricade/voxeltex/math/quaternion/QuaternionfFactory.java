package me.keybarricade.voxeltex.math.quaternion;

import org.joml.Quaternionf;

public class QuaternionfFactory {

    /**
     * Set the given quaternion to it's identity.
     *
     * @param dest Quaternion to set.
     *
     * @return The quaternion.
     */
    public static Quaternionf identity(Quaternionf dest) {
        return dest.identity();
    }

    /**
     * Create a new identity vector.
     *
     * @return Quaternion.
     */
    public static Quaternionf identity() {
        return new Quaternionf();
    }
}
