package com.timvisee.keybarricade.game.prefab;

import com.timvisee.keybarricade.game.LockType;
import com.timvisee.keybarricade.game.asset.GameResourceBundle;
import com.timvisee.voxeltex.component.light.LightSourceComponent;
import com.timvisee.voxeltex.component.mesh.filter.MeshFilterComponent;
import com.timvisee.voxeltex.component.mesh.renderer.MeshRendererComponent;
import com.timvisee.voxeltex.gameobject.GameObject;
import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.texture.Texture;

public class KeyPickupPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "KeyPickupPrefab";

    /**
     * Distance trigger.
     */
    private static final float PICKUP_TRIGGER_DISTANCE = 0.5f;

    /**
     * Reference to player prefab. Used to calculate whether to pickup the key or not.
     */
    private PlayerPrefab player;

    /**
     * Key for the given lock lockType.
     */
    private LockType lockType;

    /**
     * Constructor.
     *
     * @param lockType Lock lockType.
     */
    public KeyPickupPrefab(LockType lockType) {
        this(GAME_OBJECT_NAME, null, lockType);
    }

    /**
     * Constructor.
     *
     * @param player Player reference.
     * @param lockType Lock lockType.
     */
    public KeyPickupPrefab(PlayerPrefab player, LockType lockType) {
        this(GAME_OBJECT_NAME, player, lockType);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param player Player reference.
     * @param lockType Lock lockType.
     */
    public KeyPickupPrefab(String name, PlayerPrefab player, LockType lockType) {
        // Construct the parent with the proper size
        super(name);

        // Set the player and lockType
        this.player = player;
        this.lockType = lockType;

        // Rotate the base object around
        getTransform().getAngularVelocity().y = 0.6f;

        // Generate the key material
        Material keyMaterial = new Material(Texture.fromColor(lockType.getColor(), 1, 1));

        // Create a child game object that holds the key model
        GameObject keyModelObject = new GameObject("KeyPickupModel");
        keyModelObject.addComponent(new MeshFilterComponent(GameResourceBundle.getInstance().MESH_KEY));
        keyModelObject.addComponent(new MeshRendererComponent(keyMaterial));
        keyModelObject.getTransform().getPosition().y = 0.2f;
        keyModelObject.getTransform().getAngularVelocity().x = 2f;
        addChild(keyModelObject);

        // Create a child game object that holds the key light
        GameObject keyLightObject = new GameObject("KeyPickupModel");
        keyLightObject.getTransform().getPosition().y = 0.60f;
        keyLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, lockType.getColor().toVector3f(), 0.2f));
        addChild(keyLightObject);
    }

    @Override
    public synchronized void update() {
        // Call the super
        super.update();

        // Make sure a player reference is given
        if(this.player != null) {
            // Calculate the distance (squared) to the player
            float distance = this.player.getTransform().getPosition().distanceSquared(getTransform().getPosition());

            // Determine whether to pickup the item, trigger the player if that's the case
            if(distance <= PICKUP_TRIGGER_DISTANCE * PICKUP_TRIGGER_DISTANCE)
                this.player.onTrigger(this);
        }
    }

    /**
     * Get the attached player.
     *
     * @return Attached player.
     */
    public PlayerPrefab getPlayer() {
        return this.player;
    }

    /**
     * Set the attached player.
     *
     * @param player Player.
     */
    public void setPlayer(PlayerPrefab player) {
        this.player = player;
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
