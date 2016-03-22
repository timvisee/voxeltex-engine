#version 120

// Texture and tiling data
uniform sampler2D texture;
uniform vec2 tiling = vec2(1.0, 1.0);
uniform float ambientBrightness = 0.05; // TODO: Make this configurable!

// Fragment position data
varying vec4 position;

// Surface normal
varying vec3 surfaceNormal;

// Light data
const int LIGHT_COUNT_MAX = 16;
uniform int lightCount;
uniform int lightType[LIGHT_COUNT_MAX];
uniform vec3 lightPosition[LIGHT_COUNT_MAX];
uniform vec3 lightRotation[LIGHT_COUNT_MAX];
uniform vec4 lightColor[LIGHT_COUNT_MAX];

void main() {
    // Create a variable to define the diffuse color in
    vec3 diffuse = vec3(0);

    // Calculate the lighting for all different lights
    for(int i = 0; i < lightCount; i++) {
        // TODO: Validate the light type!

        // Get the light position
        vec3 pos = lightPosition[i];

        // Calculate the distance to the light
        float lightDistance = distance(position.xyz, pos);

        // Calculate the light direction
        vec3 lightDirection = pos - position.xyz;

        // Normalize the surface and light vector
        vec3 unitNormal = normalize(surfaceNormal);
        vec3 unitLightDirection = normalize(lightDirection);

        // Calculate the light fading factor
        float lightFadeFactor = 1 / pow(lightDistance, 2);

        // Calculate the dot product of both vectors and clamp the brightness to zero and above
        float nDotl = dot(unitNormal, unitLightDirection);
        float brightness = max(nDotl, 0.0) * lightFadeFactor * lightColor[i].w;

        // Calculate the diffuse color and append it to the result
        diffuse += brightness * lightColor[i].xyz;
    }

    // Multiply the diffuse lighting by three for better appearance
    // TODO: Should we do this?
    diffuse *= vec3(3);

    // Add ambient light
    diffuse += vec3(ambientBrightness);

    // Determine and set the fragment color
    gl_FragColor = vec4(diffuse, 1.0) * texture2D(texture, gl_TexCoord[0].st * tiling);
}
