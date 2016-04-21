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

package com.timvisee.keybarricade.game.prefab;

import com.timvisee.keybarricade.game.LockType;
import com.timvisee.keybarricade.game.asset.GameResourceBundle;
import com.timvisee.keybarricade.game.component.padlock.PadlockControllerComponent;
import com.timvisee.keybarricade.game.component.player.PlayerControllerComponent;
import com.timvisee.voxeltex.component.collider.primitive.BoxColliderComponent;
import com.timvisee.voxeltex.component.light.LightSourceComponent;
import com.timvisee.voxeltex.component.mesh.filter.MeshFilterComponent;
import com.timvisee.voxeltex.component.mesh.renderer.MeshRendererComponent;
import com.timvisee.voxeltex.gameobject.GameObject;
import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.prefab.primitive.CubePrefab;
import com.timvisee.voxeltex.texture.Texture;
import com.timvisee.voxeltex.util.Color;
import org.joml.Vector3f;

public class PadlockPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "PadlockPickupPrefab";

    /**
     * Padlock controller component instance.
     */
    private final PadlockControllerComponent controller;

    /**
     * Constructor.
     *
     * @param lockType Lock lockType.
     */
    public PadlockPrefab(LockType lockType) {
        this(GAME_OBJECT_NAME, null, lockType);
    }

    /**
     * Constructor.
     *
     * @param playerController Player controller component reference.
     *
     * @param lockType Lock lockType.
     */
    public PadlockPrefab(PlayerControllerComponent playerController, LockType lockType) {
        this(GAME_OBJECT_NAME, playerController, lockType);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param playerController Player controller component reference.
     *
     * @param lockType Lock lockType.
     */
    public PadlockPrefab(String name, PlayerControllerComponent playerController, LockType lockType) {
        // Construct the parent with the proper size
        super(name);

        // Create and add the padlock controller component
        addComponent(this.controller = new PadlockControllerComponent(playerController, lockType));

        // Generate the padlock material
        Material lockMaterial = new Material(Texture.fromColor(lockType.getColor(), 1, 1));

        // Add a collider
        addComponent(new BoxColliderComponent(new Vector3f(1f, 1f, 4f)));

        // Create a child game object that holds the padlock model
        GameObject padlockModelObject = new GameObject("PadlockRenderer");
        padlockModelObject.addComponent(new MeshFilterComponent(GameResourceBundle.getInstance().MESH_PADLOCK));
        padlockModelObject.addComponent(new MeshRendererComponent(lockMaterial));
        padlockModelObject.getTransform().getPosition().y = 0.05f;
        padlockModelObject.getTransform().getAngularVelocity().y = 0.6f;
        addChild(padlockModelObject);

        // Create a child game object that holds the force field
        CubePrefab forceField = new CubePrefab("ForceField");
        forceField.getTransform().getPosition().set(0, 0.5f, 0);
        forceField.setMaterial(new Material(Texture.fromColor(Color.RED, 1, 1)));
        forceField.getMeshRenderer().setAlpha(0f);
        addChild(forceField);

        // Set the padlock controller component force field instance
        this.controller.setForceField(forceField);

        // Create a child game object that holds the padlock light
        GameObject padlockLightObject = new GameObject("PadlockLight");
        padlockLightObject.getTransform().getPosition().y = 0.75f;
        padlockLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, lockType.getColor().toVector3f(), 0.2f));
        addChild(padlockLightObject);
    }
}
