package me.keybarricade.voxeltex.physics.rigidbody;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import me.keybarricade.voxeltex.math.matrix.Matrix4fUtil;
import me.keybarricade.voxeltex.physics.ScenePhysicsEngine;
import me.keybarricade.voxeltex.physics.collider.AbstractColliderComponent;
import org.joml.Quaternionf;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class RigidbodyComponent extends AbstractRigidbodyComponent {

    /**
     * Rigid body used with the Bullet physics engine.
     */
    private RigidBody physicsRigidbody = null;

    /**
     * The collider component that is used for this rigidbody.
     */
    private AbstractColliderComponent colliderComponent = null;

    @Override
    public void create() { }

    @Override
    public void start() {
        // Create the physics object
        createPhysicsObject();
    }

    @Override
    public void update() {
        // Update the transform of the game object according to the physics object if available
        if(this.physicsRigidbody != null) {
            // TODO: Update the game object transform

            // TODO: Use caching!
            Transform transform = new Transform();
            this.physicsRigidbody.getWorldTransform(transform);
            org.joml.Matrix4f matrix = Matrix4fUtil.toJoml(transform.getMatrix(new Matrix4f()), new org.joml.Matrix4f());
            getTransform().setPosition(matrix.getTranslation(new org.joml.Vector3f()));
            getTransform().setRotation(matrix.getUnnormalizedRotation(new Quaternionf()));
            getTransform().setScale(matrix.getScale(new org.joml.Vector3f()));
        }
    }

    private void createPhysicsObject() {
        // Make sure a collider component is attached
        if(this.colliderComponent == null) {
            // Find a suitable collider component
            this.colliderComponent = getComponent(AbstractColliderComponent.class);

            // Show a warning if no collision component was found
            if(this.colliderComponent == null) {
                System.out.println("Game object '" + getOwner().getName() + "' doesn't have a suitable collider component to use with the rigidbody");
                return;
            }
        }

        // Get the physics engine
        ScenePhysicsEngine physicsEngine = getScene().getPhysics();

        // Get the collision shape
        CollisionShape collisionShape = this.colliderComponent.getBulletShape();

        // Get the transform object for the collision shape
        // TODO: Use proper caching here!
        Transform physicsTransform = new Transform(Matrix4fUtil.toVecmath(getTransform().getWorldMatrix(), new Matrix4f()));

        // Create the motion state for the game object
        MotionState ballMotionState = new DefaultMotionState(physicsTransform);

        // Calculate and define the inertia
        // TODO: Use proper inertia here!
        Vector3f ballInertia = new Vector3f(0, 0, 0);
        collisionShape.calculateLocalInertia(2.5f, ballInertia);

        // Create and configure the rigidbody construction info
        RigidBodyConstructionInfo ballConstructionInfo = new RigidBodyConstructionInfo(2.5f, ballMotionState, collisionShape, ballInertia);
        // TODO: Use proper restitution and angular damping here!
        ballConstructionInfo.restitution = 0.5f;
        ballConstructionInfo.angularDamping = 0.05f;

        // Create the rigidbody for the game object
        this.physicsRigidbody = new RigidBody(ballConstructionInfo);
        // TODO: Determine what the activation state should be!
        //this.physicsRigidbody.setActivationState(CollisionObject.DISABLE_DEACTIVATION);

        // Add the rigidbody to the physics engine world
        physicsEngine.addRigidbody(this.physicsRigidbody);
    }
}
