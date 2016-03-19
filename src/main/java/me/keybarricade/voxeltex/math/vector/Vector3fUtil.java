package me.keybarricade.voxeltex.math.vector;

import org.joml.Vector3f;

public class Vector3fUtil {

    /**
     * Get the value at the X, Y or Z position of the vector based on the given position.
     *
     * @param vector Vector.
     * @param position Position.
     *
     * @return Value.
     */
    public static float at(Vector3f vector, int position) {
        // Return the correct value
        switch(position) {
            case 0:
                return vector.x;

            case 1:
                return vector.y;

            case 2:
                return vector.z;

            default:
                throw new RuntimeException("Invalid vector position");
        }
    }
}
