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
     * Constructor.
     */
    public KeyPickupPrefab() {
        this(GAME_OBJECT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public KeyPickupPrefab(String name) {
        // Construct the parent with the proper size
        super(name);

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
}
