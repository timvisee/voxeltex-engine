package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.component.BaseComponent;
import org.joml.Matrix4f;

public abstract class AbstractCameraComponent extends BaseComponent {

    @Override
    public void create() { }

    @Override
    public synchronized void update() { }

    @Override
    public void destroy() {
        // TODO: Remove the camera from the MainCamera global if used.
    }

    /**
     * Update the camera position.
     */
    public abstract void updateCamera();

    /**
     * Apply camera transformation to the given matrix.
     *
     * @param m Matrix to apply.
     *
     * @return Matrix.
     */
    public abstract Matrix4f apply(Matrix4f m);

    /**
     * Apply the camera transformation to a matrix.
     *
     * @return Matrix.
     */
    public abstract Matrix4f apply();

    /**
     * Apply the camera transformation to the model view matrix.
     */
    public abstract void applyViewMatrix();
}
