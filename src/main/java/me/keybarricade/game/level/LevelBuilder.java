package me.keybarricade.game.level;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import me.keybarricade.game.LockType;
import me.keybarricade.game.component.animator.ObjectSpawnAnimatorComponent;
import me.keybarricade.game.prefab.*;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
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
     * Player prefab.
     */
    private PlayerPrefab player;

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
     *
     * @param delay Delay in seconds.
     */
    @SuppressWarnings("Duplicates")
    public void build(float delay) {
        // Make sure the level is valid
        if(this.level == null)
            throw new RuntimeException("Unable to build level, level instance invalid");

        // Show a status message
        System.out.println("Building level environment at runtime...");

        // Set the delay
        this.delay = delay;

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
                String rawPositionX = positionConfig.getString("x", String.valueOf(positionConfig.getInt("x", 0)));
                String rawPositionY = positionConfig.getString("y", String.valueOf(positionConfig.getInt("y", 0)));

                // Calculate the minimum and maximum X and Y positions
                int minX, maxX, minY, maxY;

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

        // Spawn the player
        if(this.player != null)
            this.player.addComponent(new ObjectSpawnAnimatorComponent(delay += 0.02f, new RigidbodyComponent(false)));
    }

    /**
     * Build the object with the given type.
     *
     * @param rawType Raw object type.
     * @param x X coordinate of the object.
     * @param y Y coordinate of the object.
     */
    private void buildObject(String rawType, int x, int y) {
        // Create a wall
        if(rawType.trim().equalsIgnoreCase("wall"))
            this.levelRoot.addChild(new BoxPrefab(new Vector3f(x, 0.5f, y), false, delay += 0.02f, -1f));

            // Create a key
        else if(rawType.trim().equals("key")) {
            // TODO: Put player instance in here!
            KeyPickupPrefab keyObject = new KeyPickupPrefab("KeyPickupPrefab", this.player, LockType.YELLOW);
            keyObject.getTransform().getPosition().set(x, 0, y);
            keyObject.addComponent(new ObjectSpawnAnimatorComponent(delay += 0.02f));
            this.levelRoot.addChild(keyObject);
        }

        // Create a lock
        else if(rawType.trim().equals("lock")) {
            PadlockPrefab padlockObject = new PadlockPrefab(this.player, LockType.YELLOW);
            padlockObject.getTransform().getPosition().set(x, 0, y);
            padlockObject.addComponent(new ObjectSpawnAnimatorComponent(delay += 0.02f, new RigidbodyComponent(true)));
            this.levelRoot.addChild(padlockObject);
        }

        // Create a finish
        else if(rawType.trim().equals("finish")) {
            FinishPrefab finish = new FinishPrefab(this.player);
            finish.getTransform().getPosition().set(x, 0.1f, y);
            finish.addComponent(new ObjectSpawnAnimatorComponent(delay += 0.02f));
            this.levelRoot.addChild(finish);
        }

        // Create a key
        else if(rawType.trim().equals("player")) {
            PlayerPrefab playerObject = new PlayerPrefab();
            playerObject.getTransform().setPosition(new Vector3f(x, 0.5f, y));
            this.levelRoot.addChild(playerObject);
            this.player = playerObject;
        }
    }

    /**
     * Get the player instance from the builder.
     *
     * @return Player instance.
     */
    public PlayerPrefab getPlayer() {
        return this.player;
    }
}
