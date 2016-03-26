package me.keybarricade.voxeltex.physics.collider.primitive;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.mesh.generator.QuadMeshGenerator;
import me.keybarricade.voxeltex.physics.collider.AbstractColliderComponent;
import org.joml.Vector2f;

import javax.vecmath.Vector3f;

public class QuadColliderComponent extends AbstractColliderComponent {

    /**
     * Box collider size.
     */
    private Vector2f size = Vector2fFactory.one();

    /**
     * The orientation of the quad.
     */
    private int orientation = QuadMeshGenerator.DEFAULT_ORIENTATION;

    /**
     * Thickness of the quad.
     */
    private float thickness = 0.01f;

    /**
     * Bullet physics engine shape representation.
     */
    BoxShape bulletShape = null;

    /**
     * Get the size of the collider.
     *
     * @return Collider size.
     */
    public Vector2f getSize() {
        return this.size;
    }

    /**
     * Set the size of the collider.
     *
     * @param size Collider size.
     */
    public void setSize(Vector2f size) {
        this.size.set(size);
    }

    /**
     * Get the quad orientation.
     *
     * @return Quad orientation.
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * Set the quad orientation.
     *
     * @param orientation Quad orientation.
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /**
     * Get the thickness of the quad.
     *
     * @return Quad thickness.
     */
    public float getThickness() {
        return thickness;
    }

    /**
     * Quad thickness.
     *
     * @param thickness Quad thickness.
     */
    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    @Override
    public CollisionShape getBulletShape() {
        // Make sure the shape is configured
        if(this.bulletShape == null) {
            // Determine the size of the shape
            Vector3f shapeSize = new Vector3f();
            switch(this.orientation) {
                case QuadMeshGenerator.ORIENTATION_X_POSITIVE:
                case QuadMeshGenerator.ORIENTATION_X_NEGATIVE:
                    shapeSize.x = this.thickness / 2.0f;
                    shapeSize.y = this.size.x / 2.0f;
                    shapeSize.z = this.size.y / 2.0f;
                    break;

                case QuadMeshGenerator.ORIENTATION_Y_POSITIVE:
                case QuadMeshGenerator.ORIENTATION_Y_NEGATIVE:
                    shapeSize.x = this.size.x / 2.0f;
                    shapeSize.y = this.thickness / 2.0f;
                    shapeSize.z = this.size.y / 2.0f;
                    break;

                case QuadMeshGenerator.ORIENTATION_Z_POSITIVE:
                case QuadMeshGenerator.ORIENTATION_Z_NEGATIVE:
                    shapeSize.x = this.size.x / 2.0f;
                    shapeSize.y = this.size.y / 2.0f;
                    shapeSize.z = this.thickness / 2.0f;
                    break;
            }

            // Construct the shape
            this.bulletShape = new BoxShape(shapeSize);
        }

        // Return the shape
        return this.bulletShape;
    }
}
