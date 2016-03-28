package me.keybarricade.voxeltex.component.collider.primitive;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import me.keybarricade.voxeltex.component.collider.AbstractColliderComponent;

public class SphereColliderComponent extends AbstractColliderComponent {

    /**
     * Radius of the sphere collider.
     */
    private float radius = 1.0f;

    /**
     * The bullet physics engine shape representation.
     */
    private SphereShape bulletShape = null;

    /**
     * Get the radius of the sphere collider.
     *
     * @return Sphere radius.
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of the sphere collider.
     *
     * @param radius Sphere radius.
     */
    public void setRadius(float radius) {
        // TODO: Apply this to the sphere
        this.radius = radius;
    }

    @Override
    public CollisionShape getBulletShape() {
        // Make sure the shape has been configured
        if(this.bulletShape == null)
            this.bulletShape = new SphereShape(this.radius);

        // Return the shape
        return this.bulletShape;
    }
}
