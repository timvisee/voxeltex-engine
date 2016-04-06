package me.keybarricade;

import me.keybarricade.gameobject.SandSurfacePrefab;
import me.keybarricade.voxeltex.component.camera.CameraComponent;
import me.keybarricade.voxeltex.component.overlay.gui.GuiPanelComponent;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.RectangleTransformAnchor;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.gui.GuiButtonPrefab;
import me.keybarricade.voxeltex.prefab.gui.GuiLabelPrefab;
import me.keybarricade.voxeltex.prefab.light.LightPrefab;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import me.keybarricade.voxeltex.scene.Scene;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class MainMenuScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Load the box texture and material
        Texture boxTexture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        Material boxMaterial = new Material(boxTexture);

        // Create the menu
        createMenu();

        // Create and add the sand surface prefab
        addGameObject(new SandSurfacePrefab(1000));

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDDC5C).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create the camera base
        GameObject cameraBase = new GameObject("CameraBase");
        cameraBase.getTransform().getAngularVelocity().set(0, -0.1f, 0);
        addGameObject(cameraBase);

        // Create a camera, and add it to the base
        GameObject camera = new GameObject("MainCamera");
        camera.addComponent(new CameraComponent());
        camera.getTransform().setPosition(new Vector3f(0, 5f, 15));
        camera.getTransform().setRotation(new Quaternionf(-.275f, 0, 0).normalize());
        cameraBase.addChild(camera);

        // Spawn some initial boxes
        for(int x = 0; x < 50; x++) {
            for(int z = 0; z < 50; z++) {
                // Randomize
                if(Math.random() > 0.05)
                    continue;

                // Spawn a box
                // TODO: Use box prefab!
                CubePrefab boxObject = new CubePrefab("Box");
                boxObject.getTransform().setPosition(new Vector3f(-25 + x, 0.5f, -25 + z));
                boxObject.setMaterial(boxMaterial);
                boxObject.addComponent(new BoxDecayAnimatorComponent((float) (Math.random() * 10.0f)));
                addGameObject(boxObject);
            }
        }

        // Create a cube spawner
        GameObject cubeSpawner = new GameObject("CubeSpawner");
        cubeSpawner.addComponent(new BoxSpawnerComponent(boxMaterial));
        addGameObject(cubeSpawner);
    }

    /**
     * Create the toggeable menu and add it to the scene
     */
    private void createMenu() {
        // Create the base menu panel
        GameObject menuPanel = new GameObject("MenuPanel");
        menuPanel.addComponent(new RectangleTransform(
                new Vector2f(350 / 2 + 48, -(165 / 2 + 48)),
                new Vector2f(350, 165),
                new RectangleTransformAnchor(0f, 1f, 0f, 1f)
        ));
        menuPanel.addComponent(new GuiPanelComponent());
        addGameObject(menuPanel);

        // Create the menu title
        GuiLabelPrefab menuLabel = new GuiLabelPrefab("MenuLabel", KeyBarricade.APP_NAME);
        menuLabel.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        menuLabel.getRectangleTransform().setPositionTop(-(20 + 16)); // TODO: Invert this when stretched?
        menuLabel.setColor(Color.WHITE);
        menuPanel.addChild(menuLabel);

        // Create a new game button
        GuiButtonPrefab button = new GuiButtonPrefab("NewGameButton", "New Game");
        button.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8))); // TODO: Invert this when stretched?
        menuPanel.addChild(button);

        // Create an exit button
        GuiButtonPrefab button2 = new GuiButtonPrefab("ExitButton", "Exit");
        button2.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button2.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 2)); // TODO: Invert this when stretched?
        menuPanel.addChild(button2);

        // Create the base version panel
        GameObject versionPanel = new GameObject("VersionPanel");
        versionPanel.addComponent(new RectangleTransform(
                new Vector2f(-(120 / 2 + 48), 24 / 2 + 48),
                new Vector2f(120, 24),
                new RectangleTransformAnchor(1f, 0f, 1f, 0f)
        ));
        addGameObject(versionPanel);

        // Create the version label
        GuiLabelPrefab versionLabel = new GuiLabelPrefab("VersionLabel", "Version " + KeyBarricade.APP_VERSION_NAME);
        versionLabel.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        versionLabel.getRectangleTransform().setPositionTop(-(24 / 2)); // TODO: Invert this when stretched?
        versionLabel.setColor(new Color(1, 1, 1, 0.25f));
        versionPanel.addChild(versionLabel);
    }
}
