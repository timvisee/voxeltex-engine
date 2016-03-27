package me.keybarricade.voxeltex.physics;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
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
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.scene.AbstractScene;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;

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

    // TODO: Remove this after testing!
    private RigidBody ballRigidbody;
    private GameObject ballObject;

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
        CollisionShape ballShape = new SphereShape(1);

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

        // Configure the ball transform
        Transform ballTransform = new Transform(new Matrix4f(
                new Quat4f(0, 0, 0, 1),
                new Vector3f(0, 10, 0),
                1.0f
        ));

        // Initialize the motion state and rigidbody construction info for the ball
        // TODO: Remove this test!
        MotionState ballMotionState = new DefaultMotionState(ballTransform);
        Vector3f ballInertia = new Vector3f(0, 0, 0);
        ballShape.calculateLocalInertia(2.5f, ballInertia);
        RigidBodyConstructionInfo ballConstructionInfo = new RigidBodyConstructionInfo(2.5f, ballMotionState, ballShape, ballInertia);

        // Set the restitution and angular damping of the ball
        ballConstructionInfo.restitution = 0.5f;
        ballConstructionInfo.angularDamping = 0.05f;

        // Instantiate the rigidbody for the ball
        ballRigidbody = new RigidBody(ballConstructionInfo);
        ballRigidbody.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
        this.bulletDynamicsWorld.addRigidBody(ballRigidbody);


        // Create a sphere object to simulate the falling ball
        // TODO: Remove this!
        GameObject sphere = new GameObject("Sphere");
        sphere.addComponent(new MeshFilterComponent(new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/sphere.obj"))));
        sphere.addComponent(new MeshRendererComponent(new Material(Texture.fromColor(Color.ORANGE, 1, 1))));
        //sphere.getTransform().getPosition().set(-4, 1f, 0);
        //sphere.getTransform().getAngularVelocity().set(0, 1, 0);
        ballObject = sphere;
        getScene().addGameObject(sphere);
    }

    /**
     * Update the physics engine state.
     * This will simulate a physics step, and will apply the physics to the game objects in the attached scene.
     */
    public void update() {
        // Simulate the next physics step
        bulletDynamicsWorld.stepSimulation(Time.deltaTimeFloat);

        // Get the sphere position
        Vector3f ballPosVec = ballRigidbody.getWorldTransform(new Transform()).origin;
        Quat4f rot = ballRigidbody.getWorldTransform(new Transform()).getRotation(new Quat4f());
        org.joml.Vector3f ballPos = new org.joml.Vector3f(ballPosVec.x, ballPosVec.y, ballPosVec.z);
        org.joml.Quaternionf ballRot = new org.joml.Quaternionf(rot.x, rot.y, rot.z, rot.w);

        // Update the position and rotation of the ball object to match the physics world
        ballObject.getTransform().setPosition(ballPos);
        ballObject.getTransform().setRotation(ballRot);
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
