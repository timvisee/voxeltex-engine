package me.keybarricade;

import me.keybarricade.gameobject.SandSurfacePrefab;
import me.keybarricade.voxeltex.component.collider.primitive.SphereColliderComponent;
import me.keybarricade.voxeltex.component.drawable.line.AxisDrawComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.prefab.camera.FpsCameraPrefab;
import me.keybarricade.voxeltex.prefab.light.LightPrefab;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import me.keybarricade.voxeltex.scene.Scene;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector3f;

public class GameScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Create the main camera object
        FpsCameraPrefab fpsCameraPrefab = new FpsCameraPrefab();
        fpsCameraPrefab.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
        addGameObject(fpsCameraPrefab);

        // Create an object to render the center axis and grid
        GameObject gridObject = new GameObject("AxisGridRenderer");
        //gridObject.addComponent(new GridDrawComponent());
        gridObject.addComponent(new AxisDrawComponent());
        addGameObject(gridObject);

        // Load the box texture and material
        Texture boxTexture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        Material boxMaterial = new Material(boxTexture);

        // Load the sphere mesh
        Mesh sphereMesh = new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/sphere.obj"));

        // Spawn 5 boxes
        for(int i = 0; i < 5; i++) {
            CubePrefab boxObject = new CubePrefab("Box");
            boxObject.getTransform().setPosition(new Vector3f(2.0f * (i + 1), 0.5f, 0));
            boxObject.setMaterial(boxMaterial);
            addGameObject(boxObject);
        }

        // Spawn two spheres and a cube as collision demo
        GameObject sphereObject1 = new GameObject("Sphere1");
        sphereObject1.addComponent(new MeshFilterComponent(sphereMesh));
        sphereObject1.addComponent(new MeshRendererComponent(new Material(Texture.fromColor(Color.ORANGE, 1, 1))));
        sphereObject1.addComponent(new RigidbodyComponent());
        sphereObject1.addComponent(new SphereColliderComponent());
        sphereObject1.getTransform().getPosition().set(-1.1f, 2.5f, -1.1f);
        addGameObject(sphereObject1);
        GameObject sphereObject2 = new GameObject("Sphere2");
        sphereObject2.addComponent(new MeshFilterComponent(sphereMesh));
        sphereObject2.addComponent(new MeshRendererComponent(new Material(Texture.fromColor(Color.ORANGE, 1, 1))));
        sphereObject2.addComponent(new RigidbodyComponent());
        sphereObject2.addComponent(new SphereColliderComponent());
        sphereObject2.getTransform().getPosition().set(-0.9f, 6.5f, -0.9f);
        addGameObject(sphereObject2);
        CubePrefab cubeObject = new CubePrefab("Cube");
        cubeObject.addComponent(new RigidbodyComponent());
        cubeObject.setMaterial(new Material(Texture.fromColor(Color.RED, 1, 1)));
        cubeObject.getTransform().getPosition().set(-1f, 4.5f, -1f);
        addGameObject(cubeObject);

        // Create and add the sand surface prefab
        addGameObject(new SandSurfacePrefab());

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDB813).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);
    }
}
