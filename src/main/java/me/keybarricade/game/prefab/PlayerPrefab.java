package me.keybarricade.game.prefab;

import me.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import me.keybarricade.voxeltex.component.collider.primitive.SphereColliderComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.movement.WasdPhysicsMovementComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;

public class PlayerPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "PlayerPrefab";

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

        // Create the mesh filter and renderer
        addComponent(new MeshFilterComponent(sphereMesh));
        addComponent(new MeshRendererComponent(new Material(Texture.fromColor(Color.BLUE, 1, 1))));

        // Set the position of the player
        getTransform().setScale(0.3f, 0.3f, 0.3f);

        // Add the movement component
        addComponent(new WasdPhysicsMovementComponent());

        // Create a proper collider
        addComponent(new SphereColliderComponent(0.3f));
    }

    /**
     * Trigger the player for the given game object.
     *
     * @param gameObject The game object that is triggering.
     */
    public void onTrigger(GameObject gameObject) {
        System.out.println("Triggered by " + gameObject.getName());


        if(gameObject instanceof PadlockPrefab) {
            gameObject.addComponent(new ObjectDecayAnimatorComponent(0.0f));
            //gameObject.destroy();
        }
    }
}
