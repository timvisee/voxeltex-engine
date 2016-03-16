package me.keybarricade.voxeltex.component.drawable;

import static org.lwjgl.opengl.GL11.*;

public class CubeRendererComponent extends AbstractDrawableComponent {

    @Override
    public void update() { }

    @Override
    public void draw() {
        // Call the super
        super.draw();

        // Enter quad drawing mode
        glBegin(GL_QUADS);

        // Set the color and draw the quad
        glColor3f(0.0f, 0.0f, 0.2f );
        glVertex3f( 0.5f, -0.5f, -0.5f );
        glVertex3f( 0.5f,  0.5f, -0.5f );
        glVertex3f(-0.5f,  0.5f, -0.5f );
        glVertex3f(-0.5f, -0.5f, -0.5f );

        // Set the color and draw the quad
        glColor3f(0.0f, 0.0f, 1.0f );
        glVertex3f( 0.5f, -0.5f,  0.5f );
        glVertex3f( 0.5f,  0.5f,  0.5f );
        glVertex3f(-0.5f,  0.5f,  0.5f );
        glVertex3f(-0.5f, -0.5f,  0.5f );

        // Set the color and draw the quad
        glColor3f(1.0f, 0.0f, 0.0f );
        glVertex3f( 0.5f, -0.5f, -0.5f );
        glVertex3f( 0.5f,  0.5f, -0.5f );
        glVertex3f( 0.5f,  0.5f,  0.5f );
        glVertex3f( 0.5f, -0.5f,  0.5f );

        // Set the color and draw the quad
        glColor3f(0.2f, 0.0f, 0.0f );
        glVertex3f(-0.5f, -0.5f,  0.5f );
        glVertex3f(-0.5f,  0.5f,  0.5f );
        glVertex3f(-0.5f,  0.5f, -0.5f );
        glVertex3f(-0.5f, -0.5f, -0.5f );

        // Set the color and draw the quad
        glColor3f(0.0f, 1.0f, 0.0f );
        glVertex3f( 0.5f,  0.5f,  0.5f );
        glVertex3f( 0.5f,  0.5f, -0.5f );
        glVertex3f(-0.5f,  0.5f, -0.5f );
        glVertex3f(-0.5f,  0.5f,  0.5f );

        // Set the color and draw the quad
        glColor3f(0.0f, 0.2f, 0.0f );
        glVertex3f( 0.5f, -0.5f, -0.5f );
        glVertex3f( 0.5f, -0.5f,  0.5f );
        glVertex3f(-0.5f, -0.5f,  0.5f );
        glVertex3f(-0.5f, -0.5f, -0.5f );

        // Finish drawing
        glEnd();
    }
}
