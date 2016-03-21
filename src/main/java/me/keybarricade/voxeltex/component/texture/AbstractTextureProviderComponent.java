package me.keybarricade.voxeltex.component.texture;

import me.keybarricade.voxeltex.component.BaseComponent;

public abstract class AbstractTextureProviderComponent extends BaseComponent implements TextureProviderComponentInterface {

    @Override
    public void bindTexture() {
        getTexture().bind();
    }

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() { }
}
