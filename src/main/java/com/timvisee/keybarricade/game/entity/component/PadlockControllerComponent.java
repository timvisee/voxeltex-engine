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

package com.timvisee.keybarricade.game.entity.component;

import com.timvisee.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import com.timvisee.keybarricade.game.entity.LockType;
import com.timvisee.voxeltex.component.BaseComponent;
import com.timvisee.voxeltex.prefab.primitive.CubePrefab;

public class PadlockControllerComponent extends BaseComponent {

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
     * @param playerController Player controller component reference.
     * @param lockType Lock type.
     */
    public PadlockControllerComponent(PlayerControllerComponent playerController, LockType lockType) {
        this.playerController = playerController;
        this.lockType = lockType;
    }

    /**
     * Constructor.
     *
     * @param playerController Player controller component reference.
     * @param forceField Force field game object.
     * @param lockType Lock type.
     */
    public PadlockControllerComponent(PlayerControllerComponent playerController, CubePrefab forceField, LockType lockType) {
        this.playerController = playerController;
        this.forceField = forceField;
        this.lockType = lockType;
    }

    @Override
    public void create() { }

    @Override
    public synchronized void update() {
        // Make sure a player controller reference is given
        if(this.playerController != null && this.playerController.getOwner() != null) {
            // Calculate the distance (squared) to the player controller
            float distance = this.playerController.getTransform().getPosition().distanceSquared(getTransform().getPosition());

            // Determine whether to pickup the item, trigger the player controller if that's the case
            if(distance <= PICKUP_TRIGGER_DISTANCE * PICKUP_TRIGGER_DISTANCE && !this.unlocked) {
                // Trigger the playerController
                this.playerController.onTrigger(getOwner());

                // Make sure the player controller has the correct key type
                if(this.lockType.equals(this.playerController.getPickupLockType())) {
                    // Decay the padlock
                    // TODO: Remove getOwner() reference getter
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
     * Get the force field game object.
     *
     * @return Force field game object.
     */
    public CubePrefab getForceField() {
        return this.forceField;
    }

    /**
     * Set the force field game object.
     *
     * @param forceField Force field game object.
     */
    public void setForceField(CubePrefab forceField) {
        this.forceField = forceField;
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
