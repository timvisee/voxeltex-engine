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

package com.timvisee.voxeltex.architecture.component.overlay.gui;

import com.timvisee.voxeltex.architecture.component.overlay.AbstractOverlayComponent;
import com.timvisee.voxeltex.architecture.component.overlay.transform.Rectangle;
import com.timvisee.voxeltex.architecture.component.overlay.transform.RectangleTransform;

public abstract class AbstractGuiComponent extends AbstractOverlayComponent {

    /**
     * Rectangle transform component of the owner game object.
     */
    private RectangleTransform rectangleTransform;

    /**
     * Temporary rectangle variable, used to minimize object allocation at runtime to improve overall performance.
     */
    private final Rectangle tempRectangle = new Rectangle();

    @Override
    public void create() {
        // Update the rectangle transform
        updateRectangleTransform();

        // Create the super
        super.create();
    }

    @Override
    public synchronized void update() {
        // Update the rectangle transform if none is attached
        if(!hasRectangleTransform())
            updateRectangleTransform();

        // Update the super
        super.update();
    }

    /**
     * Get the attached rectangle transform component.
     *
     * @return Attached rectangle transfomr component, or null.
     */
    public RectangleTransform getRectangleTransform() {
        return this.rectangleTransform;
    }

    /**
     * Check whether this component has a rectangle transform component attached.
     *
     * @return True if a rectangle transform component is attached, false if not.
     */
    public boolean hasRectangleTransform() {
        return this.rectangleTransform != null;
    }

    /**
     * Update the attached rectangle transform.
     * This will search for an rectangle transform component on the owner game object, and attach it to the component.
     */
    private void updateRectangleTransform() {
        // Detach the currently attached transform
        this.rectangleTransform = null;

        // Synchronize to ensure we aren't using this temporary variable in multiple spots at the same time
        synchronized(this.tempRectangle) {
            // Get and set the transform component
            this.rectangleTransform = getComponent(RectangleTransform.class);
        }
    }
}
