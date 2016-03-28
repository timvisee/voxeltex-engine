package me.keybarricade.voxeltex.physics.rigidbody;

import com.bulletphysics.dynamics.RigidBody;
import me.keybarricade.voxeltex.physics.collider.AbstractColliderComponent;

public interface RigidbodyComponentInterface {

    /**
     * Get the collider component that is attached to this rigidbody.
     *
     * @return Collider component or null.
     */
    AbstractColliderComponent getColliderComponent();

    /**
     * Get the Bullet physics engine rigidbody if created.
     *
     * @return Bullet physics engine rigidbody or null.
     */
    RigidBody getPhysicsRigidbody();

    /**
     * Check whether this game object rigidbody is kinematic.
     *
     * @return True if kinematic, false if not.
     */
    boolean isKinematic();

    /**
     * Set whether this game object rigidbody is kinematic.
     *
     * @param kinematic True if kinematic, false if not.
     */
    void setKinematic(boolean kinematic);
}
