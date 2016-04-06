package me.keybarricade;

import me.keybarricade.gameobject.KeyPickupPrefab;
import me.keybarricade.gameobject.SandSurfacePrefab;
import me.keybarricade.voxeltex.component.collider.primitive.SphereColliderComponent;
import me.keybarricade.voxeltex.component.follow.SmoothTopDownFollowComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.movement.WasdPhysicsMovementComponent;
import me.keybarricade.voxeltex.component.overlay.gui.GuiPanelComponent;
import me.keybarricade.voxeltex.component.overlay.gui.menu.ToggleableMenuComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.RectangleTransformAnchor;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.prefab.camera.MouseLookCameraPrefab;
import me.keybarricade.voxeltex.prefab.gui.GuiButtonPrefab;
import me.keybarricade.voxeltex.prefab.gui.GuiLabelPrefab;
import me.keybarricade.voxeltex.prefab.light.LightPrefab;
import me.keybarricade.voxeltex.scene.Scene;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class GameScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Create the menu
        createMenu();

        // Create and add the sand surface prefab
        addGameObject(new SandSurfacePrefab());

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDDC5C).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Load the box texture and material
        Texture boxTexture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        Material boxMaterial = new Material(boxTexture);

        // Load the sphere mesh
        Mesh sphereMesh = new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/sphere.obj"));

        // Create walls
        for(int x = 0; x < 12; x++) {
            for(int z = 0; z < 12; z++) {
                // Only create walls on the edges
                if(x != 0 && z != 0 && x != 11 && z != 11)
                    continue;

                // Spawn a box
                BoxPrefab box = new BoxPrefab(new Vector3f(-5 + x, 0.5f, -5 + z), false, -1f, boxMaterial);
                addGameObject(box);
            }
        }

        // Spawn some boxes
        addGameObject(new BoxPrefab(new Vector3f(3, 0.5f, 2), false, -1f, boxMaterial));
        addGameObject(new BoxPrefab(new Vector3f(1, 0.5f, -4), false, -1f, boxMaterial));
        addGameObject(new BoxPrefab(new Vector3f(2, 0.5f, 0), false, -1f, boxMaterial));
        addGameObject(new BoxPrefab(new Vector3f(-2, 0.5f, 3), false, -1f, boxMaterial));

        // Add a key
        KeyPickupPrefab keyObject = new KeyPickupPrefab();
        keyObject.getTransform().getPosition().set(-2, 0, -1);
        addGameObject(keyObject);

        // Player
        GameObject playerObject = new GameObject("Player");
        playerObject.addComponent(new MeshFilterComponent(sphereMesh));
        playerObject.addComponent(new MeshRendererComponent(new Material(Texture.fromColor(Color.BLUE, 1, 1))));
        playerObject.getTransform().setPosition(new Vector3f(0, 0.5f, 0));
        playerObject.getTransform().setScale(0.3f, 0.3f, 0.3f);
        playerObject.addComponent(new WasdPhysicsMovementComponent());
        playerObject.addComponent(new SphereColliderComponent(0.3f));
        playerObject.addComponent(new RigidbodyComponent(false));
        addGameObject(playerObject);

        // Create a camera and follow the player
        MouseLookCameraPrefab cameraPrefab = new MouseLookCameraPrefab();
        cameraPrefab.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
        cameraPrefab.addComponent(new SmoothTopDownFollowComponent(playerObject));
        addGameObject(cameraPrefab);
//        FpsCameraPrefab camera = new FpsCameraPrefab();
//        addGameObject(camera);
    }

    /**
     * Create the toggeable menu and add it to the scene
     */
    private void createMenu() {
        // Create the base menu panel
        GameObject menuPanel = new GameObject("OverlayTest");
        menuPanel.addComponent(new RectangleTransform(
                new Vector2f(0, 0),
                new Vector2f(350, 215),
                new RectangleTransformAnchor(0.5f, 0.6f, 0.5f, 0.6f)
        ));
        menuPanel.addComponent(new GuiPanelComponent());
        addGameObject(menuPanel);

        // Create the menu label
        GuiLabelPrefab menuLabel = new GuiLabelPrefab("Button", "Menu");
        menuLabel.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        menuLabel.getRectangleTransform().setPositionTop(-(20 + 16)); // TODO: Invert this when stretched?
        menuLabel.setColor(Color.WHITE);
        menuPanel.addChild(menuLabel);

        // Create a restart button
        GuiButtonPrefab restartButton = new GuiButtonPrefab("RestartButton", "Restart");
        restartButton.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        restartButton.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8))); // TODO: Invert this when stretched?
        menuPanel.addChild(restartButton);

        // Create a main menu button
        GuiButtonPrefab mainMenuButton = new GuiButtonPrefab("MainMenuButton", "Main Menu") {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Load the main menu
                getEngine().getSceneManager().loadScene(new MainMenuScene());
            }
        };
        mainMenuButton.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        mainMenuButton.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 2)); // TODO: Invert this when stretched?
        menuPanel.addChild(mainMenuButton);

        // Create an exit button
        GuiButtonPrefab exitButton = new GuiButtonPrefab("ExitButton", "Exit") {
            @Override
            public void onClick() {
                // Call the super
                super.onClick();

                // Exit
                System.out.println("Exit button pressed.");
                System.exit(0);
            }
        };
        exitButton.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        exitButton.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 3)); // TODO: Invert this when stretched?
        menuPanel.addChild(exitButton);

        // Create a toggleable menu controller
        GameObject menuController = new GameObject("MenuController");
        menuController.addComponent(new ToggleableMenuComponent(menuPanel));
        addGameObject(menuController);
    }
}
