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

package com.timvisee.voxeltex.resource.engine;

public class EngineAssetLoader extends EngineResourceLoader {

    /**
     * Relative base path.
     */
    private static final String RELATIVE_BASE_PATH = "assets/";

    /**
     * Resource loader instance.
     */
    private static EngineAssetLoader instance;

    /**
     * Get a resource loader instance.
     *
     * @return Resource loader instance.
     */
    public static EngineAssetLoader getInstance() {
        // Return the instance if it does exist
        if(instance != null)
            return instance;

        // Create an instance, then store and return it
        instance = new EngineAssetLoader();
        return instance;
    }

    @Override
    public String getBasePath() {
        return super.getBasePath() + RELATIVE_BASE_PATH;
    }
}

