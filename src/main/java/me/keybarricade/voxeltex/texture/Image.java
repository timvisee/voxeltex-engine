package me.keybarricade.voxeltex.texture;

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
     * Constructor.
     *
     * @param path Image path.
     */
    public Image(String path) {
        // Create a byte buffer for the image to load
        ByteBuffer imageBuffer;

        // Load the image file into the image buffer
        try {
            // TODO: Allocate enough memory for the image!
            imageBuffer = BufferUtil.fileToByteBuffer(path, 8 * 1024);

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
        image = stbi_load_from_memory(imageBuffer, w, h, c, 0);

        // Set the image fields
        this.width = w.get(0);
        this.height = w.get(0);
        this.components = w.get(0);
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
