package me.keybarricade.voxeltex.component.drawable;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.camera.MainCamera;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractDrawableComponent extends BaseComponent implements DrawableComponentInterface {

    /**
     * Float buffer for the matrix.
     */
    private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    /**
     * Start the drawing of the component.
     */
    public void drawStart() {
        // Create a view matrix base based on the camera position
        Matrix4f viewMatrix = MainCamera.createCameraMatrix();

        // Apply the object's world transformation to the matrix
        getTransform().applyWorldTransform(viewMatrix);

        // Load the matrix to the GPU
        glLoadMatrixf(viewMatrix.get(fb));

//        // Alternative draw setup
//        Matrix4f viewMatrix = MainCamera.createCameraMatrix();
//        Vector3f test = getTransform().getWorldPosition();
//        viewMatrix.translate(-test.x, -test.y, -test.z).rotate(getTransform().getWorldRotation());
//        glLoadMatrixf(viewMatrix.get(fb));
    }

    /**
     * End the drawing of the component.
     */
    public void drawEnd() {
        // Pop the last matrix
        glPopMatrix();
    }
}
