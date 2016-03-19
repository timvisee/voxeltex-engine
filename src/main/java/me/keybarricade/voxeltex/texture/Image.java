package me.keybarricade.voxeltex.texture;

import me.keybarricade.voxeltex.resource.engine.EngineAssetLoader;
import me.keybarricade.voxeltex.util.BufferUtil;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

public class Image {

    /**
     * Number of components for RGB images.
     */
    public static final int COMPONENTS_RGB = 3;

    /**
     * Number of components for RGBA images.
     */
    public static final int COMPONENTS_RGBA = 4;

    /**
     * Byte buffer containing the image data.
     */
    private final ByteBuffer image;

    /**
     * Image width.
     */
    private final int width;

    /**
     * Image height.
     */
    private final int height;

    /**
     * Number of components on this image.
     */
    private final int components;

    /**
     * Constructor.
     *
     * @param image Image as byte buffer.
     * @param width Width of the image.
     * @param height Height of the image.
     * @param components Components on this image.
     */
    public Image(ByteBuffer image, int width, int height, int components) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.components = components;
    }

    /**
     * Load an image from the given path.
     *
     * @param path Image path.
     *
     * @return Image.
     */
    // TODO: This is causing an NPE when using the image in a Texture, fix this!
    public static Image loadFromEngineAssets(String path) {
        // Create a byte buffer for the image to load
        ByteBuffer imageBuffer;

        // Load the image file into the image buffer
        imageBuffer = EngineAssetLoader.getInstance().loadResourceByteBuffer(path);

        // Create some integer buffers for STB interaction
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        // Load the image into memory using STB
        ByteBuffer image = stbi_load_from_memory(imageBuffer, w, h, c, 0);

        // Create the image instance and return it
        return new Image(image, w.get(0), h.get(0), c.get(0));
    }

    /**
     * Load an image from the given path.
     *
     * @param path Image path.
     *
     * @return Image.
     */
    public static Image loadFromPath(String path) {
        // Create a byte buffer for the image to load
        ByteBuffer imageBuffer;

        // Load the image file into the image buffer
        try {
            // TODO: Allocate enough memory for the image!
            imageBuffer = BufferUtil.fileToByteBuffer(path);

        } catch(IOException e) {
            // TODO: Use a default image!
            System.out.println("Could not find the image at the path '" + path + "'");
            throw new RuntimeException(e);
        }

        // Create some integer buffers for STB interaction
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        // Load the image into memory using STB
        ByteBuffer image = stbi_load_from_memory(imageBuffer, w, h, c, 0);

        // Create the image instance and return it
        return new Image(image, w.get(0), h.get(0), c.get(0));
    }

    /**
     * Get the image as byte buffer.
     *
     * @return Image.
     */
    public ByteBuffer getImage() {
        return image;
    }

    /**
     * Get the width of the image.
     *
     * @return Image width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the image.
     *
     * @return Image height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the components of an image.
     *
     * @return Image components.
     */
    public int getComponents() {
        return components;
    }

    /**
     * Dispose the image, and free the memory.
     */
    public void dispose() {
        // Free the image memory through STB
        stbi_image_free(image);
    }
}
