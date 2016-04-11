package com.timvisee.keybarricade.demo;

import com.timvisee.keybarricade.game.asset.GameResourceBundle;
import com.timvisee.keybarricade.game.prefab.GroundPrefab;
import com.timvisee.voxeltex.component.light.LightSourceComponent;
import com.timvisee.voxeltex.light.Light;
import com.timvisee.voxeltex.prefab.camera.FpsCameraPrefab;
import com.timvisee.voxeltex.prefab.light.LightPrefab;
import com.timvisee.voxeltex.prefab.primitive.CubePrefab;
import com.timvisee.voxeltex.scene.Scene;
import com.timvisee.voxeltex.util.Color;

public class DemoScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Load all game resources
        GameResourceBundle.getInstance().load();

        // Add a light simulating the sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDDC5C).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create a surface
        addGameObject(new GroundPrefab());


        // Demo code here:


        FpsCameraPrefab camera = new FpsCameraPrefab();
        camera.getTransform().setPosition(0, 2, 3);
        addGameObject(camera);

        for (int i = 0; i < 15; i++) {
            CubePrefab box = new CubePrefab();
            box.getTransform().setPosition(i, 0.5f, 0);
            box.setMaterial(GameResourceBundle.getInstance().MATERIAL_BOX0);
            box.addComponent(new LightSourceComponent(Color.random()));
            addGameObject(box);
        }
    }
}
