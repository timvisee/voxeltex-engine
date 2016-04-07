package me.keybarricade;

import me.keybarricade.game.asset.GameResourceBundle;
import me.keybarricade.voxeltex.VoxelTexEngine;
import me.keybarricade.voxeltex.scene.DeveloperSplashScene;
import me.keybarricade.voxeltex.swing.ProgressDialog;
import me.keybarricade.voxeltex.swing.SwingUtils;

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
        this.progressDialog = new ProgressDialog(null, "VoxelTex Engine");
        this.progressDialog.setVisible(true);

        // Show initialization message
        System.out.println("Initializing " + KeyBarricade.APP_NAME + "...");

        // Initialize the VoxelTex engine
        initEngine();

        // Start the VoxelTex engine
        startEngine();
    }

    /**
     * Initialize the VoxelTex engine.
     */
    public void initEngine() {
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
    public void startEngine() {
        // Load the resource bundle
        this.progressDialog.setStatus("Loading game resources...");
        GameResourceBundle.getInstance().load();

        // Load the default scene
        this.progressDialog.setStatus("Loading scene...");
        this.engine.getSceneManager().loadScene(new DeveloperSplashScene());

        // Done, hide the progress dialog before starting the engine
        //this.progressDialog.dispose();

        // Start the engine
        this.engine.start();
    }
}
