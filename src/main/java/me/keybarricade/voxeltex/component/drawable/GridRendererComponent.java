package me.keybarricade.voxeltex.component.drawable;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class GridRendererComponent extends AbstractDrawableComponent {

    @Override
    public void update() { }

    @Override
    public void draw() {
        // Call the super
        super.draw();

        // Enable line drawing mode
        glBegin(GL_LINES);

        // Set the grid color
        glColor3f(0.2f, 0.2f, 0.2f);

        // Draw the grid
        for(int i = -20; i <= 20; i++) {
            glVertex3f(-20.0f, 0.0f, i);
            glVertex3f(20.0f, 0.0f, i);
            glVertex3f(i, 0.0f, -20.0f);
            glVertex3f(i, 0.0f, 20.0f);
        }

        // Finish drawing
        glEnd();
    }
}
