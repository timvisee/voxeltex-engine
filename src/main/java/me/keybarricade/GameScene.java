package me.keybarricade;

import me.keybarricade.gameobject.SandSurfacePrefab;
import me.keybarricade.voxeltex.component.drawable.line.AxisDrawComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
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

        // Load the box texture
        Texture boxTexture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        Material boxMaterial = new Material(boxTexture);

        // Spawn 5 boxes
        for(int i = 0; i < 5; i++) {
            CubePrefab boxObject = new CubePrefab("Box");
            boxObject.getTransform().setPosition(new Vector3f(2.0f * (i + 1), 0.5f, 0));
            boxObject.setMaterial(boxMaterial);
            addGameObject(boxObject);
        }

        // Create and add the sand surface prefab
        addGameObject(new SandSurfacePrefab());

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDB813).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);
    }
}
