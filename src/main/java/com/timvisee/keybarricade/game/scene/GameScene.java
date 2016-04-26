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

package com.timvisee.keybarricade.game.scene;

import com.timvisee.keybarricade.game.asset.GameResourceBundle;
import com.timvisee.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import com.timvisee.keybarricade.game.entity.prefab.GroundPrefab;
import com.timvisee.keybarricade.game.level.LevelBuilder;
import com.timvisee.keybarricade.game.level.LevelManager;
import com.timvisee.voxeltex.architecture.component.other.follow.SmoothTopDownFollowComponent;
import com.timvisee.voxeltex.architecture.component.overlay.gui.GuiPanelComponent;
import com.timvisee.voxeltex.architecture.component.overlay.gui.menu.ToggleableMenuComponent;
import com.timvisee.voxeltex.architecture.gameobject.GameObject;
import com.timvisee.voxeltex.architecture.prefab.camera.MouseLookCameraPrefab;
import com.timvisee.voxeltex.architecture.prefab.gui.GuiButtonPrefab;
import com.timvisee.voxeltex.architecture.prefab.gui.GuiLabelPrefab;
import com.timvisee.voxeltex.architecture.prefab.light.LightPrefab;
import com.timvisee.voxeltex.architecture.scene.Scene;
import com.timvisee.voxeltex.engine.light.Light;
import com.timvisee.voxeltex.module.Color;
import com.timvisee.voxeltex.module.transform.rectangle.RectangleTransform;
import com.timvisee.voxeltex.module.transform.rectangle.anchor.RectangleTransformAnchor;
import com.timvisee.voxeltex.module.transform.rectangle.anchor.VerticalTransformAnchorType;
import com.timvisee.voxeltex.runtime.global.Input;
import com.timvisee.voxeltex.runtime.global.Time;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class GameScene extends Scene {

    /**
     * Level base object.
     */
    private GameObject levelBase;

    /**
     * Smooth top down follow component used to follow the player.
     */
    private SmoothTopDownFollowComponent smoothCameraFollow;

    /**
     * Level manager.
     */
    private LevelManager levelManager;

    /**
     * Current level index.
     */
    private int currentLevel = 0;

    /**
     * Last time the game was restarted.
     */
    private float lastRestart = -1.0f;

    @Override
    public void load() {
        // Load the super
        super.load();

        // Set the level manager instance
        this.levelManager = GameResourceBundle.getInstance().LEVEL_MANAGER;

        // Create the menu
        createMenu();

        // Create and add the sand surface prefab
        addGameObject(new GroundPrefab());

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFFF4D6).toVector3f(), 0.5f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create the base level object
        this.levelBase = new GameObject("LevelBase");
        addGameObject(this.levelBase);

        // Create the camera prefab
        MouseLookCameraPrefab cameraPrefab = new MouseLookCameraPrefab();
        cameraPrefab.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
        cameraPrefab.addComponent(this.smoothCameraFollow = new SmoothTopDownFollowComponent());
        this.smoothCameraFollow.setPositionDamping(2f);
        addGameObject(cameraPrefab);

        // Load the level
        loadLevel();
    }

    /**
     * Create the toggleable menu and add it to the scene
     */
    private void createMenu() {
        // Create the base menu panel
        GameObject menuPanel = new GameObject("OverlayTest");
        menuPanel.addComponent(new RectangleTransform(
                new Vector2f(0, 0),
                new Vector2f(350, 215),
                new RectangleTransformAnchor(0.5f, 0.6f, 0.5f, 0.6f)
        ));
        menuPanel.addComponent(new GuiPanelComponent());
        addGameObject(menuPanel);

        // Create a toggleable menu controller
        final ToggleableMenuComponent menuController = new ToggleableMenuComponent(menuPanel);

        // Create the menu label
        GuiLabelPrefab menuLabel = new GuiLabelPrefab("Button", "Menu");
        menuLabel.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        menuLabel.getRectangleTransform().setPositionTop(-(20 + 16)); // TODO: Invert this when stretched?
        menuLabel.setColor(Color.WHITE);
        menuPanel.addChild(menuLabel);

        // Create a restart button
        GuiButtonPrefab restartButton = new GuiButtonPrefab("RestartButton", "Restart") {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Request a level restart
                requestRestart();

                // Hide the menu
                menuController.setMenuVisible(false);
            }
        };
        restartButton.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        restartButton.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8))); // TODO: Invert this when stretched?
        menuPanel.addChild(restartButton);

        // Create a main menu button
        GuiButtonPrefab mainMenuButton = new GuiButtonPrefab("MainMenuButton", "Main Menu") {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Go to the main menu
                toMainMenu();
            }
        };
        mainMenuButton.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        mainMenuButton.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 2)); // TODO: Invert this when stretched?
        menuPanel.addChild(mainMenuButton);

        // Create an exit button
        GuiButtonPrefab exitButton = new GuiButtonPrefab("ExitButton", "Exit") {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Exit
                System.out.println("Exit button clicked");
                getEngine().getRenderer().getWindow().glSetWindowShouldClose(true);
            }
        };
        exitButton.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        exitButton.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 3)); // TODO: Invert this when stretched?
        menuPanel.addChild(exitButton);

        // Create a toggleable menu controller
        GameObject menuControllerObject = new GameObject("MenuController");
        menuControllerObject.addComponent(menuController);
        addGameObject(menuControllerObject);
    }

    /**
     * Load the level.
     */
    private void loadLevel() {
        loadLevel(0.5f);
    }

    /**
     * Load the level.
     *
     * @param delay Delay in seconds.
     */
    private void loadLevel(float delay) {
        // Create a level builder for the level with the specified level index
        LevelBuilder builder = new LevelBuilder(this.levelManager.getLevel(this.currentLevel), this, this.levelBase);

        // Build the level with a delay of 0.5 seconds
        builder.build(delay);

        // Set the camera target to the player
        this.smoothCameraFollow.setTarget(builder.getPlayerController().getOwner());
    }

    /**
     * Unload the level.
     */
    private void unloadLevel() {
        // Create a variable to calculate the spawn delays
        float delay = 0.0f;

        // Reset the camera target
        this.smoothCameraFollow.resetTarget();

        // Loop through all children of the level base, and make them decay
        for(int i = 0, size = this.levelBase.getChildCount(false); i < size; i++)
            this.levelBase.getChild(i).addComponent(new ObjectDecayAnimatorComponent(delay += 0.01f));
    }

    /**
     * Finish the current level.
     */
    public void finishLevel() {
        // Check whether a new level is available
        if(this.currentLevel >= this.levelManager.getLevelCount() - 1) {
            // Go to the main menu and return
            toMainMenu();
            return;
        }

        // Increase the current level index
        this.currentLevel += 1;

        // Unload the current level, and load the next one
        unloadLevel();
        loadLevel(1f);
    }

    /**
     * Go to the main menu.
     */
    public void toMainMenu() {
        getEngine().getSceneManager().loadScene(new MainMenuScene());
    }

    @Override
    public void update() {
        // Call the super
        super.update();

        // Check whether the restart key is pressed
        if(Input.isKeyDownOnce(GLFW.GLFW_KEY_R))
            requestRestart();
    }

    /**
     * Request a restart.
     */
    public void requestRestart() {
        // Make sure the player can restart again
        if(this.lastRestart + 1.5f > Time.timeFloat)
            return;

        // Update the last restart time
        this.lastRestart = Time.timeFloat;

        // Unload and reload the current level
        unloadLevel();
        loadLevel();
    }
}
