package me.keybarricade.voxeltex.component.drawable.line;

import me.keybarricade.voxeltex.component.drawable.AbstractDrawableComponent;
import org.lwjgl.opengl.GL11;

public class AxisDrawComponent extends AbstractDrawableComponent {

    /**
     * Width of the axis lines.
     */
    private float lineWidth = 5.0f;

    /**
     * Constructor.
     */
    public AxisDrawComponent() { }

    /**
     * Constructor.
     *
     * @param lineWidth Line width.
     */
    public AxisDrawComponent(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public synchronized void update() { }

    @Override
    public void destroy() { }

    @Override
    public synchronized void draw() {
        // Set the thickness of the axis drawn
        GL11.glLineWidth(this.lineWidth);

        // Enable line drawing mode
        GL11.glBegin(GL11.GL_LINES);

        // Green for X
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 0.0f, 0.0f);

        // Green for Y
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 1.0f, 0.0f);

        // Green for Z
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 1.0f);

        // Finish drawing
        GL11.glEnd();
    }

    /**
     * Get the line width of the axis lines.
     *
     * @return Line width.
     */
    public float getLineWidth() {
        return this.lineWidth;
    }

    /**
     * Set the line width of the axis lines.
     *
     * @param lineWidth Line width.
     */
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
}
