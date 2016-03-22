#version 330

layout (location = 0) in vec4 position;
layout (location = 2) in vec3 normal; // TODO: Why is this always at 2?

in vec2 vertTexCoord;
out vec2 fragTexCoord;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

out vec3 surfaceNormal;
out vec3 toLightVector;

uniform vec3 lightPosition = vec3(0.0, 1, 0.0);
out float lightDistance;

void main() {
    // Pass the texture coordinates to the fragment shader
    fragTexCoord = vertTexCoord;

    // Set the vertex point position
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * position;

    // Calculate the vertex position
	vec4 vertexPosition = modelMatrix * position;

    // Calculate the distance to the light
    lightDistance = distance(vertexPosition.xyz, lightPosition);

    // Calculate the surface normal and light vector
    surfaceNormal = (modelMatrix * vec4(normal, 0.0)).xyz;
    toLightVector = lightPosition - vertexPosition.xyz;
}
