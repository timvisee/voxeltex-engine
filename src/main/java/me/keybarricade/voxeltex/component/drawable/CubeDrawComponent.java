package me.keybarricade.voxeltex.component.drawable;

import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class CubeDrawComponent extends AbstractDrawableComponent {

    /**
     * The size of the cube on all three axis.
     */
    private Vector3f size = Vector3fFactory.one();

    /**
     * The center of the cube.
     */
    private Vector3f center = Vector3fFactory.zero();

    @Override
    public void start() { }

    @Override
    public void update() { }

    @Override
    public void draw() {
        // Calculate half of the sizes
        float xHalf = size.x / 2f;
        float yHalf = size.y / 2f;
        float zHalf = size.z / 2f;

        // Enter quad drawing mode
        glBegin(GL_QUADS);

        // Set the color and draw the quad
        glColor3f(0.0f, 0.0f, 0.2f );
        glVertex3f(center.x + xHalf, center.y - yHalf, center.z - zHalf);
        glVertex3f(center.x + xHalf, center.y + yHalf, center.z - zHalf);
        glVertex3f(center.x - xHalf, center.y + yHalf, center.z - zHalf);
        glVertex3f(center.x - xHalf, center.y - yHalf, center.z - zHalf);

        // Set the color and draw the quad
        glColor3f(0.0f, 0.0f, 1.0f );
        glVertex3f(center.x + xHalf, center.y - yHalf, center.z + zHalf);
        glVertex3f(center.x + xHalf, center.y + yHalf, center.z + zHalf);
        glVertex3f(center.x - xHalf, center.y + yHalf, center.z + zHalf);
        glVertex3f(center.x - xHalf, center.y - yHalf, center.z + zHalf);

        // Set the color and draw the quad
        glColor3f(1.0f, 0.0f, 0.0f );
        glVertex3f(center.x + xHalf, center.y - yHalf, center.z - zHalf);
        glVertex3f(center.x + xHalf, center.y + yHalf, center.z - zHalf);
        glVertex3f(center.x + xHalf, center.y + yHalf, center.z + zHalf);
        glVertex3f(center.x + xHalf, center.y - yHalf, center.z + zHalf);

        // Set the color and draw the quad
        glColor3f(0.2f, 0.0f, 0.0f );
        glVertex3f(center.x - xHalf, center.y - yHalf, center.z + zHalf);
        glVertex3f(center.x - xHalf, center.y + yHalf, center.z + zHalf);
        glVertex3f(center.x - xHalf, center.y + yHalf, center.z - zHalf);
        glVertex3f(center.x - xHalf, center.y - yHalf, center.z - zHalf);

        // Set the color and draw the quad
        glColor3f(0.0f, 1.0f, 0.0f );
        glVertex3f(center.x + xHalf, center.y + yHalf, center.z + zHalf);
        glVertex3f(center.x + xHalf, center.y + yHalf, center.z - zHalf);
        glVertex3f(center.x - xHalf, center.y + yHalf, center.z - zHalf);
        glVertex3f(center.x - xHalf, center.y + yHalf, center.z + zHalf);

        // Set the color and draw the quad
        glColor3f(0.0f, 0.2f, 0.0f );
        glVertex3f(center.x + xHalf, center.y - yHalf, center.z - zHalf);
        glVertex3f(center.x + xHalf, center.y - yHalf, center.z + zHalf);
        glVertex3f(center.x - xHalf, center.y - yHalf, center.z + zHalf);
        glVertex3f(center.x - xHalf, center.y - yHalf, center.z - zHalf);

        // Finish drawing
        glEnd();
    }

    /**
     * Get the size of the cube.
     *
     * @return Cube size.
     */
    public Vector3f getSize() {
        return size;
    }

    /**
     * Set the size of the cube.
     *
     * @param size Cube size.
     */
    public void setSize(Vector3f size) {
        this.size = size;
    }

    /**
     * Get the center of the cube.
     *
     * @return Center.
     */
    public Vector3f getCenter() {
        return center;
    }

    /**
     * Set the center of the cube.
     *
     * @param center Center.
     */
    public void setCenter(Vector3f center) {
        this.center = center;
    }
}
