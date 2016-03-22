#version 400 core

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix = mat4(1.0);
uniform mat4 modelMatrix = mat4(1.0);

uniform sampler2D modelTexture;
uniform vec3 lightColour = vec3(1.0, 0.5, 0.0);
uniform vec3 lightPosition = vec3(0.0, 1, 0.0);
uniform vec2 tiling = vec2(1.0, 1.0);

in vec2 fragTexCoord;

in vec3 surfaceNormal;
in vec3 toLightVector;
in float lightDistance;

out vec4 out_Color;

void main() {
    // Normalize the surface and light vector
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    // Calculate the light fading factor
    float lightFadeFactor = 1 / pow(lightDistance, 2);
    lightFadeFactor = 1;

    // Calculate the dot product of both vectors and clamp the brightness to zero and above
    float nDotl = dot(unitNormal, unitLightVector);
    float brightness = max(nDotl, 0.0) * lightFadeFactor;

    // Calculate the diffuse color
    vec3 diffuse = brightness * lightColour * 3.0;

    out_Color = vec4(diffuse, 1.0) * texture(modelTexture, fragTexCoord);
}
