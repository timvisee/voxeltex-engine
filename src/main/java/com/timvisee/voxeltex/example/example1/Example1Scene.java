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

package com.timvisee.voxeltex.example.example1;

import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.prefab.camera.FpsCameraPrefab;
import com.timvisee.voxeltex.prefab.light.LightPrefab;
import com.timvisee.voxeltex.prefab.primitive.CubePrefab;
import com.timvisee.voxeltex.resource.bundle.EngineResourceBundle;
import com.timvisee.voxeltex.scene.Scene;
import com.timvisee.voxeltex.util.Color;

/**
 * Example scene one.
 *
 * Please view the description and tutorial in the provided README.md file for further information.
 */
public class Example1Scene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Eliminate darkness by adding a light simulating the sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDDC5C).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create a movable camera, so we can view our scene
        FpsCameraPrefab camera = new FpsCameraPrefab();
        camera.getTransform().setPosition(0, 0, 4);
        addGameObject(camera);

        // Place a textured box in the center of our scene
        CubePrefab box = new CubePrefab();
        box.setMaterial(EngineResourceBundle.getInstance().MATERIAL_BOX);
        addGameObject(box);
    }
}
