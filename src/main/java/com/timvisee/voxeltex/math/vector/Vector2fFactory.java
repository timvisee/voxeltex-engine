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
