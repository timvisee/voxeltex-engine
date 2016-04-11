package com.timvisee.keybarricade.game.component.animator;

import com.timvisee.voxeltex.component.BaseComponent;
import com.timvisee.voxeltex.component.mesh.renderer.MeshRendererComponent;
import com.timvisee.voxeltex.component.rigidbody.RigidbodyComponent;
import com.timvisee.voxeltex.gameobject.Transform;
import com.timvisee.voxeltex.global.Time;
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
     * Flag whether this is the first decay run.
     */
    private boolean firstDecayRun = true;

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
        // Use the mesh renderer of a child object if available
        for(int i = 0, size = getOwner().getChildren().size(); i < size; i++)
            if((this.meshRenderer = getOwner().getChild(i).getComponent(MeshRendererComponent.class)) != null)
                break;

        // Get the attached mesh renderer component if available
        if(this.meshRenderer == null)
            this.meshRenderer = getComponent(MeshRendererComponent.class);
    }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Decay the object
        if(this.meshRenderer != null && Time.timeFloat >= this.decayAt) {
            // Get the proper transform object
            Transform transform = getTransform();
            if(this.meshRenderer != null)
                transform = this.meshRenderer.getTransform();

            // Do some stuff on the first decay run
            if(this.firstDecayRun) {
                // Store the initial scale
                this.initialScale = new Vector3f(transform.getScale());

                // Get and destroy the rigidbody component if available
                RigidbodyComponent rigidbodyComponent = getOwner().getComponent(RigidbodyComponent.class);
                if(rigidbodyComponent != null)
                    rigidbodyComponent.destroy();

                // Flip the first decay run flag
                this.firstDecayRun = false;
            }

            // Calculate the animation factor
            float factor = (1.0f - (Time.timeFloat - this.decayAt)) / 1.0f;

            // Animate the alpha channel
            this.meshRenderer.getColor().setAlpha(factor);

            // Animate the scale
            transform.getScale().set(
                    this.initialScale.x * factor,
                    this.initialScale.y * factor,
                    this.initialScale.z * factor
            );
        }

        // Destroy the game object after it's decayed
        if(Time.timeFloat >= this.decayAt + 1f)
            getOwner().destroy();
    }
}
