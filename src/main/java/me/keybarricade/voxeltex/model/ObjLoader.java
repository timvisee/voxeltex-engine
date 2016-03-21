package me.keybarricade.voxeltex.model;

import me.keybarricade.voxeltex.resource.engine.EngineAssetLoader;
import org.joml.Vector3f;

import java.io.*;

public class ObjLoader {

    public static Model loadModel(String path) throws FileNotFoundException, IOException {
        System.out.println("Loading model: " + path);

        BufferedReader reader = new BufferedReader(new InputStreamReader(EngineAssetLoader.getInstance().loadResourceStream(path)));

        Model model = new Model();

        String line;

        while((line = reader.readLine()) != null) {

            // Ignore lines with comments
            if(line.startsWith("#"))
                continue;

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

                Vector3f vertexIndexes = new Vector3f(xVertex, yVertex, zVertex);

                // Gather the vertex indexes
                float xNormal = Float.valueOf(line.split(" ")[1].split("/")[2]);
                float yNormal = Float.valueOf(line.split(" ")[2].split("/")[2]);
                float zNormal = Float.valueOf(line.split(" ")[3].split("/")[2]);

                Vector3f normalIndexes = new Vector3f(xNormal, yNormal, zNormal);

                model.addFace(vertexIndexes, normalIndexes);
            }
        }

        // Close the reader
        reader.close();

        return model;
    }
}
