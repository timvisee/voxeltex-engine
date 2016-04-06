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

package me.keybarricade.voxeltex.texture;

import me.keybarricade.voxeltex.util.BufferUtil;
import me.keybarricade.voxeltex.util.Color;
import org.lwjgl.opengl.GL13;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGR;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Texture {

    /**
     * Number of components for RGB textures.
     */
    public static final int COMPONENTS_RGB = Image.COMPONENTS_RGB;

    /**
     * Number of components for RGBA textures.
     */
    public static final int COMPONENTS_RGBA = Image.COMPONENTS_RGBA;

    /**
     * Unique texture ID, for OpenGL.
     */
    private int id;

    /**
     * Texture width.
     */
    private float width;

    /**
     * Texture height.
     */
    private float height;

    /**
     * Filter option for this image.
     *
     * Possible options:
     * - GL_LINEAR
     * - GL_NEAREST
     */
    private static int filter = GL_LINEAR;

    /**
     * Wrap option for this image.
     *
     * Possible options:
     * - GL_REPEAT
     * - GL_CLAMP
     */
    private static int wrap = GL_REPEAT;

    /**
     * Constructor.
     */
    private Texture() {
        // Assign a texture ID from OpenGL
        this.id = glGenTextures();

        // Add the texture to the texture manager
        TextureTracker.trackTexture(this);
    }

    /**
     * Generate a solid colored texture based on the given color. It is possible to use the alpha channels of the color.
     *
     * @param color Solid color.
     * @param width Texture width.
     * @param height Texture height.
     *
     * @return The generated texture.
     */
    public static Texture fromColor(Color color, int width, int height) {
        ByteBuffer buffer = BufferUtil.createByteBuffer(width * height * 4);

        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                buffer.put((byte) (color.getRed() * 255.0f))
                        .put((byte) (color.getGreen() * 255.0f))
                        .put((byte) (color.getBlue() * 255.0f))
                        .put((byte) (color.getAlpha() * 255.0f));

        // Flip the buffer so we can read
        buffer.flip();

        // Create a texture from the byte buffer, return the result
        return fromByteBuffer(buffer, width, height, Image.COMPONENTS_RGBA);
    }

    /**
     * Create a texture based on the given image.
     *
     * @param image Image to use for the texture.
     *
     * @return Texture.
     */
    public static Texture fromImage(Image image) {
        // Create and return the texture
        return fromByteBuffer(image.getImage(), image.getWidth(), image.getHeight(), image.getComponents());
    }

    /**
     * Create a texture based on the given image and consume/dispose the image.
     *
     * @param image Image to use for the texture.
     *
     * @return Texture.
     */
    public static Texture consumeImage(Image image) {
        // Create the texture
        Texture texture = fromImage(image);

        // Dispose/consume the image since we're done
        image.dispose();

        // Return the texture that was created
        return texture;
    }

    /**
     * Create a texture from a byte buffer.
     *
     * @param buffer The byte buffer containing the texture data.
     * @param width Texture width.
     * @param height Texture height.
     * @param components Number of components in the texture buffer. Choose from COMPONENTS_RGBA or COMPONENTS_RGB.
     *
     * @return Texture.
     */
    public static Texture fromByteBuffer(ByteBuffer buffer, int width, int height, int components) {
        // Create a new texture instance
        Texture texture = new Texture();

        // Bind the texture
        texture.bind(GL13.GL_TEXTURE0);

        // Set the texture parameters
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);

        // Configure the texture and write the buffer to the texture
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, components == 4 ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, buffer);

        // Done using the texture, unbind
        Texture.unbind();

        // Set the width and height
        texture.width = width;
        texture.height = height;

        // Return the created texture
        return texture;
    }

    /**
     * Get the OpenGL texture ID.
     *
     * @return Texture ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the texture width.
     *
     * @return Texture width.
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Get the texture height.
     *
     * @return Texture height.
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Get the texture filter mode.
     *
     * Possible options:
     * - GL_LINEAR
     * - GL_NEAREST
     *
     * @return Texture filter.
     */
    public static int getFilter() {
        return Texture.filter;
    }

    /**
     * Get the texture wrap mode.
     *
     * Possible options:
     * - GL_REPEAT
     * - GL_CLAMP
     *
     * @return Texture wrap.
     */
    public static int getWrap() {
        return Texture.wrap;
    }

    /**
     * Set the texture filter mode.
     *
     * Possible options:
     * - GL_LINEAR
     * - GL_NEAREST
     *
     * @param filter Texture filter.
     */
    public static void setFilter(int filter) {
        Texture.filter = filter;
    }

    /**
     * Set the texture wrap mode.
     *
     * Possible options:
     * - GL_REPEAT
     * - GL_CLAMP
     *
     * @param wrap Texture wrap.
     */
    public static void setWrap(int wrap) {
        Texture.wrap = wrap;
    }

    /**
     * Bind the current texture to OpenGL.
     *
     * @param glTextureId The OpenGL texture ID.
     */
    public void bind(int glTextureId) {
        // Activate the first texture
        glActiveTexture(glTextureId);

        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, this.id);
    }

    /**
     * Unbind any textures from OpenGL.
     */
    public static void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
     * Get the current texture from OpenGL.
     *
     * @param format Texture format.
     *
     * @return Byte buffer with texture.
     */
    public ByteBuffer getImage2D(int format) {
        return getImage2D(format, GL_FLOAT);
    }

    /**
     * Get the current texture from OpenGL.
     *
     * @param format Texture format.
     * @param type Texture type.
     *
     * @return Byte buffer with texture.
     */
    public ByteBuffer getImage2D(int format, int type) {
        // Determine the number of components, use RGBA as default
        int size = Image.COMPONENTS_RGBA;

        // Switch to RGB for some formats
        switch(format) {
            case GL_RGB:
            case GL_BGR:
                size = 3;
                break;
        }

        // Calculate the byte buffer size
        size = (int) (size * width * height * 4);

        // Create a byte buffer with the given size
        ByteBuffer buffer = BufferUtil.createByteBuffer(size);

        // Get and return the texture
        return getImage2D(format, type, buffer);
    }

    /**
     * Get the current texture from OpenGL.
     *
     * @param format Texture format.
     * @param type Texture type.
     * @param buffer Byte buffer for texture.
     *
     * @return Byte buffer with texture.
     */
    private ByteBuffer getImage2D(int format, int type, ByteBuffer buffer) {
        // Get the texture from OpenGL
        glGetTexImage(GL_TEXTURE_2D, 0, format, type, buffer);

        // Return the buffer with the texture
        return buffer;
    }

    /**
     * Dispose the texture.
     * This will free the memory used by this texture.
     */
    public void dispose() {
        // Remove the texture from OpenGL
        glDeleteTextures(id);

        // Remove the image from the texture manager
        TextureTracker.untrackTexture(this);
    }
}
