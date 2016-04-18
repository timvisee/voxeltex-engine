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

package com.timvisee.keybarricade.demo;

import com.timvisee.keybarricade.KeyBarricade;
import com.timvisee.voxeltex.VoxelTexEngine;
import com.timvisee.voxeltex.swing.ProgressDialog;
import com.timvisee.voxeltex.swing.SwingUtils;

public class DemoApp {

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
    public DemoApp() { }

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
        System.out.println("Initializing demo...");

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
    @SuppressWarnings("Duplicates")
    private void initEngine() {
        // Show status
        this.progressDialog.setStatus("Initializing VoxelTex engine...");

        // Create a VoxelTex engine instance
        this.engine = new VoxelTexEngine();

        // Set the title
        this.engine.setTitle(KeyBarricade.APP_NAME + " v" + KeyBarricade.APP_VERSION_NAME);

        // Initialize the engine (without loading the recourses in advance)
        this.engine.init(false);

        // Manually load the engine resources...
        this.progressDialog.setStatus("Loading engine resources...");
        this.engine.load();
    }

    /**
     * Start the VoxelTex engine after it has been initialized.
     */
    private void startEngine() {
        // Load the default scene
        this.progressDialog.setStatus("Loading scene...");
        this.engine.getSceneManager().loadScene(new DemoScene());

        // Done, hide the progress dialog before starting the engine
        this.progressDialog.setVisible(false);

        // Start the engine
        this.engine.loop();
    }

    /**
     * Stop and exit the demo.
     */
    private void exit() {
        // Exiting, show the progress dialog
        this.progressDialog.setStatus("Quitting demo...");
        this.progressDialog.setVisible(true);

        // Dispose the engine resources
        this.progressDialog.setStatus("Disposing engine resources...");
        // TODO: EngineResourceBundle.getInstance().dispose();

        // Dispose the progress frame to ensure we're quitting properly
        this.progressDialog.dispose();

        // The demo has quit, show a status message and force quit
        System.out.println("Demo has quit");
        System.exit(0);
    }
}
