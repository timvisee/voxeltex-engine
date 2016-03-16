package me.keybarricade.voxeltex.component.drawable;

import me.keybarricade.voxeltex.component.BaseComponent;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.glTranslatef;

public class DrawableComponent extends BaseComponent implements DrawableComponentInterface {
    @Override
    public void update() { }

    @Override
    public void draw() {
        // Set the drawing position to the world position of the component
        Vector3f worldPos = getOwner().getWorldPosition();
        glTranslatef(worldPos.x, worldPos.y, worldPos.z);

        // TODO: Set the drawing rotation
    }
}
