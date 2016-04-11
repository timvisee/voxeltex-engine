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

package me.keybarricade.voxeltex.component.overlay.gui;

import me.keybarricade.voxeltex.component.transform.Rectangle;
import me.keybarricade.voxeltex.render.RenderOverlayHelper;

public class GuiPanelComponent extends AbstractGuiComponent {

    /**
     * Temporary rectangle variable, used to minimize object allocation at runtime to improve overall performance.
     */
    private final Rectangle tempRectangle = new Rectangle();

    /**
     * Constructor.
     */
    public GuiPanelComponent() { }

    @Override
    public void drawOverlay() {
        // Set the drawing color
        // TODO: Make this color configurable!
        RenderOverlayHelper.color(0, 0, 0, .75f);

        // Synchronize to ensure we aren't using this temporary variable in multiple spots at the same time
        //noinspection Duplicates
        synchronized(this.tempRectangle) {
            // Make sure we've a valid transform component, if not, skip the following code with an error message
            if(!hasRectangleTransform()) {
                System.out.println("No RectangleTransform component in " + getName() + " of " + getOwner().getName() + ", unable to render");
                return;
            }

            // Get the overlay rectangle
            getRectangleTransform().getOverlayRectangle(this.tempRectangle);

            // Render the rectangle
            RenderOverlayHelper.renderRectangle(
                    this.tempRectangle.getX(), this.tempRectangle.getY(),
                    this.tempRectangle.getWidth(), this.tempRectangle.getHeight()
            );
        }
    }
}
