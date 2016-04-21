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

import com.timvisee.keybarricade.game.entity.prefab.GroundPrefab;
import com.timvisee.voxeltex.component.light.LightSourceComponent;
import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.prefab.camera.FpsCameraPrefab;
import com.timvisee.voxeltex.prefab.light.LightPrefab;
import com.timvisee.voxeltex.prefab.primitive.CubePrefab;
import com.timvisee.voxeltex.resource.bundle.EngineResourceBundle;
import com.timvisee.voxeltex.scene.Scene;
import com.timvisee.voxeltex.util.Color;

public class DemoScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Add a light simulating the sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDDC5C).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create a surface
        addGameObject(new GroundPrefab());


        // Demo code here:


        // Create a camera, which is required to see anything in a scene
        FpsCameraPrefab camera = new FpsCameraPrefab();
        camera.getTransform().setPosition(0, 2, 3);
        addGameObject(camera);

        // Create a row of boxes which emit light
        for (int i = 0; i < 15; i++) {
            CubePrefab box = new CubePrefab();
            box.getTransform().setPosition(i, 0.5f, 0);
            box.setMaterial(EngineResourceBundle.getInstance().MATERIAL_BOX);
            box.addComponent(new LightSourceComponent(Color.random()));
            addGameObject(box);
        }
    }
}
