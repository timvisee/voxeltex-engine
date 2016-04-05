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

import org.joml.Vector4f;

public class RectangleTransformAnchor {

    /**
     * Anchor position and size.
     *
     * x = Minimum X.
     * y = Minimum Y.
     * z = Maximum X.
     * w = Maximum Y.
     */
    private final Vector4f anchor = new Vector4f(0.5f);

    /**
     * Constructor.
     */
    public RectangleTransformAnchor() { }

    /**
     * Constructor.
     *
     * @param anchor Anchor.
     */
    public RectangleTransformAnchor(Vector4f anchor) {
        setAnchor(anchor);
    }

    /**
     * Constructor.
     *
     * @param minX Anchor minimum X position.
     * @param minY Anchor minimum Y position.
     * @param maxX Anchor maximum X position.
     * @param maxY Anchor maximum Y position.
     */
    public RectangleTransformAnchor(float minX, float minY, float maxX, float maxY) {
        setAnchor(minX, minY, maxX, maxY);
    }

    /**
     * Constructor.
     *
     * @param horizontal Horizontal anchor preset.
     */
    public RectangleTransformAnchor(HorizontalTransformAnchorType horizontal) {
        setHorizontalAnchorPreset(horizontal);
        setVerticalAnchorPreset(VerticalTransformAnchorType.MIDDLE);
    }

    /**
     * Constructor.
     *
     * @param vertical Vertical anchor preset.
     */
    public RectangleTransformAnchor(VerticalTransformAnchorType vertical) {
        setHorizontalAnchorPreset(HorizontalTransformAnchorType.CENTER);
        setVerticalAnchorPreset(vertical);
    }

    /**
     * Constructor.
     *
     * @param horizontal Horizontal anchor preset.
     * @param vertical Vertical anchor preset.
     */
    public RectangleTransformAnchor(HorizontalTransformAnchorType horizontal, VerticalTransformAnchorType vertical) {
        setAnchorPreset(horizontal, vertical);
    }

    /**
     * Get the anchor's minimum and maximum position.
     *
     * @return Anchor.
     */
    public Vector4f getAnchor() {
        return this.anchor;
    }

    /**
     * Set the anchor's minimum and maximum position.
     *
     * @param anchor Anchor.
     *
     * @return This.
     */
    public RectangleTransformAnchor setAnchor(Vector4f anchor) {
        return setAnchor(anchor.x, anchor.y, anchor.z, anchor.w);
    }

    /**
     *
     * Set the anchor's minimum and maximum position.
     *
     * @param minX Anchor minimum X position.
     * @param minY Anchor minimum Y position.
     * @param maxX Anchor maximum X position.
     * @param maxY Anchor maximum Y position.
     * @return
     */
    public RectangleTransformAnchor setAnchor(float minX, float minY, float maxX, float maxY) {
        // Set the anchor
        setMinX(minX);
        setMinY(minY);
        setMaxX(maxX);
        setMaxY(maxY);

        // Return this instance for method chaining
        return this;
    }

    /**
     * Get the minimum X position of the anchor.
     *
     * @return Minimum X.
     */
    public float getMinX() {
        return this.anchor.x;
    }

    /**
     * Set the minimum X position for this anchor.
     *
     * @param minX Minimum X.
     *
     * @return This.
     */
    public RectangleTransformAnchor setMinX(float minX) {
        // TODO: Make sure the value is in-bound, and make sure the minimum and maximum are ordered correctly!
        this.anchor.x = minX;

        // Return this instance for method chaining
        return this;
    }

    /**
     * Get the minimum Y position of the anchor.
     *
     * @return Minimum Y.
     */
    public float getMinY() {
        return this.anchor.y;
    }

    /**
     * Set the minimum Y position of the anchor.
     *
     * @param minY Minimum Y.
     *
     * @return This.
     */
    public RectangleTransformAnchor setMinY(float minY) {
        // TODO: Make sure the value is in-bound, and make sure the minimum and maximum are ordered correctly!
        this.anchor.y = minY;

        // Return this instance for method chaining
        return this;
    }

    /**
     * Get the maximum X position of the anchor.
     *
     * @return Maximum X.
     */
    public float getMaxX() {
        return this.anchor.z;
    }

    /**
     * Set the maximum X position of the anchor.
     *
     * @param maxX Maximum X position.
     *
     * @return This.
     */
    public RectangleTransformAnchor setMaxX(float maxX) {
        // TODO: Make sure the value is in-bound, and make sure the minimum and maximum are ordered correctly!
        this.anchor.z = maxX;

        // Return this instance for method chaining
        return this;
    }

    /**
     * Get the maximum Y position of this anchor.
     *
     * @return Maximum Y position.
     */
    public float getMaxY() {
        return this.anchor.w;
    }

    /**
     * Set the maximum Y position of this anchor.
     *
     * @param maxY Maximum Y position.
     *
     * @return This.
     */
    public RectangleTransformAnchor setMaxY(float maxY) {
        // TODO: Make sure the value is in-bound, and make sure the minimum and maximum are ordered correctly!
        this.anchor.w = maxY;

        // Return this instance for method chaining
        return this;
    }

    /**
     * Get the anchor width.
     *
     * @return Anchor width.
     */
    public float getWidth() {
        return getMaxX() - getMinX();
    }

    /**
     * Get the anchor height.
     *
     * @return Anchor height.
     */
    public float getHeight() {
        return getMaxY() - getMinY();
    }

    /**
     * Check whether this anchor has any width.
     *
     * @return True if this anchor has any width, false if it hasn't.
     */
    public boolean hasWidth() {
        return this.anchor.x != this.anchor.z;
    }

    /**
     * Check whethre this anchor has any height.
     *
     * @return True if this anchor has any height, false if it hasn't.
     */
    public boolean hasHeight() {
        return this.anchor.y != this.anchor.w;
    }

    /**
     * Get the closest horizontal anchor preset.
     * This is calculated based on the current anchor configuration.
     *
     * @return Closest horizontal anchor preset.
     */
    public HorizontalTransformAnchorType getClosestHorizontalAnchorPreset() {
        // Return the stretched anchor if this anchor has any width
        if(hasWidth())
            return HorizontalTransformAnchorType.STRETCH;

        // Determine the closest anchor preset
        if(getMinX() < 0.25f)
            return HorizontalTransformAnchorType.LEFT;

        else if(getMinX() > 0.75f)
            return HorizontalTransformAnchorType.RIGHT;

        else
            return HorizontalTransformAnchorType.CENTER;
    }

    /**
     * Set the horizontal anchor preset.
     *
     * @param horizontal Horizontal anchor preset.
     */
    public void setHorizontalAnchorPreset(HorizontalTransformAnchorType horizontal) {
        // Update the anchor position
        switch(horizontal) {
            case LEFT:
                this.anchor.x = 0;
                this.anchor.z = 0;
                break;

            default:
            case CENTER:
                this.anchor.x = 0.5f;
                this.anchor.z = 0.5f;
                break;

            case RIGHT:
                this.anchor.x = 1;
                this.anchor.z = 1;
                break;

            case STRETCH:
                this.anchor.x = 0;
                this.anchor.z = 1;
                break;
        }
    }

    /**
     * Get the closest vertical anchor preset.
     * This is calculated based on the current anchor configuration.
     *
     * @return Closest vertical anchor preset.
     */
    public VerticalTransformAnchorType getClosestVerticalAnchorPreset() {
        // Return the stretched anchor if this anchor has any height
        if(hasHeight())
            return VerticalTransformAnchorType.STRETCH;

        // Determine the closest anchor preset
        if(getMinY() < 0.25f)
            return VerticalTransformAnchorType.BOTTOM;

        else if(getMinY() > 0.75f)
            return VerticalTransformAnchorType.TOP;

        else
            return VerticalTransformAnchorType.MIDDLE;
    }

    /**
     * Set the vertical anchor preset.
     *
     * @param vertical Vertical anchor preset.
     */
    public void setVerticalAnchorPreset(VerticalTransformAnchorType vertical) {
        // Update the anchor position
        switch(vertical) {
            case BOTTOM:
                this.anchor.y = 0;
                this.anchor.w = 0;
                break;

            default:
            case MIDDLE:
                this.anchor.y = 0.5f;
                this.anchor.w = 0.5f;
                break;

            case TOP:
                this.anchor.y = 1;
                this.anchor.w = 1;
                break;

            case STRETCH:
                this.anchor.y = 0;
                this.anchor.w = 1;
                break;
        }
    }

    /**
     * Set both anchor presets.
     *
     * @param anchor Anchor.
     */
    public void setAnchor(RectangleTransformAnchor anchor) {
        setAnchor(anchor.getAnchor());
    }

    /**
     * Set both anchor presets.
     *
     * @param horizontal Horizontal anchor preset.
     * @param vertical Vertical anchor preset.
     */
    public void setAnchorPreset(HorizontalTransformAnchorType horizontal, VerticalTransformAnchorType vertical) {
        setHorizontalAnchorPreset(horizontal);
        setVerticalAnchorPreset(vertical);
    }
}
