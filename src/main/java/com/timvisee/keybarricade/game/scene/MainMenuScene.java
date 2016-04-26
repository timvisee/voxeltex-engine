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

import com.timvisee.keybarricade.KeyBarricade;
import com.timvisee.keybarricade.game.component.MainMenuSpawnerComponent;
import com.timvisee.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import com.timvisee.keybarricade.game.entity.LockType;
import com.timvisee.keybarricade.game.entity.prefab.BoxPrefab;
import com.timvisee.keybarricade.game.entity.prefab.GroundPrefab;
import com.timvisee.keybarricade.game.entity.prefab.LampPrefab;
import com.timvisee.voxeltex.architecture.component.camera.CameraComponent;
import com.timvisee.voxeltex.architecture.component.overlay.gui.GuiPanelComponent;
import com.timvisee.voxeltex.architecture.gameobject.GameObject;
import com.timvisee.voxeltex.architecture.prefab.gui.GuiButtonPrefab;
import com.timvisee.voxeltex.architecture.prefab.gui.GuiLabelPrefab;
import com.timvisee.voxeltex.architecture.prefab.light.LightPrefab;
import com.timvisee.voxeltex.architecture.scene.Scene;
import com.timvisee.voxeltex.engine.light.Light;
import com.timvisee.voxeltex.module.Color;
import com.timvisee.voxeltex.module.transform.rectangle.RectangleTransform;
import com.timvisee.voxeltex.module.transform.rectangle.anchor.HorizontalTransformAnchorType;
import com.timvisee.voxeltex.module.transform.rectangle.anchor.VerticalTransformAnchorType;
import com.timvisee.voxeltex.runtime.global.Input;
import com.timvisee.voxeltex.runtime.input.mouse.MouseInputManager;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class MainMenuScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Set the mouse cursor mode
        Input.setMouseCursorMode(MouseInputManager.CURSOR_MODE_NORMAL);

        // Create the menu
        createMenu();

        // Create and add the sand surface prefab
        addGameObject(new GroundPrefab(1000));

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFFF4D6).toVector3f(), 0.5f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create the camera base
        GameObject cameraBase = new GameObject("CameraBase");
        cameraBase.getTransform().getAngularVelocity().set(0, -0.1f, 0);
        addGameObject(cameraBase);

        // Create a camera, and add it to the base
        GameObject camera = new GameObject("MainCamera");
        camera.addComponent(new CameraComponent());
        camera.getTransform().setPosition(new Vector3f(0, 5f, 15));
        camera.getTransform().setRotation(new Quaternionf(-.275f, 0, 0).normalize());
        cameraBase.addChild(camera);

        // Spawn some initial boxes
        for(int x = 0; x < 50; x++) {
            for(int z = 0; z < 50; z++) {
                // Randomize
                if(Math.random() > 0.05)
                    continue;

                // Determine whether to spawn a box or lamp
                if(Math.random() < 0.95f) {
                    // Spawn a box
                    BoxPrefab boxObject = new BoxPrefab(new Vector3f(-25 + x + 0.5f, 0.5f, -25 + z + 0.5f), true, -1f, -1f);
                    boxObject.addComponent(new ObjectDecayAnimatorComponent((float) (Math.random() * 10.0f)));
                    addGameObject(boxObject);

                } else {
                    // Create a lamp
                    LampPrefab lamp = new LampPrefab(
                            "LampPrefab",
                            LockType.fromDataValue((int) ((Math.random() * LockType.values().length) - 1)).getColorCopy()
                    );

                    // Set the position
                    lamp.getTransform().setPosition(x + 0.5f, 0.01f, z + 0.5f);

                    // Add a decay animator
                    lamp.addComponent(new ObjectDecayAnimatorComponent((float) (Math.random() * 10.0f)));

                    // Add the lamp
                    addGameObject(lamp);
                }
            }
        }

        // Create a cube spawner
        GameObject cubeSpawner = new GameObject("CubeSpawner");
        cubeSpawner.addComponent(new MainMenuSpawnerComponent());
        addGameObject(cubeSpawner);
    }

    /**
     * Create the toggleable menu and add it to the scene
     */
    private void createMenu() {
        // Create the base menu panel
        GameObject menuPanel = new GameObject("MenuPanel");
        menuPanel.addComponent(new RectangleTransform(
                new Vector2f(350 / 2 + 64, 165 / 2 + 64),
                new Vector2f(350, 165),
                HorizontalTransformAnchorType.LEFT,
                VerticalTransformAnchorType.BOTTOM
        ));
        menuPanel.addComponent(new GuiPanelComponent());
        addGameObject(menuPanel);

        // Create the menu title
        GuiLabelPrefab menuLabel = new GuiLabelPrefab("MenuLabel", KeyBarricade.APP_NAME);
        menuLabel.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        menuLabel.getRectangleTransform().setPositionTop(-(20 + 16)); // TODO: Invert this when stretched?
        menuLabel.setColor(Color.WHITE);
        menuPanel.addChild(menuLabel);

        // Create a new game button
        GuiButtonPrefab button = new GuiButtonPrefab("NewGameButton", "New Game") {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Load the main level
                getEngine().getSceneManager().loadScene(new GameScene());
            }
        };
        button.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8))); // TODO: Invert this when stretched?
        menuPanel.addChild(button);

        // Create an exit button
        GuiButtonPrefab button2 = new GuiButtonPrefab("ExitButton", "Exit") {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Exit
                System.out.println("Exit button clicked");
                getEngine().getRenderer().getWindow().glSetWindowShouldClose(true);
            }
        };
        button2.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button2.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 2)); // TODO: Invert this when stretched?
        menuPanel.addChild(button2);

        // Create the base version panel
        GameObject versionPanel = new GameObject("VersionPanel");
        versionPanel.addComponent(new RectangleTransform(
                new Vector2f(-(120 / 2 + 6), -(24 / 2 + 12)),
                new Vector2f(120, 24),
                HorizontalTransformAnchorType.RIGHT,
                VerticalTransformAnchorType.TOP
        ));
        addGameObject(versionPanel);

        // Create the version label
        GuiLabelPrefab versionLabel = new GuiLabelPrefab("VersionLabel", "Version " + KeyBarricade.APP_VERSION_NAME);
        versionLabel.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        versionLabel.getRectangleTransform().setPositionTop(-(24 / 2)); // TODO: Invert this when stretched?
        versionLabel.setColor(new Color(1, 1, 1, 0.35f));
        versionPanel.addChild(versionLabel);
    }
}
