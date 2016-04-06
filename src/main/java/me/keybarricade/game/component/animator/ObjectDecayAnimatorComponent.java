package me.keybarricade.game.component.animator;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.global.Time;
import org.joml.Vector3f;

public class ObjectDecayAnimatorComponent extends BaseComponent {

    /**
     * Time to decay the object at.
     */
    private float decayAt;

    /**
     * Attached mesh renderer component if available.
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
    public ObjectDecayAnimatorComponent(float delay) {
        this.decayAt = Time.timeFloat + delay;
    }

    @Override
    public void create() {
        // Get the attached mesh renderer component if available
        this.meshRenderer = getComponent(MeshRendererComponent.class);
    }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Decay the object
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
