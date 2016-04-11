package me.keybarricade;

import com.timvisee.voxeltex.VoxelTexEngine;
import com.timvisee.voxeltex.scene.DeveloperSplashScene;
import com.timvisee.voxeltex.swing.ProgressDialog;
import com.timvisee.voxeltex.swing.SwingUtils;
import me.keybarricade.game.asset.GameResourceBundle;

public class Game {

    /**
     * VoxelTex engine instance.
     */
    private VoxelTexEngine engine;

    /**
     * Progress dialog, used to show status outside of the engine window.
     */
    private ProgressDialog progressDialog;

    /**
     * Constructor.
     */
    public Game() { }

    /**
     * Initialize.
     */
    public void init() {
        // Use the native look and feel for Swing windows when possible
        SwingUtils.useNativeLookAndFeel();

        // Create and show the progress dialog
        this.progressDialog = new ProgressDialog(null, "VoxelTex Engine", false);
        this.progressDialog.setVisible(true);

        // Show initialization message
        System.out.println("Initializing " + KeyBarricade.APP_NAME + "...");

        // Initialize the VoxelTex engine
        initEngine();

        // Start the VoxelTex engine
        startEngine();

        // Exit the game
        exit();
    }

    /**
     * Initialize the VoxelTex engine.
     */
    private void initEngine() {
        // Show status
        this.progressDialog.setStatus("Initializing VoxelTex engine...");

        // Create a VoxelTex engine instance
        this.engine = new VoxelTexEngine();

        // Set the title
        this.engine.setTitle(KeyBarricade.APP_NAME + " v" + KeyBarricade.APP_VERSION_NAME);

        // Initialize the engine
        this.engine.init();
    }

    /**
     * Start the VoxelTex engine after it has been initialized.
     */
    private void startEngine() {
        // Load the resource bundle
        this.progressDialog.setStatus("Loading game resources...");
        GameResourceBundle.getInstance().load();

        // Load the default scene
        this.progressDialog.setStatus("Loading scene...");
        this.engine.getSceneManager().loadScene(new DeveloperSplashScene());

        // Done, hide the progress dialog before starting the engine
        this.progressDialog.setVisible(false);

        // Start and run the engine
        this.engine.loop();
    }

    /**
     * Stop and exit the game.
     */
    private void exit() {
        // Exiting, show a status message
        System.out.println("Quitting " + KeyBarricade.APP_NAME + "...");

        // Dispose the game resources
        System.out.println("Disposing game resources...");
        // TODO: GameResourceBundle.getInstance().dispose();

        // Dispose the progress frame to ensure we're quitting properly
        this.progressDialog.dispose();

        // The game has quit, show a status message and force quit
        System.out.println(KeyBarricade.APP_NAME + " has quit");
        System.exit(0);
    }
}
