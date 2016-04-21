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

package com.timvisee.keybarricade.game.component;

import com.timvisee.keybarricade.game.LockType;
import com.timvisee.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import com.timvisee.keybarricade.game.component.animator.ObjectSpawnAnimatorComponent;
import com.timvisee.keybarricade.game.prefab.entity.BoxPrefab;
import com.timvisee.keybarricade.game.prefab.entity.LampPrefab;
import com.timvisee.voxeltex.component.BaseComponent;
import com.timvisee.voxeltex.global.Time;
import org.joml.Vector3f;

public class MainMenuSpawnerComponent extends BaseComponent {

    /**
     * Constructor.
     */
    public MainMenuSpawnerComponent() { }

    @Override
    public void create() { }

    @Override
    public void start() {
        // Call the super
        super.start();
    }

    @Override
    public void update() {
        // Spawn cubes randomly
        if(Math.random() < Time.deltaTimeFloat * 10.0f) {
            // Determine the position
            float x = (int) (-25f + (float) Math.random() * 50);
            float z = (int) (-25f + (float) Math.random() * 50);

            // Determine what to spawn
            if(Math.random() < 0.95f)
                // Spawn a box
                getScene().addGameObject(new BoxPrefab(new Vector3f(x + 0.5f, 0.5f, z + 0.5f), true, 0.0f, 10.0f));

            else {
                // Create a lamp
                LampPrefab lamp = new LampPrefab(
                                "LampPrefab",
                                LockType.fromDataValue((int) ((Math.random() * LockType.values().length) - 1)).getColorCopy()
                );

                // Set the position
                lamp.getTransform().setPosition(x + 0.5f, 0.01f, z + 0.5f);

                // Add a spawn and decay animator
                lamp.addComponent(new ObjectSpawnAnimatorComponent(0f));
                lamp.addComponent(new ObjectDecayAnimatorComponent(10f));

                // Add the lamp
                getScene().addGameObject(lamp);
            }
        }
    }
}
