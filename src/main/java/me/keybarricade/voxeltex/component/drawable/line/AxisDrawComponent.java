package me.keybarricade.voxeltex.component.drawable.line;

import me.keybarricade.voxeltex.component.drawable.AbstractDrawableComponent;

import static org.lwjgl.opengl.GL11.*;

public class AxisDrawComponent extends AbstractDrawableComponent {

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public synchronized void update() { }

    @Override
    public void draw() {
        // Set the thickness of the axis drawn
        // TODO: Make this configurable
        glLineWidth(5.0f);

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
