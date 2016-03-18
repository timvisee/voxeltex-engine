package me.keybarricade.voxeltex.component.drawable;

import static org.lwjgl.opengl.GL11.*;

public class AxisDrawComponent extends AbstractDrawableComponent {

    @Override
    public void start() { }

    @Override
    public void update() { }

    @Override
    public void draw() {
        // Enable line drawing mode
        glBegin(GL_LINES);

        // Green for X
        glColor3f(1.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(1.0f, 0.0f, 0.0f);

        // Green for Y
        glColor3f(0.0f, 1.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 1.0f, 0.0f);

        // Green for Z
        glColor3f(0.0f, 0.0f, 1.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 1.0f);

        // Finish drawing
        glEnd();
    }
}
