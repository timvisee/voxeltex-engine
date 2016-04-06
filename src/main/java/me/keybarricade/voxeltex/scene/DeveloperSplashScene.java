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

package me.keybarricade.voxeltex.scene;

import me.keybarricade.voxeltex.component.overlay.gui.GuiImageComponent;
import me.keybarricade.voxeltex.component.splash.SplashAnimationComponent;
import me.keybarricade.voxeltex.component.transform.HorizontalTransformAnchorType;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.texture.Image;
import org.joml.Vector2f;

public class DeveloperSplashScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Load the developer splash image
        Image developerSplashImage = Image.loadFromEngineAssets("images/developerSplash.png");

        // Create an game object with the splash as GUI image
        GameObject avatarImage = new GameObject("Avatar");
        avatarImage.addComponent(new RectangleTransform(
                new Vector2f(0, 0),
                new Vector2f(256, 256),
                HorizontalTransformAnchorType.CENTER, VerticalTransformAnchorType.MIDDLE
        ));
        avatarImage.addComponent(new GuiImageComponent(developerSplashImage));
        avatarImage.addComponent(new SplashAnimationComponent());
        addGameObject(avatarImage);
    }
}
