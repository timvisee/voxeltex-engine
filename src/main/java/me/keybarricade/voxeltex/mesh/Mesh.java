package me.keybarricade.voxeltex.mesh;

import me.keybarricade.voxeltex.model.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.glGetError;
import static org.lwjgl.opengl.GLUtil.getErrorString;

public class Mesh {

    /**
     * The raw mesh object containing the vertexes data.
     */
    private RawMesh raw;

    /**
     * The number of vertexes in the buffered mesh.
     */
    private int vertexCount;

    /**
     * The number of normals in the buffered mesh.
     */
    private int normalCount;

    /**
     * Vertex buffer.
     */
    private FloatBuffer vertexBuffer;

    /**
     * Vertex buffer.
     */
    private FloatBuffer normalBuffer;

    /**
     * Texture buffer.
     */
    private FloatBuffer textureBuffer;

    /**
     * VBO vertex buffer handle.
     */
    private int vboVertexHandle = 0;

    /**
     * VBO normal buffer handle.
     */
    private int vboNormalHandle = 0;

    /**
     * VBO texture buffer handle.
     */
    private int vboTextureHandle = 0;

    /**
     * Constructor.
     *
     * @param raw Raw mesh.
     */
    public Mesh(RawMesh raw) {
        // Set the raw mesh
        this.raw = raw;

        // Buffer the mesh
        bufferMesh();
    }

    /**
     * Constructor.
     *
     * @param raw Raw model.
     */
    public Mesh(RawModel raw) {
        // Set the raw mesh
        this.raw = raw.toRawMesh();

        // Buffer the mesh
        bufferMesh();
    }

    /**
     * Get the raw mesh.
     *
     * @return Raw mesh.
     */
    public RawMesh getRawMesh() {
        return raw;
    }

    /**
     * Get the vertex buffer.
     *
     * @return Vertex buffer.
     */
    public FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    /**
     * Get the normal buffer.
     *
     * @return Normal buffer.
     */
    public FloatBuffer getNormalBuffer() {
        return normalBuffer;
    }

    /**
     * Get the texture buffer.
     *
     * @return Texture buffer.
     */
    public FloatBuffer getTextureBuffer() {
        return textureBuffer;
    }

    /**
     * Get the VBO vertex buffer handle.
     *
     * @return VBO vertex buffer handle.
     */
    public int getVboVertexHandle() {
        return vboVertexHandle;
    }

    /**
     * Get the VBO texture buffer handle.
     *
     * @return VBO texture buffer handle.
     */
    public int getVboTextureHandle() {
        return vboTextureHandle;
    }

    /**
     * Check whether this mesh has any normal data.
     *
     * @return True if this mesh has normal data, false if not.
     */
    public boolean hasNormalData() {
        // Determine the result based on the stored buffers if data has been buffered
        if(isBuffered())
            return this.vboNormalHandle > 0;

        // The mesh isn't buffered, determine the result based on the raw mesh
        return this.raw.hasNormalData();
    }

    /**
     * Check whether this mesh has any texture data.
     *
     * @return True if this mesh has texture data, false if not.
     */
    public boolean hasTextureData() {
        // Determine the result based on the stored buffers if data has been buffered
        if(isBuffered())
            return this.vboTextureHandle > 0;

        // The mesh isn't buffered, determine the result based on the raw mesh
        return this.raw.hasTextureData();
    }

    /**
     * Check whether the mesh is buffered on the graphics card.
     *
     * @return True if this mash is buffered, false if not.
     */
    public boolean isBuffered() {
        return this.vboVertexHandle > 0;
    }

    /**
     * Build and buffer the mesh on the graphics card.
     */
    public void bufferMesh() {
        // Create a flipped float buffer for the vertexes
        this.vertexBuffer = BufferUtils.createFloatBuffer(this.raw.getVertexCount() * this.raw.getVertexAxisCount());
        this.vertexBuffer.put(this.raw.getVertexes());
        this.vertexBuffer.flip();

        // Create a VBO handle for the vertexes and bind the buffer
        vboVertexHandle = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vboVertexHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.vertexBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        // Buffer the normal data if the mesh has any configured
        if(this.raw.hasNormalData()) {
            // Create a flipped float buffer for the normal coordinates
            this.normalBuffer = BufferUtils.createFloatBuffer(this.raw.getNormalCount() * this.raw.getNormalAxisCount());
            this.normalBuffer.put(this.raw.getNormals());
            this.normalBuffer.flip();

            // Create a VBO handle for the normal coordinates and bind the buffer
            vboNormalHandle = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vboNormalHandle);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.normalBuffer, GL15.GL_STATIC_DRAW);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        }

        // Buffer the texture data if the mesh has any configured
        if(this.raw.hasTextureData()) {
            // Create a flipped float buffer for the texture coordinates
            this.textureBuffer = BufferUtils.createFloatBuffer(this.raw.getVertexCount() * this.raw.getTextureAxisCount());
            this.textureBuffer.put(this.raw.getTextures());
            this.textureBuffer.flip();

            // Create a VBO handle for the texture coordinates and bind the buffer
            vboTextureHandle = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vboTextureHandle);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.textureBuffer, GL15.GL_STATIC_DRAW);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        }

        // Set the number of vertexes and normals
        this.vertexCount = this.raw.getVertexCount();
        this.normalCount = this.raw.getNormalCount();

        // TODO: We can destroy the buffers here?
    }

    /**
     * Update the buffered mesh on the graphics card to use the current raw mesh.
     */
    public void updateBuffer() {
        // Clear the buffers if they are already made
        clearMeshBuffer();

        // Buffer the new mesh
        bufferMesh();
    }

    /**
     * Clear the buffers for this mesh on the graphics card.
     */
    public void clearMeshBuffer() {
        // Delete data from old buffers if available
        if(this.vertexBuffer != null)
            this.vertexBuffer.clear();
        if(this.normalBuffer != null)
            this.normalBuffer.clear();
        if(this.textureBuffer != null)
            this.textureBuffer.clear();

        // Reset the buffer instances
        this.vertexBuffer = null;
        this.normalBuffer = null;
        this.textureBuffer = null;

        // Clear old VBO buffers
        if(this.vboVertexHandle != 0)
            GL15.glDeleteBuffers(this.vboVertexHandle);
        if(this.vboNormalHandle != 0)
            GL15.glDeleteBuffers(this.vboNormalHandle);
        if(this.vboTextureHandle != 0)
            GL15.glDeleteBuffers(this.vboTextureHandle);

        // Reset the VBO handles
        this.vboVertexHandle = 0;
        this.vboNormalHandle = 0;
        this.vboTextureHandle = 0;

        // Reset the vertex and normal count
        this.vertexCount = 0;
        this.normalCount = 0;
    }

    /**
     * Render or draw the mesh using OpenGL.
     */
    public void draw() {
//        // Show an error if occurred
//        int err;
//        if((err = glGetError()) != GL_NO_ERROR)
//            System.out.println("Begin mesh draw: " + getErrorString(err));

        // Bind the vertex buffer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandle);
        GL11.glVertexPointer(this.raw.getVertexAxisCount(), GL11.GL_FLOAT, 0, 0L);

        // Bind the normal coordinate buffer if available
        if(hasNormalData()) {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboNormalHandle);
            GL11.glNormalPointer(GL11.GL_FLOAT, 0, 0L);
        }

        // Bind the texture coordinate buffer if available
        if(hasTextureData()) {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboTextureHandle);
            GL11.glTexCoordPointer(this.raw.getTextureAxisCount(), GL11.GL_FLOAT, 0, 0L);
        }

        // Enable the client states for the buffers that were bound
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        if(hasNormalData())
            GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        if(hasTextureData())
            GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

        // Draw the mesh
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.vertexCount);

        // Disable the client used states
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        if(hasNormalData())
            GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        if(hasTextureData())
            GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

        // Unbind all buffers
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

//        // Show an error if occurred
//        if((err = glGetError()) != GL_NO_ERROR)
//            System.out.println("End mesh draw: " + getErrorString(err));
    }
}
