package me.keybarricade.voxeltex.math.vector;

import org.joml.Vector2f;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Vector3f extends org.joml.Vector3f {

    /**
     * Zero vector.
     */
    // TODO: Make sure this can't be changed!
    public static final Vector3f ZERO = new Vector3f(0.0f, 0.0f, 0.0f);

    /**
     * Zero vector.
     */
    // TODO: Make sure this can't be changed!
    public static final Vector3f ONE = new Vector3f(1.0f, 1.0f, 1.0f);

    /**
     * Create a new {@link Vector3f} of <tt>(0, 0, 0)</tt>.
     */
    public Vector3f() {
        super();
    }

    /**
     * Create a new {@link Vector3f} and initialize all three components with the given value.
     *
     * @param d
     *          the value of all three components
     */
    public Vector3f(float d) {
        super(d, d, d);
    }

    /**
     * Create a new {@link Vector3f} with the given component values.
     *
     * @param x
     *          the value of x
     * @param y
     *          the value of y
     * @param z
     *          the value of z
     */
    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    /**
     * Create a new {@link Vector3f} with the same values as <code>v</code>.
     *
     * @param v
     *          the {@link Vector3f} to copy the values from
     */
    public Vector3f(Vector3f v) {
        super(v);
    }

    /**
     * Create a new {@link Vector3f} with the first two components from the
     * given <code>v</code> and the given <code>z</code>
     *
     * @param v
     *          the {@link Vector2f} to copy the values from
     * @param z
     *          the z component
     */
    public Vector3f(Vector2f v, float z) {
        super(v, z);
    }

    /**
     * Create a new {@link Vector3f} and read this vector from the supplied {@link ByteBuffer}
     * at the current buffer {@link ByteBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     * <p>
     * In order to specify the offset into the ByteBuffer at which
     * the vector is read, use {@link #Vector3f(int, ByteBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer values will be read in <tt>x, y, z</tt> order
     * @see #Vector3f(int, ByteBuffer)
     */
    public Vector3f(ByteBuffer buffer) {
        super(buffer);
    }

    /**
     * Create a new {@link Vector3f} and read this vector from the supplied {@link ByteBuffer}
     * starting at the specified absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer values will be read in <tt>x, y, z</tt> order
     */
    public Vector3f(int index, ByteBuffer buffer) {
        super(index, buffer);
    }

    /**
     * Create a new {@link Vector3f} and read this vector from the supplied {@link FloatBuffer}
     * at the current buffer {@link FloatBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     * <p>
     * In order to specify the offset into the FloatBuffer at which
     * the vector is read, use {@link #Vector3f(int, FloatBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer values will be read in <tt>x, y, z</tt> order
     * @see #Vector3f(int, FloatBuffer)
     */
    public Vector3f(FloatBuffer buffer) {
        super(buffer);
    }

    /**
     * Create a new {@link Vector3f} and read this vector from the supplied {@link FloatBuffer}
     * starting at the specified absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     *
     * @param index  the absolute position into the FloatBuffer
     * @param buffer values will be read in <tt>x, y, z</tt> order
     */
    public Vector3f(int index, FloatBuffer buffer) {
        super(index, buffer);
    }

    /**
     * Calculate the relative vector and set it in {@link this}.
     *
     * @param other Other vector.
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f relative(Vector3f other, Vector3f dest) {
        // Create the relative vector
        sub(other, dest);

        // Return this
        return dest;
    }

    /**
     * Get the relative vector.
     *
     * @param other Other vector.
     *
     * @return Relative vector.
     */
    public Vector3f relative(Vector3f other) {
        // Create the relative vector
        Vector3f relative = new Vector3f();

        // Calculate and return the relative vector
        return relative(other, relative);
    }

    /**
     * Get the relative vector to zero.
     *
     * @return Relative vector.
     */
    public Vector3f relativeZero() {
        return relative(ZERO);
    }
}
