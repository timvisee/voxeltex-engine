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

package me.keybarricade.voxeltex.component.transform;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import org.joml.Vector2f;

public class RectangleTransform extends BaseComponent {

    /**
     * The parent rectangle transform, if available.
     */
    private RectangleTransform parent;

    /**
     * The position of the rectangle transform.
     * The X and/or Y coordinate might be used as left or top offset if the horizontal or vertical anchor has the stretch preset.
     */
    private final Vector2f position = Vector2fFactory.identity();

    /**
     * The size of the rectangle transform.
     * The X and/or Y coordinate might be used as left or top offset if the horizontal or vertical anchor has the stretch preset.
     */
    private final Vector2f size = new Vector2f(100);

    /**
     * The rectangle transform anchor.
     */
    private final RectangleTransformAnchor anchor = new RectangleTransformAnchor();

    // TODO: Rotation
    // TODO: Scale

    /**
     * Constructor.
     *
     * @param size Rectangle size.
     * @param horizontalAnchor Horizontal anchor preset.
     * @param verticalAnchor Vertical anchor preset.
     */
    public RectangleTransform(Vector2f size,
                              HorizontalTransformAnchorType horizontalAnchor,
                              VerticalTransformAnchorType verticalAnchor) {
        setSize(size);
        setAnchorPreset(horizontalAnchor, verticalAnchor);
    }

    /**
     * Constructor.
     *
     * @param size Rectangle size.
     * @param anchor Rectangle anchor preset.
     */
    public RectangleTransform(Vector2f size, RectangleTransformAnchor anchor) {
        setSize(size);
        setAnchor(anchor);
    }

    /**
     * Constructor.
     *
     * @param position Rectangle position.
     * @param size Rectangle size.
     */
    public RectangleTransform(Vector2f position, Vector2f size) {
        setPosition(position);
        setSize(size);
    }

    /**
     * Constructor.
     *
     * @param position Rectangle position.
     * @param size Rectangle size.
     * @param horizontalAnchor Horizontal anchor preset.
     * @param verticalAnchor Vertical anchor preset.
     */
    public RectangleTransform(Vector2f position, Vector2f size,
                              HorizontalTransformAnchorType horizontalAnchor,
                              VerticalTransformAnchorType verticalAnchor) {
        setPosition(position);
        setSize(size);
        setAnchorPreset(horizontalAnchor, verticalAnchor);
    }

    /**
     * Constructor.
     *
     * @param position Rectangle position.
     * @param size Rectangle size.
     * @param anchor Rectangle anchor preset.
     */
    public RectangleTransform(Vector2f position, Vector2f size, RectangleTransformAnchor anchor) {
        setPosition(position);
        setSize(size);
        setAnchor(anchor);
    }

    /**
     * Get the parent transform if available.
     *
     * @return Parent transform, or null.
     */
    public RectangleTransform getParentTransform() {
        return this.parent;
    }

    /**
     * Update/refresh the parent transform.
     */
    private void updateParentTransform() {
        // Get the parent transform if available
        if(getOwner().hasParent())
            this.parent = getOwner().getParent().getComponent(RectangleTransform.class);
    }

    /**
     * Check whether this rectangle transform has any parent rectangle transform.
     *
     * @return True if there is a parent rectangle transform, false if not.
     */
    public boolean hasParentTransform() {
        return this.parent != null;
    }

    /**
     * Get the transform position.
     *
     * @return Transform position.
     */
    public Vector2f getPosition() {
        return this.position;
    }

    /**
     * Get the position X field.
     *
     * @return Position X.
     */
    public float getPositionX() {
        return this.position.x;
    }

    /**
     * Get the position left field.
     *
     * @return Position left.
     */
    public float getPositionLeft() {
        return this.position.x;
    }

    /**
     * Get the position Y field.
     *
     * @return Position Y.
     */
    public float getPositionY() {
        return this.position.y;
    }

    /**
     * Get the position top field.
     *
     * @return Position top.
     */
    public float getPositionTop() {
        return this.position.y;
    }

    /**
     * Set the transform position.
     *
     * @param position Transform position.
     */
    public void setPosition(Vector2f position) {
        this.position.set(position);
    }

    /**
     * Set the position X field.
     *
     * @param x Position X.
     */
    public void setPositionX(float x) {
        this.position.x = x;
    }

    /**
     * Set the position left field.
     *
     * @param left Position left.
     */
    public void setPositionLeft(float left) {
        this.position.x = left;
    }

    /**
     * Set the position Y field.
     *
     * @param y Position Y.
     */
    public void setPositionY(float y) {
        this.position.y = y;
    }

    /**
     * Set the position top field.
     *
     * @param top Position top.
     */
    public void setPositionTop(float top) {
        this.position.y = top;
    }

    /**
     * Set the transform size.
     *
     * @return Transform size.
     */
    public Vector2f getSize() {
        return this.size;
    }

    /**
     * Get the size width field.
     *
     * @return Size width.
     */
    public float getSizeWidth() {
        return this.size.x;
    }

    /**
     * Get the size right field.
     *
     * @return Size right.
     */
    public float getSizeRight() {
        return this.size.x;
    }

    /**
     * Get the size height field.
     *
     * @return Size height.
     */
    public float getSizeHeight() {
        return this.size.y;
    }

    /**
     * Get the size bottom field.
     *
     * @return Size bottom.
     */
    public float getSizeBottom() {
        return this.size.y;
    }

    /**
     * Set the transform size.
     *
     * @param size Transform size.
     */
    public void setSize(Vector2f size) {
        this.size.set(size);
    }

    /**
     * Set the size width field.
     *
     * @param width Size width.
     */
    public void setSizeX(float width) {
        this.size.x = width;
    }

    /**
     * Set the size right field.
     *
     * @param right Size right.
     */
    public void setSizeRight(float right) {
        this.size.x = right;
    }

    /**
     * Set the size height field.
     *
     * @param height Size height.
     */
    public void setSizeY(float height) {
        this.size.y = height;
    }

    /**
     * Set the size bottom field.
     *
     * @param bottom Size bottom.
     */
    public void setSizeBottom(float bottom) {
        this.size.y = bottom;
    }

    /**
     * Get the rectangle transform anchor.
     *
     * @return Rectangle transform anchor.
     */
    public RectangleTransformAnchor getAnchor() {
        return this.anchor;
    }

    /**
     * Set the rectangle transform anchor.
     *
     * @param anchor Rectangle transform anchor.
     */
    public void setAnchor(RectangleTransformAnchor anchor) {
        this.anchor.setAnchor(anchor);
    }

    /**
     * Get the horizontal anchor preset.
     *
     * @return Horizontal anchor preset.
     */
    public HorizontalTransformAnchorType getHorizontalAnchorPreset() {
        return this.anchor.getHorizontalAnchorPreset();
    }

    /**
     * Set the horizontal anchor preset.
     *
     * @param horizontal Horizontal anchor preset.
     */
    public void setHorizontalAnchorPreset(HorizontalTransformAnchorType horizontal) {
        this.anchor.setHorizontalAnchorPreset(horizontal);
    }

    /**
     * Get the vertical anchor preset.
     *
     * @return Vertical anchor preset.
     */
    public VerticalTransformAnchorType getVerticalAnchorPreset() {
        return this.anchor.getVerticalAnchorPreset();
    }

    /**
     * Set the vertical anchor preset.
     *
     * @param vertical Vertical anchor preset.
     */
    public void setVerticalAnchorPreset(VerticalTransformAnchorType vertical) {
        this.anchor.setVerticalAnchorPreset(vertical);
    }

    /**
     * Set both anchor presets.
     *
     * @param horizontal Horizontal anchor preset.
     * @param vertical Vertical anchor preset.
     */
    public void setAnchorPreset(HorizontalTransformAnchorType horizontal, VerticalTransformAnchorType vertical) {
        this.anchor.setAnchorPreset(horizontal, vertical);
    }

    @Override
    public void create() {
        // Define the rectangle transform variable for the children
        RectangleTransform childTransform;

        // Loop through all children, update their rectangle transforms if available
        for(int i = 0, size = getOwner().getChildCount(false); i < size; i++)
            // Get the child's rectangle transform component if available, then update
            if((childTransform = this.getOwner().getChild(i).getComponent(RectangleTransform.class)) != null)
                childTransform.updateParentTransform();
    }

    @Override
    public void start() {
        // Update the parent transform
        // TODO: Is this still necessary?
        updateParentTransform();
    }

    @Override
    public void update() { }

    @Override
    public void destroy() { }
}
