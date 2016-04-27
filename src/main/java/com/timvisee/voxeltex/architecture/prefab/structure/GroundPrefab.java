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

package com.timvisee.voxeltex.architecture.prefab.structure;

import com.timvisee.voxeltex.architecture.component.rigidbody.RigidbodyComponent;
import com.timvisee.voxeltex.architecture.prefab.primitive.QuadPrefab;
import com.timvisee.voxeltex.module.material.Material;
import com.timvisee.voxeltex.module.resource.bundle.EngineResourceBundle;
import org.joml.Vector2f;

public class GroundPrefab extends QuadPrefab {

    /**
     * Constructor.
     */
    public GroundPrefab() {
        this(50.0f);
    }

    /**
     * Constructor.
     *
     * @param size Ground size.
     */
    public GroundPrefab(float size) {
        this(new Vector2f(size));
    }

    /**
     * Constructor.
     *
     * @param size Ground size.
     */
    public GroundPrefab(Vector2f size) {
        // Construct the parent with the proper size
        super("GroundPrefab", size);

        // Create a ground surface material
        System.out.println("Generating " + this + " surface material...");
        Material groundMaterial = new Material(EngineResourceBundle.getInstance().TEXTURE_GROUND);
        groundMaterial.getTiling().set(size.x / 8.0f);

        // Set the quad material to the ground
        setMaterial(groundMaterial);

        // Add a kinematic rigidbody for collision
        addComponent(new RigidbodyComponent(true));
    }
}
