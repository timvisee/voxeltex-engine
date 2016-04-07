package me.keybarricade.game.prefab;

import me.keybarricade.game.asset.GameAssetLoader;
import me.keybarricade.voxeltex.component.collider.primitive.BoxColliderComponent;
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

public class PadlockPrefab extends GameObject {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "PadlockPickupPrefab";

    /**
     * Constructor.
     */
    public PadlockPrefab() {
        this(GAME_OBJECT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public PadlockPrefab(String name) {
        // Construct the parent with the proper size
        super(name);

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
}
