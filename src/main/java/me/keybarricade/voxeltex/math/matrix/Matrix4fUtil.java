package me.keybarricade.voxeltex.math.matrix;

import org.joml.Matrix4f;

public class Matrix4fUtil {

    /**
     * Convert a VecMath matrix to a JOML matrix.
     *
     * @param source Source matrix.
     * @param dest Destination matrix.
     *
     * @return Converted matrix.
     */
    public static Matrix4f toJoml(javax.vecmath.Matrix4f source, Matrix4f dest) {
        // Set the matrix
        dest.m00 = source.m00;
        dest.m10 = source.m01;
        dest.m20 = source.m02;
        dest.m30 = source.m03;
        dest.m01 = source.m10;
        dest.m11 = source.m11;
        dest.m21 = source.m12;
        dest.m31 = source.m13;
        dest.m02 = source.m20;
        dest.m12 = source.m21;
        dest.m22 = source.m22;
        dest.m32 = source.m23;
        dest.m03 = source.m30;
        dest.m13 = source.m31;
        dest.m23 = source.m32;
        dest.m33 = source.m33;

        // Return the new matrix
        return dest;
    }

    /**
     * Convert a JOML matrix to a VecMath matrix.
     *
     * @param source Source matrix.
     * @param dest Destination matrix.
     *
     * @return Converted matrix.
     */
    public static javax.vecmath.Matrix4f toVecmath(Matrix4f source, javax.vecmath.Matrix4f dest) {
        // Set the matrix
        dest.m00 = source.m00;
        dest.m10 = source.m01;
        dest.m20 = source.m02;
        dest.m30 = source.m03;
        dest.m01 = source.m10;
        dest.m11 = source.m11;
        dest.m21 = source.m12;
        dest.m31 = source.m13;
        dest.m02 = source.m20;
        dest.m12 = source.m21;
        dest.m22 = source.m22;
        dest.m32 = source.m23;
        dest.m03 = source.m30;
        dest.m13 = source.m31;
        dest.m23 = source.m32;
        dest.m33 = source.m33;

        // Return the new matrix
        return dest;
    }
}
