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

package com.timvisee.keybarricade.game.level;

import com.timvisee.keybarricade.game.LockType;
import com.timvisee.keybarricade.game.component.animator.ObjectSpawnAnimatorComponent;
import com.timvisee.keybarricade.game.component.entity.PlayerControllerComponent;
import com.timvisee.keybarricade.game.prefab.entity.*;
import com.timvisee.keybarricade.game.scene.GameScene;
import com.timvisee.voxeltex.component.rigidbody.RigidbodyComponent;
import com.timvisee.voxeltex.gameobject.GameObject;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {

    /**
     * Level to build.
     */
    private final Level level;

    /**
     * Game scene instance.
     */
    private final GameScene gameScene;

    /**
     * Level root object.
     */
    private GameObject levelRoot;

    /**
     * Player controller component.
     */
    private PlayerControllerComponent playerController;

    /**
     * Hint of the level.
     */
    private String levelHint;

    /**
     * Spawn delay.
     */
    private float delay = 0.0f;

    /**
     * Constructor.
     *
     * @param level Level to build.
     * @param gameScene Game scene instance.
     * @param levelRoot Level root object.
     */
    public LevelBuilder(Level level, GameScene gameScene, GameObject levelRoot) {
        this.level = level;
        this.gameScene = gameScene;
        this.levelRoot = levelRoot;
    }

    /**
     * Get the level to build.
     *
     * @return Level to build.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Get the level root object.
     *
     * @return Level root object.
     */
    public GameObject getLevelRoot() {
        return this.levelRoot;
    }

    /**
     * Set the level root object.
     *
     * @param levelRoot Level root object.
     */
    public void setLevelRoot(GameObject levelRoot) {
        this.levelRoot = levelRoot;
    }

    /**
     * Build the level.
     *
     * @param delay Delay in seconds.
     */
    @SuppressWarnings("Duplicates")
    public void build(float delay) {
        // Make sure the level is valid
        if(this.level == null)
            throw new RuntimeException("Unable to build level, level instance invalid");

        // Show a status message
        System.out.println("Building level environment at runtime...");

        // Set the delay
        this.delay = delay;

        // Get the level hint
        this.levelHint = this.level.getConfig().getString("hint", null);

        // Get the tiles section
        ConfigurationSection objectsConfig = this.level.getConfig().getConfigurationSection("objects");

        // Get all object keys
        List<String> objectKeys = objectsConfig.getKeys("");

        // Keep track of the minimum and maximum block positions
        boolean mapCoordinatesInit = false;
        int mapMinX = 0, mapMaxX = 0, mapMinY = 0, mapMaxY = 0;

        // Loop through each object
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = objectKeys.size(); i < size; i++) {
            // Get the object section
            ConfigurationSection objectConfig = objectsConfig.getConfigurationSection(objectKeys.get(i));

            // Create a list of configuration sections that specify the position
            List<ConfigurationSection> positionSections = new ArrayList<>();

            // Add the regular position configuration if available
            if(objectConfig.isConfigurationSection("pos"))
                positionSections.add(objectConfig.getConfigurationSection("pos"));

            // Check whether this game object has position sets
            if(objectConfig.isConfigurationSection("posSet")) {
                // Get a list of all set keys
                List<String> posSets = objectConfig.getKeys("posSet");

                // Add all their configuration sections to the position sections list
                //noinspection ForLoopReplaceableByForEach
                for(int j = 0, jSize = posSets.size(); j < jSize; j++)
                    positionSections.add(objectConfig.getConfigurationSection("posSet." + posSets.get(j)));
            }

            // Get the object type and data value if available
            String rawObjectType = objectConfig.getString("type");
            int dataValue = objectConfig.getInt("dataValue", 0);

            // Loop through each position section to parse the position
            //noinspection ForLoopReplaceableByForEach
            for(int positionIndex = 0, positionIndexSize = positionSections.size(); positionIndex < positionIndexSize; positionIndex++) {
                // Get the configuration section
                ConfigurationSection positionConfig = positionSections.get(positionIndex);

                // Parse the X and Y value
                String rawPositionX = positionConfig.getString("x", String.valueOf(positionConfig.getInt("x", 0)));
                String rawPositionY = positionConfig.getString("y", String.valueOf(positionConfig.getInt("y", 0)));

                // Calculate the minimum and maximum X and Y positions
                int fromX, toX, fromY, toY;

                // Check whether the X coordinate contains any colon character
                if(rawPositionX.contains(":")) {
                    // Split the raw position
                    String[] split = rawPositionX.trim().split(":");

                    // Parse the values
                    fromX = Integer.parseInt(split[0]);
                    toX = Integer.parseInt(split[1]);

                } else
                    fromX = toX = Integer.parseInt(rawPositionX);

                // Check whether the Y coordinate contains any colon character
                if(rawPositionY.contains(":")) {
                    // Split the raw position
                    String[] split = rawPositionY.trim().split(":");

                    // Parse the values
                    fromY = Integer.parseInt(split[0]);
                    toY = Integer.parseInt(split[1]);

                } else
                    fromY = toY = Integer.parseInt(rawPositionY);

                // Initialize the map's minimum and maximum coordinates for the first position
                if(!mapCoordinatesInit) {
                    mapMinX = fromX;
                    mapMaxX = fromX;
                    mapMinY = fromY;
                    mapMaxY = fromY;
                    mapCoordinatesInit = true;
                }

                // Find the minimum and maximum coordinates for the map with the current position
                mapMinX = Math.min(Math.min(fromX, toX), mapMinX);
                mapMaxX = Math.max(Math.max(fromX, toX), mapMaxX);
                mapMinY = Math.min(Math.min(fromY, toY), mapMinY);
                mapMaxY = Math.max(Math.max(fromY, toY), mapMaxY);

                // Loop through the positions
                for(int x = fromX; fromX < toX ? x <= toX : x >= toX; x += fromX < toX ? 1 : -1)
                    for(int y = fromY; fromY < toY ? y <= toY : y >= toY; y += fromY < toY ? 1 : -1)
                        buildObject(rawObjectType, dataValue, x, y);
            }
        }

        // Spawn the player
        if(this.playerController != null)
            // TODO: Remove the getOwner() reference getter usage
            this.playerController.getOwner().addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f, new RigidbodyComponent(false)));

        // Spawn some randomized blocks outside the map
        for(int i = 0; i < 4; i++) {
            // Enlarge the outer edges by one
            mapMinX--;
            mapMinY--;
            mapMaxX++;
            mapMaxY++;

            // Loop through the edges of the map
            for(int x = mapMinX; x <= mapMaxX; x++) {
                for(int y = mapMinY; y <= mapMaxY; y++) {
                    // Only place blocks for walls
                    if(x != mapMinX && x != mapMaxX && y != mapMinY && y != mapMaxY)
                        continue;

                    // Determine whether to spawn a dummy block
                    if(Math.random() < Math.min(0.1f * (3 - i), 0.2f))
                        buildObject("wall", 0, x, y);
                }
            }
        }
    }

    /**
     * Build the object with the given type.
     *
     * @param rawType Raw object type.
     * @param dataValue Data value.
     * @param x X coordinate of the object.
     * @param y Y coordinate of the object.
     */
    private void buildObject(String rawType, int dataValue, int x, int y) {
        // Invert the y axis
        y *= -1;

        // Create a wall
        if(rawType.trim().equalsIgnoreCase("wall"))
            this.levelRoot.addChild(new BoxPrefab(new Vector3f(x + 0.5f, 0.5f, y + 0.5f), false, this.delay += 0.02f, -1f));

        // Create a player and store the player controller component reference
        else if(rawType.trim().equals("player")) {
            // Create the player prefab and set it's position
            PlayerPrefab playerObject = new PlayerPrefab(this.gameScene);
            playerObject.getTransform().setPosition(new Vector3f(x + 0.5f, 0.5f, y + 0.5f));

            // Add the player prefab to the level root
            this.levelRoot.addChild(playerObject);

            // Store the player controller component reference of the player
            this.playerController = playerObject.getPlayerController();

            // Set the level hint
            this.playerController.setHint(this.levelHint);
        }

        // Create a key
        else if(rawType.trim().equals("key")) {
            KeyPickupPrefab keyObject = new KeyPickupPrefab("KeyPickupPrefab", this.playerController, LockType.fromDataValue(dataValue));
            keyObject.getTransform().getPosition().set(x + 0.5f, 0, y + 0.5f);
            keyObject.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f));
            this.levelRoot.addChild(keyObject);
        }

        // Create a lock
        else if(rawType.trim().equals("lock")) {
            PadlockPrefab padlockObject = new PadlockPrefab(this.playerController, LockType.fromDataValue(dataValue));
            padlockObject.getTransform().getPosition().set(x + 0.5f, 0, y + 0.5f);
            padlockObject.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f, new RigidbodyComponent(true)));
            this.levelRoot.addChild(padlockObject);
        }

        // Create a lamp
        else if(rawType.trim().equals("lamp")) {
            LampPrefab lampObject = new LampPrefab(LockType.fromDataValue(dataValue).getColorCopy());
            lampObject.getTransform().getPosition().set(x + 0.5f, 0.01f, y + 0.5f);
            lampObject.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f));
            this.levelRoot.addChild(lampObject);
        }

        // Create a finish
        else if(rawType.trim().equals("finish")) {
            FinishPrefab finish = new FinishPrefab(this.playerController);
            finish.getTransform().getPosition().set(x + 0.5f, 0.01f, y + 0.5f);
            finish.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f));
            this.levelRoot.addChild(finish);
        }

        // Show errors
        else
            System.out.println("Unknown level object: " + rawType);
    }

    /**
     * Get the player controller component instance from the builder.
     *
     * @return Player controller component instance.
     */
    public PlayerControllerComponent getPlayerController() {
        return this.playerController;
    }
}
