package me.keybarricade.voxeltex.component.drawable;

import me.keybarricade.voxeltex.global.MainCamera;
import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.renderer.VoxelTexRenderer;
import me.keybarricade.voxeltex.shader.Shader;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class TexturedQuadDrawComponent extends AbstractDrawableComponent {

    /**
     * The size of the cube on all three axis.
     */
    private Vector2f size = Vector2fFactory.one();

    /**
     * The center of the cube.
     */
    private Vector2f center = Vector2fFactory.zero();

    @Override
    public void start() { }

    @Override
    public void update() { }

    private Texture texture;
    private Shader shader;

    @Override
    public void draw() {
        // Calculate half of the sizes
        float xHalf = size.x / 2f;
        float yHalf = size.y / 2f;

        // Load a texture if it hasn't been loaded yet
        if(texture == null) {
            // Load and bind the shader
            shader = Shader.fromEngineAssets("shaders/texvert.vert", "shaders/texfrag.frag");
            shader.bind();

            // Activate the texture
            glActiveTexture(GL_TEXTURE0);

            // Load a test texture
            texture = Texture.fromImage(Image.loadFromEngineAssets("images/test3.png"));
        }

        // Bind the shader to OpenGL
        shader.bind();

        // Get the projection matrix
        Matrix4f mat = new Matrix4f(VoxelTexRenderer.mat);
        mat.scale(0.5f, 0.5f, 0.5f);

        // Get the view matrix
        Matrix4f viewMatrix = MainCamera.createRelativeCameraMatrix();
        getTransform().applyWorldTransform(viewMatrix);

        // Configure the shader
        shader.setUniformMatrix4f("pr_matrix", mat);
        shader.setUniformMatrix4f("ml_matrix", viewMatrix);
        shader.setUniform1f("tex", 0);

        // Bind the texture and shader to OpenGL
        texture.bind();

        // Enter quad drawing mode
        glBegin(GL_QUADS);

        // Draw the quad
        glColor3f(0.0f, 1.0f, 0.0f );
        glTexCoord2f(0, 0);
        glVertex3f(center.x + xHalf, 0, center.y + yHalf);
        glTexCoord2f(1, 0);
        glVertex3f(center.x + xHalf, 0, center.y - yHalf);
        glTexCoord2f(1, 1);
        glVertex3f(center.x - xHalf, 0, center.y - yHalf);
        glTexCoord2f(0, 1);
        glVertex3f(center.x - xHalf, 0, center.y + yHalf);

        // Finish drawing
        glEnd();

        Shader.unbind();
        Texture.unbind();
    }

    /**
     * Get the size of the quad.
     *
     * @return Quad size.
     */
    public Vector2f getSize() {
        return size;
    }

    /**
     * Set the size of the quad.
     *
     * @param size Quad size.
     */
    public void setSize(Vector2f size) {
        this.size = size;
    }

    /**
     * Get the center of the quad.
     *
     * @return Center.
     */
    public Vector2f getCenter() {
        return center;
    }

    /**
     * Set the center of the quad.
     *
     * @param center Center.
     */
    public void setCenter(Vector2f center) {
        this.center = center;
    }
}
