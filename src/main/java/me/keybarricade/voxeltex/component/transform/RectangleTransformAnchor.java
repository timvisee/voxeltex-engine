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

public class RectangleTransformAnchor {

    /**
     * Horizontal anchor preset.
     */
    private HorizontalTransformAnchorType horizontal = HorizontalTransformAnchorType.CENTER;

    /**
     * Vertical anchor preset.
     */
    private VerticalTransformAnchorType vertical = VerticalTransformAnchorType.MIDDLE;

    /**
     * Constructor.
     */
    public RectangleTransformAnchor() { }

    /**
     * Constructor.
     *
     * @param horizontal Horizontal anchor preset.
     */
    public RectangleTransformAnchor(HorizontalTransformAnchorType horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Constructor.
     *
     * @param vertical Vertical anchor preset.
     */
    public RectangleTransformAnchor(VerticalTransformAnchorType vertical) {
        this.vertical = vertical;
    }

    /**
     * Constructor.
     *
     * @param horizontal Horizontal anchor preset.
     * @param vertical Vertical anchor preset.
     */
    public RectangleTransformAnchor(HorizontalTransformAnchorType horizontal, VerticalTransformAnchorType vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    /**
     * Get the horizontal anchor preset.
     *
     * @return Horizontal anchor preset.
     */
    public HorizontalTransformAnchorType getHorizontalAnchorPreset() {
        return this.horizontal;
    }

    /**
     * Set the horizontal anchor preset.
     *
     * @param horizontal Horizontal anchor preset.
     */
    public void setHorizontalAnchorPreset(HorizontalTransformAnchorType horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Get the vertical anchor preset.
     *
     * @return Vertical anchor preset.
     */
    public VerticalTransformAnchorType getVerticalAnchorPreset() {
        return this.vertical;
    }

    /**
     * Set the vertical anchor preset.
     *
     * @param vertical Vertical anchor preset.
     */
    public void setVerticalAnchorPreset(VerticalTransformAnchorType vertical) {
        this.vertical = vertical;
    }

    /**
     * Set both anchor presets.
     *
     * @param anchor Anchor.
     */
    public void setAnchor(RectangleTransformAnchor anchor) {
        setAnchorPreset(anchor.getHorizontalAnchorPreset(), anchor.getVerticalAnchorPreset());
    }

    /**
     * Set both anchor presets.
     *
     * @param horizontal Horizontal anchor preset.
     * @param vertical Vertical anchor preset.
     */
    public void setAnchorPreset(HorizontalTransformAnchorType horizontal, VerticalTransformAnchorType vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
}
