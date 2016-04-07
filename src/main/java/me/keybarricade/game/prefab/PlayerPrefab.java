package me.keybarricade.game.prefab;

import me.keybarricade.game.LockType;
import me.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import me.keybarricade.voxeltex.component.collider.primitive.SphereColliderComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.movement.WasdPhysicsMovementComponent;
import me.keybarricade.voxeltex.component.overlay.gui.GuiImageComponent;
import me.keybarricade.voxeltex.component.transform.HorizontalTransformAnchorType;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;

public class PlayerPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "PlayerPrefab";

    /**
     * The type of key the player has currently picked up, null if none.
     */
    private LockType lockType = null;

    /**
     * Player material.
     */
    private Material playerMaterial;

    private GuiImageComponent keyImage;

    /**
     * Constructor.
     */
    public PlayerPrefab() {
        this(GAME_OBJECT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public PlayerPrefab(String name) {
        // Construct the parent with the proper size
        super(name);

        // Load the sphere mesh
        Mesh sphereMesh = new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/sphere.obj"));

        // Create the player material
        this.playerMaterial = new Material(Texture.fromColor(Color.BLUE, 1, 1));

        // Create the mesh filter and renderer
        addComponent(new MeshFilterComponent(sphereMesh));
        addComponent(new MeshRendererComponent(this.playerMaterial));

        // Set the position of the player
        getTransform().setScale(0.3f, 0.3f, 0.3f);

        // Add the movement component
        addComponent(new WasdPhysicsMovementComponent());

        // Create a proper collider
        addComponent(new SphereColliderComponent(0.3f));

        // Load the developer splash image
        Image developerSplashImage = Image.loadFromEngineAssets("images/key.png");

        // Create the base menu panel
        GameObject keyPanel = new GameObject("KeyPanel");
        keyPanel.addComponent(new RectangleTransform(
                new Vector2f(-(96f / 2) - 32, +(96f / 2) + 32),
                new Vector2f(96f, 96f),
                HorizontalTransformAnchorType.RIGHT,
                VerticalTransformAnchorType.BOTTOM
        ));
        this.keyImage = new GuiImageComponent(developerSplashImage);
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

        // Process padlocks
        if(gameObject instanceof PadlockPrefab) {
            // Get the padlock prefab
            PadlockPrefab padlock = (PadlockPrefab) gameObject;

            // Make sure we've the correct key type
            if(padlock.getLockType().equals(getPickupLockType())) {
                // Decay the padlock
                gameObject.addComponent(new ObjectDecayAnimatorComponent(0.0f));

                // TODO: Ensure this is enough!
            }
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
        this.keyImage.setAlpha(0.5f);
        // TODO: Set the proper key color!
    }
}
