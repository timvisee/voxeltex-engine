package me.keybarricade.voxeltex.physics.collider;

import com.bulletphysics.collision.shapes.CollisionShape;
import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Vector3f;

public abstract class AbstractColliderComponent extends BaseComponent implements ColliderComponentInterface {

    /**
     * Collider offset in local space.
     */
    private Vector3f offset = Vector3fFactory.zero();

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() { }

    /**
     * Get the collider offset in local space.
     *
     * @return Collider offset.
     */
    public Vector3f getOffset() {
        return this.offset;
    }

    /**
     * Set the collider offset in local space.
     *
     * @param offset Collider offset.
     */
    public void setOffset(Vector3f offset) {
        this.offset.set(offset);
    }

    /**
     * Get the shape to use with the bullet physics engine.
     *
     * @return Bullet shape.
     */
    public abstract CollisionShape getBulletShape();
}