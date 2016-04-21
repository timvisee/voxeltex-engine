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

package com.timvisee.keybarricade.game.component.keyPickup;

import com.timvisee.keybarricade.game.LockType;
import com.timvisee.keybarricade.game.component.player.PlayerControllerComponent;
import com.timvisee.voxeltex.component.BaseComponent;

public class KeyPickupControllerComponent extends BaseComponent {

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
     * @param playerController Player controller component reference.
     * @param lockType Lock type.
     */
    public KeyPickupControllerComponent(PlayerControllerComponent playerController, LockType lockType) {
        this.playerController = playerController;
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
            if(distance <= PICKUP_TRIGGER_DISTANCE * PICKUP_TRIGGER_DISTANCE)
                this.playerController.onTrigger(getOwner());
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
