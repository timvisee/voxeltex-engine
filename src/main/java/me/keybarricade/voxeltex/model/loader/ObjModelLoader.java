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

package me.keybarricade.voxeltex.model.loader;

import me.keybarricade.voxeltex.model.RawModel;
import me.keybarricade.voxeltex.resource.engine.EngineAssetLoader;
import org.joml.Vector3f;

import java.io.*;

public class ObjModelLoader {

    /**
     * Load a 3D model from the engine assets.
     *
     * @param path Path of the engine asset model.
     *
     * @return The loaded model.
     */
    public static RawModel loadModelFromEngineAssets(String path) {
        return loadModelFromInputStream(EngineAssetLoader.getInstance().loadResourceStream(path));
    }

    /**
     * Load a 3D model from an input stream.
     *
     * @param input Input stream to load the model from.
     *
     * @return The loaded model.
     */
    public static RawModel loadModelFromInputStream(InputStream input) {
        // Load and return the 3D model
        try {
            return loadModel(new InputStreamReader(input));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load 3D model!");
        }
    }

    /**
     * Load a 3D model from a reader.
     *
     * @param reader Reader to load the model from.
     *
     * @return The loaded model.
     *
     * @throws IOException
     */
    public static RawModel loadModel(Reader reader) throws IOException {
        // Show a status message
        System.out.print("Loading model...");

        // Create a buffered reader to read the model
        BufferedReader bufferedReader = new BufferedReader(reader);

        RawModel model = new RawModel();

        // Read the file line by line
        String line;
        while((line = bufferedReader.readLine()) != null) {

            // Ignore lines with comments
            if(line.startsWith("#"))
                continue;

            // Read the object name
            if(line.startsWith("o ")) {
                // Fetch the name of the object
                String objectName = line.split(" ", 2)[1];

                // Show a message
                System.out.print(" Model name: " + objectName);
            }

            // Read vertexes
            if(line.startsWith("v ")) {
                // Split the values
                String[] values = line.split(" ");

                // Gather the vertex coordinates
                float x = Float.valueOf(values[1]);
                float y = Float.valueOf(values[2]);
                float z = Float.valueOf(values[3]);

                // Add the vertex
                model.addVertex(x, y, z);

                // Read normals
            } else if(line.startsWith("vn ")) {
                // Split the values
                String[] values = line.split(" ");

                // Gather the normal coordinates
                float x = Float.valueOf(values[1]);
                float y = Float.valueOf(values[2]);
                float z = Float.valueOf(values[3]);

                // Add the normal
                model.addNormal(x, y, z);

                // Read faces
            } else if(line.startsWith("f ")) {
                // Gather the vertex indexes
                float xVertex = Float.valueOf(line.split(" ")[1].split("/")[0]);
                float yVertex = Float.valueOf(line.split(" ")[2].split("/")[0]);
                float zVertex = Float.valueOf(line.split(" ")[3].split("/")[0]);

                // Put the vertex indexes in a vector
                Vector3f vertexIndexes = new Vector3f(xVertex, yVertex, zVertex);

                // Gather the normal indexes
                float xNormal = Float.valueOf(line.split(" ")[1].split("/")[2]);
                float yNormal = Float.valueOf(line.split(" ")[2].split("/")[2]);
                float zNormal = Float.valueOf(line.split(" ")[3].split("/")[2]);

                // Put the normal indexes in a vector
                Vector3f normalIndexes = new Vector3f(xNormal, yNormal, zNormal);

                // Add both index vectors as face
                model.addFace(vertexIndexes, normalIndexes);
            }
        }

        // Close the reader
        bufferedReader.close();

        // Add a new line
        System.out.print("\n");

        // Return the model
        return model;
    }
}
