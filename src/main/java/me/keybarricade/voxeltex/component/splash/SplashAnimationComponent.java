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

package me.keybarricade.voxeltex.component.splash;

import me.keybarricade.game.scene.MainMenuScene;
import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.overlay.gui.GuiImageComponent;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.global.Time;

import static org.lwjgl.opengl.GL11.glClearColor;

public class SplashAnimationComponent extends BaseComponent {

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Set the clear color of this scene
        // TODO: Configure this in the renderer class!
        glClearColor(0, 0, 0, 1.0f);

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
        // TODO: Buffer the components to maximize performance
        getComponent(RectangleTransform.class).setSizeX((float) size);
        getComponent(RectangleTransform.class).setSizeY((float) size);
        getComponent(GuiImageComponent.class).setAlpha((float) alpha);

        // Load the test environment scene after  the splash screen is done
        if(Time.time > 3.5)
            getEngine().getSceneManager().loadScene(new MainMenuScene());
    }

    @Override
    public void destroy() { }
}
