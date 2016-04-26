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

package com.timvisee.voxeltex.module.render;

import com.timvisee.voxeltex.VoxelTexEngine;
import com.timvisee.voxeltex.engine.window.VoxelTexWindow;

public class OverlayUtil {

    /**
     * Window instance currently used for calculations.
     */
    private static VoxelTexWindow window;

    /**
     * Get the window instance that is currently used.
     *
     * @return Window instance.
     */
    public static VoxelTexWindow getWindow() {
        return window;
    }

    /**
     * Set the window instance that is used for calculations.
     *
     * @param window Window instance.
     */
    public static void setWindow(VoxelTexWindow window) {
        OverlayUtil.window = window;
    }

    /**
     * Set the window instance that is used for calculations based on a VoxelTex engine instance.
     *
     * @param engine Engine instance.
     */
    public static void setWindow(VoxelTexEngine engine) {
        setWindow(engine.getRenderer().getWindow());
    }

    /**
     * Calculate and return the window ratio factor.
     *
     * @return Window ratio factor.
     */
    public static float getWindowRatioFactor() {
        return (float) window.getWidth() / (float) window.getHeight();
    }

    /**
     * Calculate the horizontal overlay position based on the given position in pixels.
     *
     * @param px Pixels.
     *
     * @return Overlay position.
     */
    public static float pixelToOverlayHorizontal(float px) {
        return px / (float) window.getWidth();
    }

    /**
     * Calculate the vertical overlay position based on the given position in pixels.
     *
     * @param px Pixels.
     *
     * @return Overlay position.
     */
    public static float pixelToOverlayVertical(float px) {
        return px / (float) window.getHeight();
    }
}
