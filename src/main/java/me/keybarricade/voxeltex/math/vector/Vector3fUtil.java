package me.keybarricade.voxeltex.math.vector;

import org.joml.Vector3f;

public class Vector3fUtil {

    /**
     * Convert a VecMath vector to a JOML vector.
     *
     * @param source Source vector.
     * @param dest Destination vector.
     *
     * @return Converted vector.
     */
    public static Vector3f vecmathToJoml(javax.vecmath.Vector3f source, Vector3f dest) {
        // Set the coordinates
        dest.x = source.x;
        dest.y = source.y;
        dest.z = source.z;

        // Return the new vector
        return dest;
    }

    /**
     * Convert a JOML vector to a VecMath vector.
     *
     * @param source Source vector.
     * @param dest Destination vector.
     *
     * @return Converted vector.
     */
    public static javax.vecmath.Vector3f jomlToVecmath(Vector3f source, javax.vecmath.Vector3f dest) {
        // Set the coordinates
        dest.x = source.x;
        dest.y = source.y;
        dest.z = source.z;

        // Return the new vector
        return dest;
    }
}
