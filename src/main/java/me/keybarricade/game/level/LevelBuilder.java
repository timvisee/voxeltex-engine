package me.keybarricade.game.level;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import me.keybarricade.game.prefab.BoxPrefab;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {

    /**
     * Level to build.
     */
    private final Level level;

    /**
     * Level root object.
     */
    private GameObject levelRoot;

    /**
     * Spawn delay.
     */
    private float delay = 0.0f;

    /**
     * Constructor.
     *
     * @param level Level to build.
     */
    public LevelBuilder(Level level, GameObject levelRoot) {
        this.level = level;
        this.levelRoot = levelRoot;
    }

    /**
     * Get the level to build.
     *
     * @return Level to build.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Get the level root object.
     *
     * @return Level root object.
     */
    public GameObject getLevelRoot() {
        return this.levelRoot;
    }

    /**
     * Set the level root object.
     *
     * @param levelRoot Level root object.
     */
    public void setLevelRoot(GameObject levelRoot) {
        this.levelRoot = levelRoot;
    }

    /**
     * Build the level.
     */
    @SuppressWarnings("Duplicates")
    public void build() {
        // Make sure the level is valid
        if(this.level == null)
            throw new RuntimeException("Unable to build level, level instance invalid");

        // Show a status message
        System.out.println("Building runtime level...");

        // Get the tiles section
        ConfigurationSection objectsConfig = this.level.getConfig().getConfigurationSection("objects");

        // Get all object keys
        List<String> objectKeys = objectsConfig.getKeys("");

        // Loop through each object
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0, size = objectKeys.size(); i < size; i++) {
            // Get the object section
            ConfigurationSection objectConfig = objectsConfig.getConfigurationSection(objectKeys.get(i));

            // Create a list of configuration sections that specify the position
            List<ConfigurationSection> positionSections = new ArrayList<>();

            // Add the regular position configuration if available
            if(objectConfig.isConfigurationSection("pos"))
                positionSections.add(objectConfig.getConfigurationSection("pos"));

            // Check whether this game object has position sets
            if(objectConfig.isConfigurationSection("posSet")) {
                // Get a list of all set keys
                List<String> posSets = objectConfig.getKeys("posSet");

                // Add all their configuration sections to the position sections list
                //noinspection ForLoopReplaceableByForEach
                for(int j = 0, jSize = posSets.size(); j < jSize; j++)
                    positionSections.add(objectConfig.getConfigurationSection("posSet." + posSets.get(j)));
            }

            // Get the object type
            String rawObjectType = objectConfig.getString("type");

            // Loop through each position section to parse the position
            //noinspection StatementWithEmptyBody
            for(int positionIndex = 0, positionIndexSize = positionSections.size(); positionIndex < positionIndexSize; positionIndex++) {
                // Get the configuration section
                ConfigurationSection positionConfig = positionSections.get(positionIndex);

                // Parse the X and Y value
                String rawPositionX = positionConfig.getString("x", "0");
                String rawPositionY = positionConfig.getString("y", "0");

                // Calculate the minimum and maximum X and Y positions
                int minX = 0;
                int maxX = 1;
                int minY = 0;
                int maxY = 1;

                // Check whether the X coordinate contains any colon character
                if(rawPositionX.contains(":")) {
                    // Split the raw position
                    String[] splitted = rawPositionX.trim().split(":");

                    int a = Integer.parseInt(splitted[0]);
                    int b = Integer.parseInt(splitted[1]);

                    // Get the minimum and maximum part
                    minX = Math.min(a, b);
                    maxX = Math.max(a, b);

                } else
                    minX = maxX = Integer.parseInt(rawPositionX);

                // Check whether the Y coordinate contains any colon character
                if(rawPositionY.contains(":")) {
                    // Split the raw position
                    String[] splitted = rawPositionY.trim().split(":");

                    int a = Integer.parseInt(splitted[0]);
                    int b = Integer.parseInt(splitted[1]);

                    // Get the minimum and maximum part
                    minY = Math.min(a, b);
                    maxY = Math.max(a, b);
                } else
                    minY = maxY = Integer.parseInt(rawPositionY);

                // Loop through the positions
                for(int x = minX; x <= maxX; x++)
                    for(int y = minY; y <= maxY; y++)
                        buildObject(rawObjectType, x, y);
            }
        }
    }

    private void buildObject(String rawType, int x, int y) {
        // Load the box texture and material
        Texture boxTexture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        Material boxMaterial = new Material(boxTexture);

        // Spawn a wall
        if(rawType.trim().equalsIgnoreCase("wall"))
            this.levelRoot.addChild(new BoxPrefab(new Vector3f(x, 0.5f, y), false, delay += 0.02f, -1f, boxMaterial));
    }
}
