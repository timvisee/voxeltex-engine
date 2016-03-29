package me.keybarricade.voxeltex.component.rigidbody;

import com.bulletphysics.collision.dispatch.CollisionFlags;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import me.keybarricade.voxeltex.component.collider.AbstractColliderComponent;
import me.keybarricade.voxeltex.math.matrix.Matrix4fUtil;
import me.keybarricade.voxeltex.physics.ScenePhysicsEngine;
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

    /**
     * Flag which defines whether to initialize the rigidbody in kinematic mode or not.
     */
    private boolean initKinematic = false;

    /**
     * Constructor.
     */
    public RigidbodyComponent() { }

    /**
     * Constructor.
     *
     * @param kinematic True if kinematic, false if not.
     */
    public RigidbodyComponent(boolean kinematic) {
        this.initKinematic = kinematic;
    }

    @Override
    public void create() { }

    @Override
    public void start() {
        // Create the physics object
        createPhysicsRigidbody();
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

    /**
     * Create the actual rigidbody for this component.
     * This should be called when the scene the game object is in is started.
     */
    private void createPhysicsRigidbody() {
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
        ScenePhysicsEngine physicsEngine = getScene().getPhysicsEngine();

        // Get the collision shape
        CollisionShape collisionShape = this.colliderComponent.getBulletShape();

        // Get the transform object for the collision shape
        // TODO: Use proper caching here!
        Transform physicsTransform = new Transform(Matrix4fUtil.toVecmath(getTransform().getWorldMatrix(), new Matrix4f()));

        // Create the motion state for the game object
        MotionState motionState = new DefaultMotionState(physicsTransform);

        // Calculate and define the inertia
        // TODO: Use proper inertia here!
        Vector3f inertia = new Vector3f(0, 0, 0);
        collisionShape.calculateLocalInertia(2.5f, inertia);

        // Create and configure the rigidbody construction info
        RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(2.5f, motionState, collisionShape, inertia);
        // TODO: Use proper restitution and angular damping here!
        constructionInfo.restitution = 0.5f;
        constructionInfo.angularDamping = 0.1f;

        // Create the rigidbody for the game object
        this.physicsRigidbody = new RigidBody(constructionInfo);
        // TODO: Determine what the activation state should be!
        this.physicsRigidbody.setActivationState(CollisionObject.DISABLE_DEACTIVATION);

        // Set whether the game rigidbody is kinematic or not
        setKinematic(this.initKinematic);

        // Add the rigidbody to the physics engine world
        physicsEngine.addRigidbody(this.physicsRigidbody);
    }

    @Override
    public AbstractColliderComponent getColliderComponent() {
        return this.colliderComponent;
    }

    @Override
    public RigidBody getPhysicsRigidbody() {
        return this.physicsRigidbody;
    }

    @Override
    public boolean isKinematic() {
        return this.physicsRigidbody.isKinematicObject();
    }

    @Override
    public void setKinematic(boolean kinematic) {
        setCollisionFlag(CollisionFlags.KINEMATIC_OBJECT, kinematic);
    }

    /**
     * Set whether the given collision flag is set or not.
     *
     * @param collisionFlag Collision flag to configure.
     * @param enabled True to enable the flag, false to disable.
     */
    private void setCollisionFlag(int collisionFlag, boolean enabled) {
        if(enabled)
            this.physicsRigidbody.setCollisionFlags(this.physicsRigidbody.getCollisionFlags() | collisionFlag);
        else
            this.physicsRigidbody.setCollisionFlags(this.physicsRigidbody.getCollisionFlags() & ~collisionFlag);
    }
}
