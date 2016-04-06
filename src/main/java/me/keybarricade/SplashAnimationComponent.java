package me.keybarricade;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.component.overlay.gui.GuiImageComponent;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.scene.TestEnvironmentScene;

import static org.lwjgl.opengl.GL11.glClearColor;

public class SplashAnimationComponent extends BaseComponent {

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Set the clear color of this scene
        // TODO: Configure this in the renderer class!
        glClearColor(0, 0, 0, 1.0f);

        // Calculate the local time value
        double x = Time.time - 0.5;

        // Define the size and alpha variable used for calculations
        double size, alpha = 0;

        // Calculate the size of the splash
        size = (Math.pow(-x * 0.48 + 0.8, 3) + 1.2 - x * 0.1) * 256.0;

        // Calculate the alpha intensity of the splash
        if(Time.time < 1.25)
            alpha = Math.pow((x - 0.75) * 1.35, 2.0) * -1.0 + 1.0;
        else if(Time.time < 2.75)
            alpha = 1f;
        else if(Time.time < 3.5)
            alpha = Math.pow((x - 2.25) * 1.35, 2.0) * -1.0 + 1.0;

        // Send the values to the target components
        // TODO: Buffer the components to maximize performance
        getComponent(RectangleTransform.class).setSizeX((float) size);
        getComponent(RectangleTransform.class).setSizeY((float) size);
        getComponent(GuiImageComponent.class).setAlpha((float) alpha);

        // Load the test environment scene after  the splash screen is done
        if(Time.time > 3.5)
            getEngine().getSceneManager().loadScene(new TestEnvironmentScene());
    }

    @Override
    public void destroy() { }
}
