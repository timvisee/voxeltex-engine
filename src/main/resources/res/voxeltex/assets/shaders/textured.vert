#version 120

// Matrix data
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

// Surface normal and vertex position
varying vec3 surfaceNormal;
varying vec4 position;
uniform int lightCount;

void main() {
    // Determine the position
    position = modelMatrix * gl_Vertex;

    // Pass the texture coordinates to the fragment shader
	gl_TexCoord[0] = gl_MultiTexCoord0;

    // Set the vertex point position
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;

    // Calculate the surface normal
    surfaceNormal = (modelMatrix * vec4(gl_Normal, 0.0)).xyz;
}
