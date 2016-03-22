#version 120

in vec4 position;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

out vec3 surfaceNormal;
out vec3 toLightVector;

uniform vec3 lightPosition = vec3(0.0, 3.0, 0.0);
out float lightDistance;

void main() {
    // Pass the texture coordinates to the fragment shader
	gl_TexCoord[0] = gl_MultiTexCoord0 ;

    // Set the vertex point position
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * gl_Vertex;

    // Calculate the vertex position
	vec4 vertexPosition = modelMatrix * gl_Vertex;

    // Calculate the distance to the light
    lightDistance = distance(vertexPosition.xyz, lightPosition);

    // Calculate the surface normal and light vector
    surfaceNormal = (modelMatrix * vec4(gl_Normal, 0.0)).xyz;
    //surfaceNormal = modelMatrix * gl_Normal;
    toLightVector = lightPosition - vertexPosition.xyz;
}
