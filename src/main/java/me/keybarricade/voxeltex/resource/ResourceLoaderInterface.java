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

package me.keybarricade.voxeltex.resource;

import java.io.InputStream;
import java.nio.ByteBuffer;

public interface ResourceLoaderInterface {

    /**
     * Get the base path the resource loader loads from.
     *
     * @return Base path.
     */
    String getBasePath();

    /**
     * Load a resource as stream.
     *
     * @param path Path of the resource to load.
     *
     * @return Resource stream.
     */
    InputStream loadResourceStream(String path);

    /**
     * Load a resource as string.
     *
     * @param path Path of the resource to load.
     *
     * @return Resource string.
     */
    String loadResourceString(String path);

    /**
     * Load a resource in a byte buffer.
     *
     * @param path Path of the resource to load.
     *
     * @return Byte buffer.
     */
    ByteBuffer loadResourceByteBuffer(String path);
}
