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

package com.timvisee.voxeltex.architecture.component.other.splash;

import com.timvisee.voxeltex.architecture.component.BaseComponent;
import com.timvisee.voxeltex.architecture.component.overlay.gui.GuiImageComponent;
import com.timvisee.voxeltex.architecture.scene.Scene;
import com.timvisee.voxeltex.module.transform.rectangle.Rectangle;
import com.timvisee.voxeltex.module.transform.rectangle.RectangleTransform;
import com.timvisee.voxeltex.runtime.global.Time;
import org.lwjgl.opengl.GL11;

public class SplashAnimatorComponent extends BaseComponent {

    /**
     * Rectangle transform component of the owner game object.
     */
    private RectangleTransform rectangleTransform;

    /**
     * GUI image component of the owner game object.
     */
    private GuiImageComponent guiImage;

    /**
     * Temporary rectangle variable, used to minimize object allocation at runtime to improve overall performance.
     */
    private final Rectangle tempRectangle = new Rectangle();

    /**
     * The next scene that should be loaded, after the splash screen.
     */
    private Scene nextScene;

    /**
     * Constructor.
     *
     * @param nextScene The next scene.
     */
    public SplashAnimatorComponent(Scene nextScene) {
        this.nextScene = nextScene;
    }

    @Override
    public void create() {
        // Update the attachments
        updateAttachments();
    }

    @Override
    public void start() {
        // Call the super
        super.start();
    }

    @Override
    public void update() {
        // Set the clear color of this scene
        // TODO: Configure this in the renderer class!
        GL11.glClearColor(0, 0, 0, 1.0f);

        // Make sure the proper components are attached
        if(this.rectangleTransform == null || this.guiImage == null) {
            // Update the attachments
            updateAttachments();

            // If the components still aren't attached, show an error and quit this method
            if(this.rectangleTransform == null || this.guiImage == null) {
                System.out.println("No RectangleTransform or GUI image component in " + this + " of " + getOwner() + ", unable to animate");
                return;
            }
        }

        // Calculate the local time value
        double x = Time.time - 0.5;

        // Define the size and alpha variable used for calculations
        double size, alpha = 0;

        // Calculate the size of the splash
        size = (Math.pow(-x * 0.48 + 0.8, 3) + 1.2 - x * 0.1) * 256.0;

        // Calculate the alpha intensity of the splash
        if(Time.time < 1.25)
            alpha = Math.pow((x - 0.75) * 1.35, 2.0) * -1.0 + 1.0;
        else if(Time.time < 2.75)
            alpha = 1f;
        else if(Time.time < 3.5)
            alpha = Math.pow((x - 2.25) * 1.35, 2.0) * -1.0 + 1.0;

        // Send the values to the target components
        this.rectangleTransform.setSizeX((float) size);
        this.rectangleTransform.setSizeY((float) size);
        this.guiImage.setAlpha((float) alpha);

        // Load the test environment scene after  the splash screen is done
        if(Time.time > 3.5)
            getEngine().getSceneManager().loadScene(getNextScene());
    }

    /**
     * Update all attached components to this component.
     */
    private void updateAttachments() {
        // Detach all attachments
        this.rectangleTransform = null;
        this.guiImage = null;

        // Synchronize to ensure we aren't using this temporary variable in multiple spots at the same time
        synchronized(this.tempRectangle) {
            // Get and set the transform component
            this.rectangleTransform = getComponent(RectangleTransform.class);
        }

        // Get and set the GUI image component
        this.guiImage = getComponent(GuiImageComponent.class);
    }

    /**
     * Get the next scene.
     *
     * @return Next scene.
     */
    public Scene getNextScene() {
        return this.nextScene;
    }

    /**
     * Set the next scene.
     *
     * @param nextScene Next scene.
     */
    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }
}
