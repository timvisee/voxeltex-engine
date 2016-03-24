package me.keybarricade.voxeltex.math.quaternion;

import org.joml.Quaternionf;

public class QuaternionfUtil {

    /**
     * Convert a VecMath quaternion to a JOML quaternion.
     *
     * @param source Source quaternion.
     * @param dest Destination quaternion.
     *
     * @return Converted quaternion.
     */
    public static Quaternionf vecmathToJoml(javax.vecmath.Quat4f source, Quaternionf dest) {
        // Set the coordinates
        dest.x = source.x;
        dest.y = source.y;
        dest.z = source.z;

        // Return the new quaternion
        return dest;
    }

    /**
     * Convert a JOML quaternion to a VecMath quaternion.
     *
     * @param source Source quaternion.
     * @param dest Destination quaternion.
     *
     * @return Converted quaternion.
     */
    public static javax.vecmath.Quat4f jomlToVecmath(Quaternionf source, javax.vecmath.Quat4f dest) {
        // Set the coordinates
        dest.x = source.x;
        dest.y = source.y;
        dest.z = source.z;

        // Return the new quaternion
        return dest;
    }
}
