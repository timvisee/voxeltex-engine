package me.keybarricade.voxeltex.font;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.texture.Texture;

public class BitmapFontMaterial extends Material {

    /**
     * Constructor.
     *
     * @param fontTexture Font texture.
     */
    public BitmapFontMaterial(Texture fontTexture) {
        // Construct the super
        super(fontTexture);

        // TODO: Load the proper font shader!
    }
}
