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

package com.timvisee.keybarricade.demo;

public class Demo {

    /**
     * Application name.
     */
    public static String APP_NAME = "Key Barricade";

    /**
     * Application version name.
     */
    public static String APP_VERSION_NAME = "0.1";

    /**
     * Application version code.
     * This integer should be increased by one each version.
     */
    public static int APP_VERSION_CODE = 1;

    /**
     * YamlWrapperExample method, called on start.
     *
     * @param args Start up arguments.
     */
    public static void main(String[] args) {
        // Load the app
        DemoApp app = new DemoApp();

        // Initialize the app
        app.init();
    }
}
