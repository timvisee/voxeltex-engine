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
import com.timvisee.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
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
     * Distance trigger.
     */
    private static final float PICKUP_TRIGGER_DISTANCE = 1.1f;

    /**
     * Reference to the player controller component. Used to calculate whether to pickup the key or not.
     */
    private PlayerControllerComponent playerController;

    /**
     * Force field.
     */
    private CubePrefab forceField;

    /**
     * Key for the given lock lockType.
     */
    private LockType lockType;

    /**
     * True if this lock has been unlocked.
     */
    private boolean unlocked = false;

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

        // Set the player controller component and lockType
        this.playerController = playerController;
        this.lockType = lockType;

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
        this.forceField = new CubePrefab("ForceField");
        forceField.getTransform().getPosition().set(0, 0.5f, 0);
        forceField.setMaterial(new Material(Texture.fromColor(Color.RED, 1, 1)));
        forceField.getMeshRenderer().setAlpha(0f);
        addChild(forceField);

        // Create a child game object that holds the padlock light
        GameObject padlockLightObject = new GameObject("PadlockLight");
        padlockLightObject.getTransform().getPosition().y = 0.75f;
        padlockLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, lockType.getColor().toVector3f(), 0.2f));
        addChild(padlockLightObject);
    }

    @Override
    public synchronized void update() {
        // Call the super
        super.update();

        // Make sure a player controller reference is given
        if(this.playerController != null && this.playerController.getOwner() != null) {
            // Calculate the distance (squared) to the player controller
            float distance = this.playerController.getTransform().getPosition().distanceSquared(getTransform().getPosition());

            // Determine whether to pickup the item, trigger the player controller if that's the case
            if(distance <= PICKUP_TRIGGER_DISTANCE * PICKUP_TRIGGER_DISTANCE && !this.unlocked) {
                // Trigger the playerController
                this.playerController.onTrigger(this);

                // Make sure the player controller has the correct key type
                if(this.lockType.equals(this.playerController.getPickupLockType())) {
                    // Decay the padlock
                    addComponent(new ObjectDecayAnimatorComponent(0.0f));

                    // Set the unlocked flag
                    this.unlocked = true;
                }
            }

            // Calculate the force field intensity
            float forceFieldIntensity = 0;
            if(this.playerController.getPickupLockType() != this.lockType && !this.unlocked)
                forceFieldIntensity = Math.max(Math.min((2.5f - distance) / 5f, 0.45f), 0);

            // Update the force field intensity
            this.forceField.getMeshRenderer().setAlpha(forceFieldIntensity);
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
