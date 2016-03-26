package me.keybarricade.voxeltex.physics.collider.primitive;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.physics.collider.AbstractColliderComponent;
import org.joml.Vector3f;

public class BoxColliderComponent extends AbstractColliderComponent {

    /**
     * Box collider size.
     */
    private Vector3f size = Vector3fFactory.one();

    /**
     * Bullet physics engine shape representation.
     */
    private BoxShape bulletShape = null;

    /**
     * Constructor.
     *
     * @param size Box collider size.
     */
    public BoxColliderComponent(Vector3f size) {
        this.size.set(size);
    }

    /**
     * Get the size of the collider.
     *
     * @return Collider size.
     */
    public Vector3f getSize() {
        return this.size;
    }

    /**
     * Set the size of the collider.
     *
     * @param size Collider size.
     */
    public void setSize(Vector3f size) {
        this.size.set(size);
    }

    @Override
    public CollisionShape getBulletShape() {
        // Make sure the bullet shape has been configured
        if(this.bulletShape == null)
            this.bulletShape = new BoxShape(
                    new javax.vecmath.Vector3f(
                            size.x / 2.0f,
                            size.y / 2.0f,
                            size.y / 2.0f
                    )
            );

        // Return the shape
        return this.bulletShape;
    }
}
