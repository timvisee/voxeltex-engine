package me.keybarricade;

import me.keybarricade.voxeltex.component.drawable.line.AxisDrawComponent;
import me.keybarricade.voxeltex.component.drawable.line.GridDrawComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.camera.FpsCameraPrefab;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import me.keybarricade.voxeltex.scene.Scene;
import me.keybarricade.voxeltex.shader.ShaderManager;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
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
        gridObject.addComponent(new GridDrawComponent());
        gridObject.addComponent(new AxisDrawComponent());
        addGameObject(gridObject);

        // Load the box texture
        Texture boxTexture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        Material boxMaterial = new Material(ShaderManager.SHADER_DEFAULT_TEXTURED, boxTexture);

        // Spawn 5 boxes
        for(int i = 0; i < 5; i++) {
            CubePrefab cube = new CubePrefab("Cube");
            cube.getTransform().setPosition(new Vector3f(2.0f * (i + 1), 0, 0));
            cube.setMaterial(boxMaterial);
            addGameObject(cube);
        }
    }
}
