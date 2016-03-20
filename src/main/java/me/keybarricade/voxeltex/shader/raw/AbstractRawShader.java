package me.keybarricade.voxeltex.shader.raw;

public abstract class AbstractRawShader implements RawShaderInterface {

    @Override
    public boolean hasVertexShader() {
        return getVertexShader() != null;
    }

    @Override
    public boolean hasFragmentShader() {
        return getFragmentShader() != null;
    }

    /**
     * Compile the shader from the given sources and load it into OpenGL.
     *
     * @return OpenGL shader program ID.
     */
    public abstract int compile();
}
