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

package com.timvisee.voxeltex.util.math.quaternion;

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
