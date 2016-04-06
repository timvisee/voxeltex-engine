package me.keybarricade;

import me.keybarricade.gameobject.KeyPickupPrefab;
import me.keybarricade.gameobject.SandSurfacePrefab;
import me.keybarricade.voxeltex.component.collider.primitive.SphereColliderComponent;
import me.keybarricade.voxeltex.component.drawable.line.AxisDrawComponent;
import me.keybarricade.voxeltex.component.follow.SmoothTopDownFollowComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.movement.WasdMovementComponent;
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
import me.keybarricade.voxeltex.prefab.camera.FpsCameraPrefab;
import me.keybarricade.voxeltex.prefab.camera.MouseLookCameraPrefab;
import me.keybarricade.voxeltex.prefab.gui.GuiButtonPrefab;
import me.keybarricade.voxeltex.prefab.gui.GuiLabelPrefab;
import me.keybarricade.voxeltex.prefab.light.LightPrefab;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
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

        // Create an object to render the center axis and grid
        GameObject gridObject = new GameObject("AxisGridRenderer");
        //gridObject.addComponent(new GridDrawComponent());
        gridObject.addComponent(new AxisDrawComponent());
        addGameObject(gridObject);

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

                CubePrefab boxObject = new CubePrefab("Box");
                boxObject.getTransform().setPosition(new Vector3f(-5 + x, 0.5f, -5 + z));
                boxObject.setMaterial(boxMaterial);
                boxObject.addComponent(new RigidbodyComponent(true));
                addGameObject(boxObject);
            }
        }

        // Spawn some boxes
        CubePrefab obj = new CubePrefab("Box");
        obj.getTransform().setPosition(new Vector3f(3, 0.5f, 2));
        obj.setMaterial(boxMaterial);
        addGameObject(obj);

        obj = new CubePrefab("Box");
        obj.getTransform().setPosition(new Vector3f(1, 0.5f, -4));
        obj.setMaterial(boxMaterial);
        addGameObject(obj);

        obj = new CubePrefab("Box");
        obj.getTransform().setPosition(new Vector3f(2, 0.5f, 0));
        obj.setMaterial(boxMaterial);
        addGameObject(obj);

        obj = new CubePrefab("Box");
        obj.getTransform().setPosition(new Vector3f(-2, 0.5f, 3));
        obj.setMaterial(boxMaterial);
        addGameObject(obj);

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
        playerObject.addComponent(new WasdMovementComponent());
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
                new Vector2f(350, 165),
                new RectangleTransformAnchor(0.5f, 0.6f, 0.5f, 0.6f)
        ));
        menuPanel.addComponent(new GuiPanelComponent());
        addGameObject(menuPanel);

        // Create the menu title
        GuiLabelPrefab menuTitle = new GuiLabelPrefab("Button", "Menu");
        menuTitle.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        menuTitle.getRectangleTransform().setPositionTop(-(20 + 16)); // TODO: Invert this when stretched?
        menuTitle.setColor(Color.WHITE);
        menuPanel.addChild(menuTitle);

        // Create a new game button
        GuiButtonPrefab button = new GuiButtonPrefab("Button", "New Game");
        button.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8))); // TODO: Invert this when stretched?
        menuPanel.addChild(button);

        // Create an exit button
        GuiButtonPrefab button2 = new GuiButtonPrefab("Button", "Exit");
        button2.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button2.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 2)); // TODO: Invert this when stretched?
        menuPanel.addChild(button2);

        // Create a toggleable menu controller
        GameObject menuController = new GameObject("MenuController");
        menuController.addComponent(new ToggleableMenuComponent(menuPanel));
        addGameObject(menuController);
    }
}
