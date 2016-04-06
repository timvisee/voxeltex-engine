package me.keybarricade.game.component.animator;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.global.Time;
import org.joml.Vector3f;

public class BoxSpawnAnimatorComponent extends BaseComponent {

    /**
     * The time offset used for animations.
     */
    private float timeOffset;

    /**
     * Target position of the cube.
     */
    private Vector3f targetPosition;

    /**
     * Mesh renderer component.
     */
    private MeshRendererComponent meshRenderer;

    private float waitUntil = Time.timeFloat;

    /**
     * True to add a kinematic rigidbody after the spawn animation.
     */
    private boolean applyPhysics = false;

    /**
     * Constructor.
     */
    public BoxSpawnAnimatorComponent() { }

    /**
     * Constructor.
     *
     * @param applyPhysics True to add a kinematic rigidbody after the spawn animation, false if not.
     */
    public BoxSpawnAnimatorComponent(float delay, boolean applyPhysics) {
        this.waitUntil += delay;
        this.applyPhysics = applyPhysics;
    }

    @Override
    public void create() {
        // Get the mesh renderer component
        this.meshRenderer = getComponent(MeshRendererComponent.class);
    }

    @Override
    public void start() {
        // Set the time offset
        this.timeOffset = this.waitUntil;

        // Store the cube's targetPosition position
        targetPosition = new Vector3f(getTransform().getPosition());

        // Place the cube in the air
        getTransform().getPosition().y = 6f;
    }

    @Override
    public void update() {
        // Do not start the animation if we still need to wait
        if(Time.timeFloat < waitUntil) {
            // Disable the mesh renderer, to ensure the blocks aren't visible already in the air
            if(this.meshRenderer != null)
                this.meshRenderer.setEnabled(false);

            // Return
            return;
        }

        // Enable the mesh renderer
        if(this.meshRenderer != null)
            this.meshRenderer.setEnabled(true);

        // Lerp the position
        getTransform().getPosition().lerp(this.targetPosition, (Time.timeFloat - timeOffset) / 4.0f);

        // Set the alpha intensity of the cube based on it's spawn time
        if(this.meshRenderer != null)
            this.meshRenderer.getColor().setAlpha(Math.min((Time.timeFloat - timeOffset) / 0.25f, 1));

        // Destroy the spawn animator after a second
        if(Time.timeFloat - timeOffset > 1f) {
            // Force-set the position of the box
            getTransform().setPosition(this.targetPosition);

            // Destroy the component
            getOwner().destroyComponent(this);

            // Add a rigidbody if specified
            if(this.applyPhysics)
                getOwner().addComponent(new RigidbodyComponent(true));
        }
    }

    @Override
    public void destroy() { }
}
