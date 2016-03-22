//combined projection and view matrix
//uniform mat4 u_projView;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

//"in" attributes from our SpriteBatch
//attribute vec2 Position;
attribute vec2 TexCoord;
attribute vec4 Color;

//"out" varyings to our fragment shader
varying vec2 vTexCoord;
varying vec4 vColor;
 
void main() {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * gl_Vertex;


	vColor = Color;
	vTexCoord = TexCoord;
    vTexCoord = gl_MultiTexCoord0;
}