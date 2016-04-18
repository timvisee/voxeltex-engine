/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.voxeltex;

import com.timvisee.voxeltex.scene.TestEnvironmentScene;
import com.timvisee.voxeltex.swing.ProgressDialog;
import com.timvisee.voxeltex.swing.SwingUtils;

public class VoxelTex {

    /**
     * Engine name.
     */
    public static final String ENGINE_NAME = "VoxelTex";

    /**
     * Engine version name.
     */
    public static final String ENGINE_VERSION_NAME = "0.1";

    /**
     * Engine version code.
     */
    public static final int ENGINE_VERSION_CODE = 1;

    /**
     * VoxelTex engine instance.
     */
    private static VoxelTexEngine engine;

    /**
     * Progress dialog, used to show status outside of the engine window.
     */
    private static ProgressDialog progressDialog;

    /**
     * Get the full engine name string, including the version number.
     *
     * @return Engine name string.
     */
    public static String getEngineNameFull() {
        return ENGINE_NAME + " v" + ENGINE_VERSION_NAME;
    }

    /**
     * Run the VoxelTex engine with the test environment.
     *
     * @param args Startup arguments.
     */
    public static void main(String[] args) {
        // Use the native look and feel for Swing windows when possible
        SwingUtils.useNativeLookAndFeel();

        // Create and show the progress dialog
        progressDialog = new ProgressDialog(null, "VoxelTex Engine", false);
        progressDialog.setVisible(true);

        // Initialize the VoxelTex engine
        initEngine();

        // Start the VoxelTex engine
        startEngine();

        // Stop and exit the test environment
        exit();
    }

    /**
     * Initialize the VoxelTex engine.
     */
    private static void initEngine() {
        // Show status
        progressDialog.setStatus("Initializing VoxelTex engine...");

        // Create a VoxelTex engine instance
        engine = new VoxelTexEngine();

        // Set the title
        engine.setTitle(ENGINE_NAME + " v" + ENGINE_VERSION_NAME + " - Test Environment");

        // Initialize the engine
        engine.init(false);
    }

    /**
     * Start the VoxelTex engine after it has been initialized.
     */
    private static void startEngine() {
        // Manually load the engine resources...
        progressDialog.setStatus("Loading engine resources...");
        engine.load();

        // Load the test environment scene
        progressDialog.setStatus("Loading test environment scene...");
        engine.getSceneManager().loadScene(new TestEnvironmentScene());

        // Done, hide the progress dialog before starting the engine
        progressDialog.setVisible(false);

        // Run the engine
        engine.loop();
    }

    /**
     * Stop and exit the test environment.
     */
    private static void exit() {
        // Exiting, show the progress dialog
        progressDialog.setStatus("Quitting VoxelTex test environment...");
        progressDialog.setVisible(true);

        // Dispose the engine resources
        progressDialog.setStatus("Disposing engine resources...");
        // TODO: EngineResourceBundle.getInstance().dispose();

        // Dispose the progress frame to ensure we're quitting properly
        progressDialog.dispose();

        // The game has quit, show a status message and force quit
        System.out.println("VoxelTex test environment has quit");
        System.exit(0);
    }
}
