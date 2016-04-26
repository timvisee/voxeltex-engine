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

package com.timvisee.voxeltex.architecture.component.overlay.gui;

import com.timvisee.voxeltex.module.Color;
import com.timvisee.voxeltex.module.material.Material;
import com.timvisee.voxeltex.module.render.RenderOverlayHelper;
import com.timvisee.voxeltex.module.shader.ShaderManager;
import com.timvisee.voxeltex.module.shader.specific.GuiTextureShader;
import com.timvisee.voxeltex.module.texture.Image;
import com.timvisee.voxeltex.module.texture.Texture;
import com.timvisee.voxeltex.module.transform.rectangle.Rectangle;

public class GuiImageComponent extends AbstractGuiComponent {

    /**
     * GUI image material.
     */
    private Material material = new Material(ShaderManager.SHADER_DEFAULT_GUI_TEXTURE);

    /**
     * Color channel intensity.
     */
    private Color color = new Color(1, 1, 1, 1);

    /**
     * Temporary rectangle variable, used to minimize object allocation at runtime to improve overall performance.
     */
    private final Rectangle tempRectangle = new Rectangle();

    /**
     * Constructor.
     */
    public GuiImageComponent() { }

    /**
     * Constructor.
     *
     * @param material Material.
     */
    public GuiImageComponent(Material material) {
        setMaterial(material);
    }

    /**
     * Constructor.
     *
     * @param texture Texture.
     */
    public GuiImageComponent(Texture texture) {
        setTexture(texture);
    }

    /**
     * Constructor.
     *
     * @param image Image.
     */
    public GuiImageComponent(Image image) {
        setImage(image);
    }

    @Override
    public void onDrawOverlay() {
        // Bind the material if available
        if(this.material != null)
            this.material.bind();

        // Set the color level if this is an GUI texture shader
        if(this.material != null && this.material.getShader() instanceof GuiTextureShader) {
            // Set the color intensity
            ((GuiTextureShader) this.material.getShader()).setColor(this.color);

            // Update the shader
            this.material.update(getScene());
        }

        // Synchronize to ensure we aren't using this temporary variable in multiple spots at the same time
        //noinspection Duplicates
        synchronized(this.tempRectangle) {
            // Make sure we've a valid transform component, if not, skip the following code with an error message
            if(!hasRectangleTransform()) {
                System.out.println("No RectangleTransform component in " + this + " of " + getOwner() + ", unable to render");
                return;
            }

            // Get the overlay rectangle
            getRectangleTransform().getOverlayRectangle(this.tempRectangle);

            // Render the rectangle
            RenderOverlayHelper.renderRectangle(
                    this.tempRectangle.getX(), this.tempRectangle.getY(),
                    this.tempRectangle.getWidth(), this.tempRectangle.getHeight()
            );
        }

        // Unbind the material if available
        if(this.material != null)
            this.material.unbind();
    }

    /**
     * Get the GUI texture material.
     *
     * @return GUI texture material.
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Set the GUI texture material.
     *
     * @param material GUI texture material.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Get the texture.
     *
     * @return Texture.
     */
    public Texture getTexture() {
        return material.getTexture();
    }

    /**
     * Set the texture.
     *
     * @param texture Texture.
     */
    public void setTexture(Texture texture) {
        this.material.setTexture(texture);
    }

    /**
     * Set the image.
     *
     * @param image Image.
     */
    public void setImage(Image image) {
        setTexture(Texture.fromImage(image));
    }

    /**
     * Get the color channel intensity.
     *
     * @return Color channel intensity.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Get the color channel intensity.
     *
     * @param color Color channel intensity.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set the color channel intensity.
     *
     * @param alpha Alpha channel intensity.
     */
    public void setAlpha(float alpha) {
        this.color.setAlpha(alpha);
    }
}
