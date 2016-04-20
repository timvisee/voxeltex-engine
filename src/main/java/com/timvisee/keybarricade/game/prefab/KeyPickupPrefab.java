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
import com.timvisee.keybarricade.game.component.player.PlayerControllerComponent;
import com.timvisee.voxeltex.component.light.LightSourceComponent;
import com.timvisee.voxeltex.component.mesh.filter.MeshFilterComponent;
import com.timvisee.voxeltex.component.mesh.renderer.MeshRendererComponent;
import com.timvisee.voxeltex.gameobject.GameObject;
import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.texture.Texture;

public class KeyPickupPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "KeyPickupPrefab";

    /**
     * Distance trigger.
     */
    private static final float PICKUP_TRIGGER_DISTANCE = 0.5f;

    /**
     * Reference to the player controller component. Used to calculate whether to pickup the key or not.
     */
    private PlayerControllerComponent playerController;

    /**
     * Key for the given lock lockType.
     */
    private LockType lockType;

    /**
     * Constructor.
     *
     * @param lockType Lock lockType.
     */
    public KeyPickupPrefab(LockType lockType) {
        this(GAME_OBJECT_NAME, null, lockType);
    }

    /**
     * Constructor.
     *
     * @param playerController Player controller component reference.
     * @param lockType Lock lockType.
     */
    public KeyPickupPrefab(PlayerControllerComponent playerController, LockType lockType) {
        this(GAME_OBJECT_NAME, playerController, lockType);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param playerController Player controller component reference.
     * @param lockType Lock lockType.
     */
    public KeyPickupPrefab(String name, PlayerControllerComponent playerController, LockType lockType) {
        // Construct the parent with the proper size
        super(name);

        // Set the player controller component and lockType
        this.playerController = playerController;
        this.lockType = lockType;

        // Rotate the base object around
        getTransform().getAngularVelocity().y = 0.6f;

        // Generate the key material
        Material keyMaterial = new Material(Texture.fromColor(lockType.getColor(), 1, 1));

        // Create a child game object that holds the key model
        GameObject keyModelObject = new GameObject("KeyPickupModel");
        keyModelObject.addComponent(new MeshFilterComponent(GameResourceBundle.getInstance().MESH_KEY));
        keyModelObject.addComponent(new MeshRendererComponent(keyMaterial));
        keyModelObject.getTransform().getPosition().y = 0.2f;
        keyModelObject.getTransform().getAngularVelocity().x = 2f;
        addChild(keyModelObject);

        // Create a child game object that holds the key light
        GameObject keyLightObject = new GameObject("KeyPickupModel");
        keyLightObject.getTransform().getPosition().y = 0.60f;
        keyLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, lockType.getColor().toVector3f(), 0.2f));
        addChild(keyLightObject);
    }

    @Override
    public synchronized void update() {
        // Call the super
        super.update();

        // Make sure a player controller reference is given
        if(this.playerController != null) {
            // Calculate the distance (squared) to the player controller
            float distance = this.playerController.getTransform().getPosition().distanceSquared(getTransform().getPosition());

            // Determine whether to pickup the item, trigger the player controller if that's the case
            if(distance <= PICKUP_TRIGGER_DISTANCE * PICKUP_TRIGGER_DISTANCE)
                this.playerController.onTrigger(this);
        }
    }

    /**
     * Get the attached player controller component.
     *
     * @return Attached player controller component.
     */
    public PlayerControllerComponent getPlayerController() {
        return this.playerController;
    }

    /**
     * Set the attached player controller component.
     *
     * @param playerController Player controller component.
     */
    public void setPlayerController(PlayerControllerComponent playerController) {
        this.playerController = playerController;
    }

    /**
     * Get the lock lockType.
     *
     * @return Lock lockType.
     */
    public LockType getLockType() {
        return this.lockType;
    }

    /**
     * Set the lock lockType.
     *
     * @param lockType Lock lockType.
     */
    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }
}
