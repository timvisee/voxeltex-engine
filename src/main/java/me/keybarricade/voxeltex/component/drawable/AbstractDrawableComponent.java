package me.keybarricade.voxeltex.component.drawable;

import me.keybarricade.voxeltex.component.BaseComponent;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractDrawableComponent extends BaseComponent implements DrawableComponentInterface {

    /**
     * Float buffer for the matrix.
     */
    private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    public void drawStart() {
        // Save the current matrix state
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();

        Vector3f pos = getTransform().getPosition();

        Matrix4f matrix = new Matrix4f();

        matrix.translate(pos.x, pos.y, pos.z).rotate(getTransform().getRotation());

        Quaternionf rot = new Quaternionf(getTransform().getRotation());

        Vector3f euler = new Vector3f();
        rot.getEulerAnglesXYZ(euler);

        AxisAngle4f test = rot.normalize().get(new AxisAngle4f());

        glMatrixMode(GL_MODELVIEW);

        glRotatef(test.angle * 10.0f, test.x, test.y, test.z);

        // Set the matrix mode
        //glLoadMatrixf(matrix.get(fb));
    }

    public void drawEnd() {
        // Pop the last matrix
        glPopMatrix();
    }
}
