package me.keybarricade;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
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

    @Override
    public void create() {
        // Get the mesh renderer component
        this.meshRenderer = getComponent(MeshRendererComponent.class);
    }

    @Override
    public void start() {
        // Set the time offset
        this.timeOffset = Time.timeFloat;

        // Store the cube's targetPosition position
        targetPosition = new Vector3f(getTransform().getPosition());

        // Place the cube in the air
        getTransform().getPosition().y = 6f;
    }

    @Override
    public void update() {
        // Lerp the position
        getTransform().getPosition().lerp(this.targetPosition, (Time.timeFloat - timeOffset) / 4.0f);

        // Set the alpha intensity of the cube based on it's spawn time
        this.meshRenderer.getColor().setAlpha(Math.min((Time.timeFloat - timeOffset) / 0.5f, 1));

        // Destroy the spawn animator after a second
        if(Time.timeFloat - timeOffset > 1f) {
            // Force-set the position of the box
            getTransform().setPosition(this.targetPosition);

            // Destroy the component
            getOwner().destroyComponent(this);
        }
    }

    @Override
    public void destroy() { }
}
