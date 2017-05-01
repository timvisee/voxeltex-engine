/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.voxeltex.util.math.matrix;

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
        // Copy and return the matrix
        return dest.m00(source.m00)
                .m10(source.m01)
                .m20(source.m02)
                .m30(source.m03)
                .m01(source.m10)
                .m11(source.m11)
                .m21(source.m12)
                .m31(source.m13)
                .m02(source.m20)
                .m12(source.m21)
                .m22(source.m22)
                .m32(source.m23)
                .m03(source.m30)
                .m13(source.m31)
                .m23(source.m32)
                .m33(source.m33);
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
        dest.m00 = source.m00();
        dest.m10 = source.m01();
        dest.m20 = source.m02();
        dest.m30 = source.m03();
        dest.m01 = source.m10();
        dest.m11 = source.m11();
        dest.m21 = source.m12();
        dest.m31 = source.m13();
        dest.m02 = source.m20();
        dest.m12 = source.m21();
        dest.m22 = source.m22();
        dest.m32 = source.m23();
        dest.m03 = source.m30();
        dest.m13 = source.m31();
        dest.m23 = source.m32();
        dest.m33 = source.m33();

        // Return the new matrix
        return dest;
    }
}
