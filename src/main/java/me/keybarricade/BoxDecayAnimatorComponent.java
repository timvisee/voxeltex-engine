package me.keybarricade;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.global.Time;

public class BoxDecayAnimatorComponent extends BaseComponent {

    /**
     * Time to decay the box at.
     */
    private float decayAt;

    /**
     * Mesh renderer component.
     */
    private MeshRendererComponent meshRenderer;

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
        if(this.meshRenderer != null && Time.timeFloat >= this.decayAt)
            this.meshRenderer.getColor().setAlpha((2.0f - (Time.timeFloat - this.decayAt)) / 2.0f);

        // Destroy the game object after it's decated
        if(Time.timeFloat >= this.decayAt + 2f)
            getScene().destroyGameObject(getOwner());
    }

    @Override
    public void destroy() { }
}
