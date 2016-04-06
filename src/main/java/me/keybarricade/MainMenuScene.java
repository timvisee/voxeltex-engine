package me.keybarricade;

import me.keybarricade.voxeltex.component.overlay.gui.GuiPanelComponent;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.RectangleTransformAnchor;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.prefab.gui.GuiButtonPrefab;
import me.keybarricade.voxeltex.prefab.gui.GuiLabelPrefab;
import me.keybarricade.voxeltex.scene.Scene;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;

public class MainMenuScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Create the menu
        createMenu();
    }

    /**
     * Create the toggeable menu and add it to the scene
     */
    private void createMenu() {
        // Create the base menu panel
        GameObject menuPanel = new GameObject("OverlayTest");
        menuPanel.addComponent(new RectangleTransform(
                new Vector2f(350 / 2 + 32, -(165 / 2 + 32)),
                new Vector2f(350, 165),
                new RectangleTransformAnchor(0f, 1f, 0f, 1f)
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
    }
}
