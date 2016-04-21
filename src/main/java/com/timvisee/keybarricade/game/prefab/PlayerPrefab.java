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

import com.timvisee.keybarricade.game.component.player.PlayerControllerComponent;
import com.timvisee.keybarricade.game.scene.GameScene;
import com.timvisee.voxeltex.gameobject.GameObject;

public class PlayerPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "PlayerPrefab";

    /**
     * Player controller component instance.
     */
    private final PlayerControllerComponent playerController;

    /**
     * Constructor.
     *
     * @param gameScene Game scene instance.
     */
    public PlayerPrefab(GameScene gameScene) {
        this(GAME_OBJECT_NAME, gameScene);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param gameScene Game scene instance.
     */
    public PlayerPrefab(String name, GameScene gameScene) {
        // Construct the parent with the proper size
        super(name);

        // Create and add the player controller
        addComponent(this.playerController = new PlayerControllerComponent(gameScene));
    }

    /**
     * Get the player controller instance.
     *
     * @return Player controller instance.
     */
    public PlayerControllerComponent getPlayerController() {
        return this.playerController;
    }
}
