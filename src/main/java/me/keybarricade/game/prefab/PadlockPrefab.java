package me.keybarricade.game.prefab;

import me.keybarricade.game.LockType;
import me.keybarricade.game.asset.GameAssetLoader;
import me.keybarricade.voxeltex.component.collider.primitive.BoxColliderComponent;
import me.keybarricade.voxeltex.component.light.LightSourceComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector3f;

public class PadlockPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "PadlockPickupPrefab";

    /**
     * Distance trigger.
     */
    private static final float PICKUP_TRIGGER_DISTANCE = 0.8f;

    /**
     * Reference to player prefab. Used to calculate whether to pickup the key or not.
     */
    private PlayerPrefab player;

    /**
     * Key for the given lock type.
     */
    private LockType type;

    /**
     * Constructor.
     *
     * @param type Lock type.
     */
    public PadlockPrefab(LockType type) {
        this(GAME_OBJECT_NAME, null, type);
    }

    /**
     * Constructor.
     *
     * @param player Player reference.
     *
     * @param type Lock type.
     */
    public PadlockPrefab(PlayerPrefab player, LockType type) {
        this(GAME_OBJECT_NAME, player, type);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param player Player reference.
     *
     * @param type Lock type.
     */
    public PadlockPrefab(String name, PlayerPrefab player, LockType type) {
        // Construct the parent with the proper size
        super(name);

        // Set the player and type
        this.player = player;
        this.type = type;

        // Load the padlock material
        Material lockMaterial = new Material(Texture.fromColor(new Color(1, 1, 0), 1, 1));

        // Load the padlock mesh
        Mesh padlockMesh = new Mesh(
                ObjModelLoader.loadModelFromInputStream(
                        GameAssetLoader.getInstance().loadResourceStream("models/padlock.obj")
                )
        );

        // Add a collider
        addComponent(new BoxColliderComponent(new Vector3f(0.6f, 0.6f, 2f)));

        // Create a child game object that holds the padlock model
        GameObject padlockModelObject = new GameObject("PadlockRenderer");
        padlockModelObject.addComponent(new MeshFilterComponent(padlockMesh));
        padlockModelObject.addComponent(new MeshRendererComponent(lockMaterial));
        padlockModelObject.getTransform().getPosition().y = 0.05f;
        padlockModelObject.getTransform().getAngularVelocity().y = 0.6f;
        addChild(padlockModelObject);

        // Create a child game object that holds the padlock light
        GameObject padlockLightObject = new GameObject("PadlockLight");
        padlockLightObject.getTransform().getPosition().y = 0.65f;
        padlockLightObject.getTransform().getPosition().y = 1f;
        padlockLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, new Vector3f(1, 1, 0), 0.05f));
        addChild(padlockLightObject);
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
     * Get the lock type.
     *
     * @return Lock type.
     */
    public LockType getType() {
        return this.type;
    }

    /**
     * Set the lock type.
     *
     * @param type Lock type.
     */
    public void setType(LockType type) {
        this.type = type;
    }
}
