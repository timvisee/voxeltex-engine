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

package com.timvisee.voxeltex.example.example2;

import com.timvisee.voxeltex.architecture.component.light.LightSourceComponent;
import com.timvisee.voxeltex.architecture.prefab.camera.FpsCameraPrefab;
import com.timvisee.voxeltex.architecture.prefab.light.LightPrefab;
import com.timvisee.voxeltex.architecture.prefab.primitive.CubePrefab;
import com.timvisee.voxeltex.architecture.prefab.structure.GroundPrefab;
import com.timvisee.voxeltex.architecture.scene.Scene;
import com.timvisee.voxeltex.engine.light.Light;
import com.timvisee.voxeltex.module.Color;
import com.timvisee.voxeltex.module.resource.bundle.EngineResourceBundle;

public class Example2Scene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Add a light simulating the sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFFF4D6).toVector3f(), 0.4f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Add a ground
        addGameObject(new GroundPrefab());

        // Create a camera, which is required to see anything in a scene
        FpsCameraPrefab camera = new FpsCameraPrefab();
        camera.getTransform().setPosition(0, 2, 5);
        addGameObject(camera);

        // Create a row of light emitting boxes
        for(int i = 0; i < 10; i++) {
            // Create a box at the proper position
            CubePrefab box = new CubePrefab();
            box.getTransform().setPosition(0.5f + 2.0f * i, 0.5f, 0.5f);
            box.setMaterial(EngineResourceBundle.getInstance().MATERIAL_BOX);
            addGameObject(box);

            // Add a light source component to the box with a random color
            box.addComponent(new LightSourceComponent(Color.random()));
        }
    }
}
