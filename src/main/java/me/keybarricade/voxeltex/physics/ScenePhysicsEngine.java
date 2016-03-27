package me.keybarricade.voxeltex.physics;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.scene.AbstractScene;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class ScenePhysicsEngine {

    /**
     * The scene this physics engine is attached to.
     */
    private AbstractScene scene;

    /**
     * Bullet engine physics world instance.
     */
    private DynamicsWorld bulletDynamicsWorld;

    /**
     * Constructor.
     *
     * @param scene Scene.
     */
    public ScenePhysicsEngine(AbstractScene scene) {
        this.scene = scene;
    }

    /**
     * Get the scene this physics engine is attached to.
     *
     * @return Attached scene.
     */
    public AbstractScene getScene() {
        return this.scene;
    }

    public void setUp() {
        // Create a DBVT broadphase to broadly check collisions using the AABB technique which ensures good performance
        BroadphaseInterface broadphase = new DbvtBroadphase();

        // Set up a collision configuration and dispatcher
        CollisionConfiguration collisionConfig = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfig);

        // Configure a constraint solver
        ConstraintSolver solver = new SequentialImpulseConstraintSolver();

        // Create and configure the Bullet physics dynamic world
        this.bulletDynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfig);
        this.bulletDynamicsWorld.setGravity(new Vector3f(0, -9.81f, 0));

        // Create the ground and ball shapes
        // TODO: Remove this test
        CollisionShape groundShape = new StaticPlaneShape(new Vector3f(0, 1, 0), 0);

        // Initialize the motion state and rigidbody construction info for the ground
        MotionState groundMotionState = new DefaultMotionState(new Transform(new Matrix4f(
                new Quat4f(0, 0, 0, 1),
                new Vector3f(0, 0, 0),
                1.0f
        )));
        RigidBodyConstructionInfo groundBodyConstructionInfo = new RigidBodyConstructionInfo(
                0,
                groundMotionState,
                groundShape,
                new Vector3f(0, 0, 0)
        );

        // Configure the restitution of the ground
        groundBodyConstructionInfo.restitution = .25f;

        // Instantiate a rigidbody for the ground
        RigidBody groundRigidbody = new RigidBody(groundBodyConstructionInfo);
        this.bulletDynamicsWorld.addRigidBody(groundRigidbody);
    }

    /**
     * Update the physics engine state.
     * This will simulate a physics step, and will apply the physics to the game objects in the attached scene.
     */
    public void update() {
        // Simulate the next physics step
        bulletDynamicsWorld.stepSimulation(Time.deltaTimeFloat);

        // TODO: Apply physics to all game objects?
    }

    /**
     * Add a rigidbody to the physics world.
     *
     * @param rigidbody Rigidbody to add.
     */
    public void addRigidbody(RigidBody rigidbody) {
        this.bulletDynamicsWorld.addRigidBody(rigidbody);
    }
}
