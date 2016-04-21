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

package com.timvisee.keybarricade.game.component.entity;

import com.timvisee.voxeltex.component.BaseComponent;

public class FinishControllerComponent extends BaseComponent {

    /**
     * Distance trigger.
     */
    private static final float PICKUP_TRIGGER_DISTANCE = 0.5f;

    /**
     * Reference to player controller component. Used to calculate whether to pickup the key or not.
     */
    private PlayerControllerComponent controller;

    /**
     * Flag to ensure the finish is only triggered once.
     */
    private boolean triggered = false;

    /**
     * Constructor.
     *
     * @param playerController Player controller component reference.
     */
    public FinishControllerComponent(PlayerControllerComponent playerController) {
        this.controller = playerController;
    }

    @Override
    public void create() { }

    @Override
    public void update() {
        // Make sure the finish hasn't been triggered before
        if(this.triggered)
            return;

        // Make sure a player controller reference is given
        if(this.controller != null && this.controller.getOwner() != null) {
            // Calculate the distance (squared) to the player controller
            float distance = this.controller.getTransform().getPosition().distanceSquared(getTransform().getPosition());

            // Determine whether to pickup the item, trigger the player controller if that's the case
            if(distance <= PICKUP_TRIGGER_DISTANCE * PICKUP_TRIGGER_DISTANCE) {
                // Set the triggered flag
                this.triggered = true;

                // Trigger the player controller
                this.controller.onTrigger(getOwner());
            }
        }
    }
}
