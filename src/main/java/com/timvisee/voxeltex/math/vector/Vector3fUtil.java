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

package com.timvisee.voxeltex.math.vector;

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
    public static Vector3f toJoml(javax.vecmath.Vector3f source, Vector3f dest) {
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
    public static javax.vecmath.Vector3f toVecmath(Vector3f source, javax.vecmath.Vector3f dest) {
        // Set the coordinates
        dest.x = source.x;
        dest.y = source.y;
        dest.z = source.z;

        // Return the new vector
        return dest;
    }
}
