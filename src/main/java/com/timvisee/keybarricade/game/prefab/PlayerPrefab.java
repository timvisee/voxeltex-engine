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
import com.timvisee.keybarricade.game.scene.GameScene;
import com.timvisee.voxeltex.component.collider.primitive.SphereColliderComponent;
import com.timvisee.voxeltex.component.light.LightSourceComponent;
import com.timvisee.voxeltex.component.mesh.filter.MeshFilterComponent;
import com.timvisee.voxeltex.component.mesh.renderer.MeshRendererComponent;
import com.timvisee.voxeltex.component.movement.WasdPhysicsMovementComponent;
import com.timvisee.voxeltex.component.overlay.gui.GuiImageComponent;
import com.timvisee.voxeltex.component.overlay.gui.GuiLabelComponent;
import com.timvisee.voxeltex.component.overlay.gui.GuiPanelComponent;
import com.timvisee.voxeltex.component.transform.HorizontalTransformAnchorType;
import com.timvisee.voxeltex.component.transform.RectangleTransform;
import com.timvisee.voxeltex.component.transform.VerticalTransformAnchorType;
import com.timvisee.voxeltex.gameobject.GameObject;
import com.timvisee.voxeltex.global.Time;
import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.texture.Texture;
import com.timvisee.voxeltex.util.Color;
import org.joml.Vector2f;

public class PlayerPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "PlayerPrefab";

    /**
     * Game scene instance.
     */
    private final GameScene gameScene;

    /**
     * The type of key the player has currently picked up, null if none.
     */
    private LockType lockType = null;

    /**
     * Player material.
     */
    private Material playerMaterial;

    /**
     * Light source component of the player.
     */
    private LightSourceComponent lightSource;

    /**
     * GUI component to render the key image.
     */
    private GuiImageComponent keyImage;

    /**
     * Game object the hint is rendered with.
     */
    private GameObject hintPanel;

    /**
     * GUI component to render the hint text.
     */
    private GuiLabelComponent hintLabel;

    /**
     * Time to show the hint at.
     */
    private float showHintAt = -1.0f;

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

        // Store the game scene instance
        this.gameScene = gameScene;

        // Create the player material
        this.playerMaterial = new Material(Texture.fromColor(Color.ORANGE, 1, 1));

        // Create the mesh filter and renderer
        addComponent(new MeshFilterComponent(GameResourceBundle.getInstance().MESH_SPHERE));
        addComponent(new MeshRendererComponent(this.playerMaterial));

        // Set the position of the player
        getTransform().setScale(0.3f, 0.3f, 0.3f);

        // Add the movement component
        addComponent(new WasdPhysicsMovementComponent());

        // Create a proper collider
        addComponent(new SphereColliderComponent(0.3f));

        // Add a light source component to the player
        this.lightSource = new LightSourceComponent(Light.LIGHT_TYPE_POINT, Color.ORANGE.toVector3f(), 0.05f);
        addComponent(this.lightSource);

        // Hint panel
        this.hintPanel = new GameObject("HintPanel");
        this.hintPanel.addComponent(new RectangleTransform(
                new Vector2f(64, -(64 + (32f / 2f))),
                new Vector2f(64, 32f),
                HorizontalTransformAnchorType.STRETCH,
                VerticalTransformAnchorType.TOP
        ));
        GameObject hintLabel = new GameObject("HintLabel");
        hintLabel.addComponent(new RectangleTransform(
                new Vector2f(4, 4),
                new Vector2f(4, 4),
                HorizontalTransformAnchorType.STRETCH,
                VerticalTransformAnchorType.STRETCH
        ));
        this.hintLabel = new GuiLabelComponent("", Color.WHITE);
        hintLabel.addComponent(this.hintLabel);
        hintPanel.addChild(hintLabel);
        hintPanel.addComponent(new GuiPanelComponent());
        addChild(hintPanel);
        this.hintPanel.setEnabled(false);

        // Create the base menu panel
        GameObject keyPanel = new GameObject("KeyPanel");
        keyPanel.addComponent(new RectangleTransform(
                new Vector2f(-(72f / 2) - 32, +(72f / 2) + 32),
                new Vector2f(72f, 72f),
                HorizontalTransformAnchorType.RIGHT,
                VerticalTransformAnchorType.BOTTOM
        ));
        this.keyImage = new GuiImageComponent(GameResourceBundle.getInstance().IMAGE_KEY);
        this.keyImage.setAlpha(0f);
        keyPanel.addComponent(this.keyImage);
        addChild(keyPanel);
    }

    /**
     * Trigger the player for the given game object.
     *
     * @param gameObject The game object that is triggering.
     */
    public void onTrigger(GameObject gameObject) {
        // Process keys
        if(gameObject instanceof KeyPickupPrefab) {
            // Get the key prefab
            KeyPickupPrefab key = (KeyPickupPrefab) gameObject;

            // Set the currently picked up lock type
            setPickupLockType(key.getLockType());

            // Decay the key object
            key.addComponent(new ObjectDecayAnimatorComponent(0f));
        }

        // Process finish
        else if(gameObject instanceof FinishPrefab)
            // Finish the current level
            this.gameScene.finishLevel();
    }

    @Override
    public synchronized void update() {
        // Call the super
        super.update();

        // Show the hint panel if it's time
        if(this.showHintAt >= 0.0f && this.showHintAt <= Time.timeFloat) {
            // Show the hint panel
            this.hintPanel.setEnabled(true);

            // Disable the timer
            this.showHintAt = -1f;
        }
    }

    /**
     * Get the pickup lock type. Null if the player hasn't picked up anything.
     *
     * @return Lock type, or null.
     */
    public LockType getPickupLockType() {
        return lockType;
    }

    /**
     * Set the pickup lock type. Null if the player hasn't picked up anything.
     *
     * @param lockType Lock type, or null.
     */
    public void setPickupLockType(LockType lockType) {
        // Set the picked up lock
        this.lockType = lockType;

        // Update the player material
        this.playerMaterial.setTexture(Texture.fromColor(lockType.getColor(), 1, 1));

        // Set the key image
        this.keyImage.setColor(lockType.getColorCopy());
        this.keyImage.setAlpha(0.75f);

        // Update the light source color of the player
        this.lightSource.setLightColor(lockType.getColor().toVector3f());
        this.lightSource.setLightBrightness(0.09f);
    }

    /**
     * Set the player hint.
     *
     * @param hintText Hint.
     */
    public void setHint(String hintText) {
        // Make sure the hint contains any text
        if(hintText == null || hintText.trim().length() == 0) {
            this.hintPanel.setEnabled(false);
            return;
        }

        // Set the hint text
        this.hintLabel.setText(hintText);

        // Set the hint timer
        this.showHintAt = Time.timeFloat + 2f;
    }
}
