package me.keybarricade.voxeltex.component.drawable.line;

import me.keybarricade.voxeltex.component.drawable.AbstractDrawableComponent;
import org.lwjgl.opengl.GL11;

public class GridDrawComponent extends AbstractDrawableComponent {

    @Override
    public void start() { }

    @Override
    public void update() { }

    @Override
    public void draw() {
        // Set the thickness of the axis drawn
        // TODO: Make this configurable
        GL11.glLineWidth(1.0f);

        // Enable line drawing mode
        GL11.glBegin(GL11.GL_LINES);

        // Set the grid color
        GL11.glColor3f(0.2f, 0.2f, 0.2f);

        // Draw the grid
        for(int i = -20; i <= 20; i++) {
            GL11.glVertex3f(-20.0f, 0.0f, i);
            GL11.glVertex3f(20.0f, 0.0f, i);
            GL11.glVertex3f(i, 0.0f, -20.0f);
            GL11.glVertex3f(i, 0.0f, 20.0f);
        }

        // Finish drawing
        GL11.glEnd();
    }
}
