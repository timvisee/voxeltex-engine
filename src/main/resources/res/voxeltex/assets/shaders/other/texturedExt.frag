#version 120

uniform sampler2D texture;
uniform vec3 lightColour = vec3(1.0, 0.5, 0.0);
uniform vec2 tiling = vec2(1.0, 1.0);

uniform vec3 lightPosition = vec3(0.0, 2, 0.0);

varying vec3 surfaceNormal;

varying vec4 position;



//const vec4 diffuse_color = vec4(1.0, 0.0, 0.0, 1.0);
//const vec4 specular_color = vec4(1.0, 1.0, 1.0, 1.0);
//varying float specular_intensity;
//varying float diffuse_intensity;

void main() {


    // Calculate the distance to the light
    float lightDistance = distance(position.xyz, lightPosition);

    // Calculate the light direction
    vec3 lightDirection = lightPosition - position.xyz;


    // Normalize the surface and light vector
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightDirection = normalize(lightDirection);

    // Calculate the light fading factor
    float lightFadeFactor = 1 / pow(lightDistance, 2);
    //lightFadeFactor = 1;

    // Calculate the dot product of both vectors and clamp the brightness to zero and above
    float nDotl = dot(unitNormal, unitLightDirection);
    float brightness = max(nDotl, 0.0) * lightFadeFactor * 10;

    // Calculate the diffuse color
    vec3 diffuse = brightness * lightColour;

    gl_FragColor = vec4(diffuse, 1.0) * texture2D(texture, gl_TexCoord[0].st);

//    gl_FragColor =
//    		diffuse_color * diffuse_intensity +
//    		specular_color * specular_intensity;
}
