package me.keybarricade.game.prefab;

import me.keybarricade.game.asset.GameAssetLoader;
import me.keybarricade.voxeltex.component.light.LightSourceComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector3f;

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
     * Constructor.
     */
    public KeyPickupPrefab() {
        this(GAME_OBJECT_NAME, null);
    }

    /**
     * Constructor.
     *
     * @param player Player reference.
     */
    public KeyPickupPrefab(PlayerPrefab player) {
        this(GAME_OBJECT_NAME, player);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param player Player reference.
     */
    public KeyPickupPrefab(String name, PlayerPrefab player) {
        // Construct the parent with the proper size
        super(name);

        // Set the player instance
        this.player = player;

        // Rotate the base object around
        getTransform().getAngularVelocity().y = 0.6f;

        // Load the key material
        Material keyMaterial = new Material(Texture.fromColor(new Color(1, 1, 0), 1, 1));

        // Load the key mesh
        Mesh keyMesh = new Mesh(
                ObjModelLoader.loadModelFromInputStream(
                        GameAssetLoader.getInstance().loadResourceStream("models/key.obj")
                )
        );

        // Create a child game object that holds the key model
        GameObject keyModelObject = new GameObject("KeyPickupModel");
        keyModelObject.addComponent(new MeshFilterComponent(keyMesh));
        keyModelObject.addComponent(new MeshRendererComponent(keyMaterial));
        keyModelObject.getTransform().getPosition().y = 0.2f;
        keyModelObject.getTransform().getAngularVelocity().x = 2f;
        addChild(keyModelObject);

        // Create a child game object that holds the key light
        GameObject keyLightObject = new GameObject("KeyPickupModel");
        keyLightObject.getTransform().getPosition().y = 0.65f;
        keyLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, new Vector3f(1, 1, 0), 0.05f));
        addChild(keyLightObject);

        // Add a kinematic rigidbody for collision
        addComponent(new RigidbodyComponent(true));
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
}
