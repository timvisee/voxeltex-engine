package me.keybarricade.game.component.animator;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.global.Time;
import org.joml.Vector3f;

public class BoxDecayAnimatorComponent extends BaseComponent {

    /**
     * Time to decay the box at.
     */
    private float decayAt;

    /**
     * Mesh renderer component.
     */
    private MeshRendererComponent meshRenderer;

    /**
     * Initial scale. Used as reference for the scaling animation.
     */
    private Vector3f initialScale;

    /**
     * Constructor.
     *
     * @param delay Decay delay.
     */
    public BoxDecayAnimatorComponent(float delay) {
        this.decayAt = Time.timeFloat + delay;
    }

    @Override
    public void create() {
        // Get the mesh renderer component
        this.meshRenderer = getComponent(MeshRendererComponent.class);
    }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Decay the cube
        if(this.meshRenderer != null && Time.timeFloat >= this.decayAt) {
            // Store the initial scale if it hasn't been stored yet
            if(this.initialScale == null)
                this.initialScale = new Vector3f(getTransform().getScale());

            // Calculate the animation factor
            float factor = (1.0f - (Time.timeFloat - this.decayAt)) / 1.0f;

            // Animate the alpha channel
            this.meshRenderer.getColor().setAlpha(factor);

            // Animate the scale
            getTransform().getScale().set(
                    this.initialScale.x * factor,
                    this.initialScale.y * factor,
                    this.initialScale.z * factor
            );
        }

        // Destroy the game object after it's decayed
        if(Time.timeFloat >= this.decayAt + 1f)
            getScene().destroyGameObject(getOwner());
    }

    @Override
    public void destroy() { }
}
