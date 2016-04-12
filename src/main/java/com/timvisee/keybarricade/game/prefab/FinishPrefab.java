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

import com.timvisee.keybarricade.game.asset.GameResourceBundle;
import com.timvisee.voxeltex.prefab.primitive.QuadPrefab;

public class FinishPrefab extends QuadPrefab {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "FinishPrefab";

    /**
     * Distance trigger.
     */
    private static final float PICKUP_TRIGGER_DISTANCE = 0.5f;

    /**
     * Reference to player prefab. Used to calculate whether to pickup the key or not.
     */
    private PlayerPrefab player;

    /**
     * Flag to ensure the finish is only triggered once.
     */
    private boolean triggered = false;

    /**
     * Constructor.
     */
    public FinishPrefab() {
        this(GAME_OBJECT_NAME, null);
    }

    /**
     * Constructor.
     *
     * @param player Player reference.
     */
    public FinishPrefab(PlayerPrefab player) {
        this(GAME_OBJECT_NAME, player);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param player Player reference.
     */
    public FinishPrefab(String name, PlayerPrefab player) {
        // Construct the parent with the proper size
        super(name);

        // Set the player instance
        this.player = player;

        // Set the finish material
        setMaterial(GameResourceBundle.getInstance().MATERIAL_FINISH);
    }

    @Override
    public synchronized void update() {
        // Call the super
        super.update();

        // Make sure the finish hasn't been triggered before
        if(this.triggered)
            return;

        // Make sure a player reference is given
        if(this.player != null) {
            // Calculate the distance (squared) to the player
            float distance = this.player.getTransform().getPosition().distanceSquared(getTransform().getPosition());

            // Determine whether to pickup the item, trigger the player if that's the case
            if(distance <= PICKUP_TRIGGER_DISTANCE * PICKUP_TRIGGER_DISTANCE) {
                // Set the triggered flag
                this.triggered = true;

                // Trigger the player
                this.player.onTrigger(this);
            }
        }
    }
}
