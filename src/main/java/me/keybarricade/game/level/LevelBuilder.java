package me.keybarricade.game.level;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import me.keybarricade.game.LockType;
import me.keybarricade.game.component.animator.ObjectSpawnAnimatorComponent;
import me.keybarricade.game.prefab.*;
import me.keybarricade.game.scene.GameScene;
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
     * Game scene instance.
     */
    private final GameScene gameScene;

    /**
     * Level root object.
     */
    private GameObject levelRoot;

    /**
     * Player prefab.
     */
    private PlayerPrefab player;

    /**
     * Hint of the level.
     */
    private String levelHint;

    /**
     * Spawn delay.
     */
    private float delay = 0.0f;

    /**
     * Constructor.
     *
     * @param level Level to build.
     * @param gameScene Game scene instance.
     * @param levelRoot Level root object.
     */
    public LevelBuilder(Level level, GameScene gameScene, GameObject levelRoot) {
        this.level = level;
        this.gameScene = gameScene;
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

        // Get the level hint
        this.levelHint = this.level.getConfig().getString("hint", null);

        // Get the tiles section
        ConfigurationSection objectsConfig = this.level.getConfig().getConfigurationSection("objects");

        // Get all object keys
        List<String> objectKeys = objectsConfig.getKeys("");

        // Keep track of the minimum and maximum block positions
        boolean mapCoordsInit = false;
        int mapMinX = 0, mapMaxX = 0, mapMinY = 0, mapMaxY = 0;

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

            // Get the object type and data value if available
            String rawObjectType = objectConfig.getString("type");
            int dataValue = objectConfig.getInt("dataValue", 0);

            // Loop through each position section to parse the position
            //noinspection StatementWithEmptyBody
            for(int positionIndex = 0, positionIndexSize = positionSections.size(); positionIndex < positionIndexSize; positionIndex++) {
                // Get the configuration section
                ConfigurationSection positionConfig = positionSections.get(positionIndex);

                // Parse the X and Y value
                String rawPositionX = positionConfig.getString("x", String.valueOf(positionConfig.getInt("x", 0)));
                String rawPositionY = positionConfig.getString("y", String.valueOf(positionConfig.getInt("y", 0)));

                // Calculate the minimum and maximum X and Y positions
                int fromX, toX, fromY, toY;

                // Check whether the X coordinate contains any colon character
                if(rawPositionX.contains(":")) {
                    // Split the raw position
                    String[] split = rawPositionX.trim().split(":");

                    // Parse the values
                    fromX = Integer.parseInt(split[0]);
                    toX = Integer.parseInt(split[1]);

                } else
                    fromX = toX = Integer.parseInt(rawPositionX);

                // Check whether the Y coordinate contains any colon character
                if(rawPositionY.contains(":")) {
                    // Split the raw position
                    String[] split = rawPositionY.trim().split(":");

                    // Parse the values
                    fromY = Integer.parseInt(split[0]);
                    toY = Integer.parseInt(split[1]);

                } else
                    fromY = toY = Integer.parseInt(rawPositionY);

                // Initialize the map's minimum and maximum coordinates for the first position
                if(!mapCoordsInit) {
                    mapMinX = fromX;
                    mapMaxX = fromX;
                    mapMinY = fromY;
                    mapMaxY = fromY;
                    mapCoordsInit = true;
                }

                // Find the minimum and maximum coordinates for the map with the current position
                mapMinX = Math.min(Math.min(fromX, toX), mapMinX);
                mapMaxX = Math.max(Math.max(fromX, toX), mapMaxX);
                mapMinY = Math.min(Math.min(fromY, toY), mapMinY);
                mapMaxY = Math.max(Math.max(fromY, toY), mapMaxY);

                // Loop through the positions
                for(int x = fromX; fromX < toX ? x <= toX : x >= toX; x += fromX < toX ? 1 : -1)
                    for(int y = fromY; fromY < toY ? y <= toY : y >= toY; y += fromY < toY ? 1 : -1)
                        buildObject(rawObjectType, dataValue, x, y);
            }
        }

        // Spawn the player
        if(this.player != null)
            this.player.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f, new RigidbodyComponent(false)));

        // Spawn some randomized blocks outside the map
        for(int i = 0; i < 4; i++) {
            // Enlarge the outer edges by one
            mapMinX--;
            mapMinY--;
            mapMaxX++;
            mapMaxY++;

            // Loop through the edges of the map
            for(int x = mapMinX; x <= mapMaxX; x++) {
                for(int y = mapMinY; y <= mapMaxY; y++) {
                    // Only place blocks for walls
                    if(x != mapMinX && x != mapMaxX && y != mapMinY && y != mapMaxY)
                        continue;

                    // Determine whether to spawn a dummy block
                    if(Math.random() < Math.min(0.1f * (3 - i), 0.2f))
                        buildObject("wall", 0, x, y);
                }
            }
        }
    }

    /**
     * Build the object with the given type.
     *
     * @param rawType Raw object type.
     * @param dataValue Data value.
     * @param x X coordinate of the object.
     * @param y Y coordinate of the object.
     */
    private void buildObject(String rawType, int dataValue, int x, int y) {
        // Invert the y axis
        y *= -1;

        // Create a wall
        if(rawType.trim().equalsIgnoreCase("wall")){
            this.levelRoot.addChild(new BoxPrefab(new Vector3f(x + 0.5f, 0.5f, y + 0.5f), false, this.delay += 0.02f, -1f));
        }

        // Create a player
        else if(rawType.trim().equals("player")) {
            PlayerPrefab playerObject = new PlayerPrefab(this.gameScene);
            playerObject.getTransform().setPosition(new Vector3f(x + 0.5f, 0.5f, y + 0.5f));
            this.levelRoot.addChild(playerObject);
            this.player = playerObject;

            // Set the level hint
            this.player.setHint(this.levelHint);
        }

        // Create a key
        else if(rawType.trim().equals("key")) {
            KeyPickupPrefab keyObject = new KeyPickupPrefab("KeyPickupPrefab", this.player, LockType.fromDataValue(dataValue));
            keyObject.getTransform().getPosition().set(x + 0.5f, 0, y + 0.5f);
            keyObject.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f));
            this.levelRoot.addChild(keyObject);
        }

        // Create a lock
        else if(rawType.trim().equals("lock")) {
            PadlockPrefab padlockObject = new PadlockPrefab(this.player, LockType.fromDataValue(dataValue));
            padlockObject.getTransform().getPosition().set(x + 0.5f, 0, y + 0.5f);
            padlockObject.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f, new RigidbodyComponent(true)));
            this.levelRoot.addChild(padlockObject);
        }

        // Create a lamp
        else if(rawType.trim().equals("lamp")) {
            LampPrefab lampObject = new LampPrefab(LockType.fromDataValue(dataValue).getColorCopy());
            lampObject.getTransform().getPosition().set(x + 0.5f, 0.01f, y + 0.5f);
            lampObject.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f));
            this.levelRoot.addChild(lampObject);
        }

        // Create a finish
        else if(rawType.trim().equals("finish")) {
            FinishPrefab finish = new FinishPrefab(this.player);
            finish.getTransform().getPosition().set(x + 0.5f, 0.01f, y + 0.5f);
            finish.addComponent(new ObjectSpawnAnimatorComponent(this.delay += 0.02f));
            this.levelRoot.addChild(finish);
        }

        // Show errors
        else
            System.out.println("Unknown level object: " + rawType);
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
